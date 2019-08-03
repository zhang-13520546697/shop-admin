package com.fh.shop.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.annotation.IgnoreCheck;
import com.fh.shop.biz.user.ILoginService;
import com.fh.shop.common.ResponseEnum;
import com.fh.shop.common.ServerResponse;
import com.fh.shop.po.user.UserInfo;
import com.fh.shop.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>包名称：com.fh.shop.controller.user
 * 类名称：LoginController
 * 类描述：登陆
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/1714:05
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @Resource(name="loginService")
    private ILoginService loginService;

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 登录验证
     * 创建时间： 2019/6/20 
     * @Param [user, request]
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("/login")
    @ResponseBody
    @IgnoreCheck
    public ServerResponse findLogin(UserInfo user, HttpServletRequest request, HttpServletResponse response){
        //String code = (String) request.getSession().getAttribute("code");
        String sessionId = DistributedSessionUtil.getSessionId(request, response);
        String code = RedisUtil.get(KeyUtil.CookieKey(sessionId));
        boolean exists = RedisUtil.exists(KeyUtil.CookieKey(sessionId));
        if(!exists){
            return ServerResponse.error(ResponseEnum.CODE_TIMEOUT);
        }
        //先判定验证码是否正确  原因为了减少与数据库交互次数
        if(StringUtils.isEmpty(user.getImgCode())){
            return ServerResponse.error(ResponseEnum.CODE_IS_NULL);
        }
        if(!user.getImgCode().equals(code)){
            return ServerResponse.error(ResponseEnum.CODE_IS_ERROR);
        }
        //判断用户名和密码不能为空
        if(StringUtils.isEmpty(user.getUserName() ) || StringUtils.isEmpty(user.getUserPwd())){
           return ServerResponse.error(ResponseEnum.USER_PASSWRD_ISNULL);
        }
        //用户名是否正确
        UserInfo  userDb =  loginService.findLogin(user.getUserName());
        if(userDb == null){
          return ServerResponse.error(ResponseEnum.USER_ERROR);
        }
        //密码是否正确
        if(!Md5Util.md5(user.getUserPwd()+userDb.getSalt()).equals(userDb.getUserPwd())){
           return  ServerResponse.error(ResponseEnum.PASSWRD_ERROR);
        }
        //成功登录
        //更新为当前时间为登录时间
        loginService.updateLogTime(new Date(),userDb.getId());
        //request.getSession().setAttribute(SystemConstant.CURR_USER,userDb);
        String userJson = JSONObject.toJSONString(userDb);
        RedisUtil.setex(KeyUtil.UserKey(sessionId),userJson,SystemConstant.USER_TIMEOUT);
        return ServerResponse.success();
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 跳转用户添加页面
     * 创建时间： 2019/6/20 
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("toAddUserPage")
    public String toAddUserPage(){
        return "user/addUser";
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 注册用户
     * 创建时间： 2019/7/19
     * @Param [userInfo]
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("addUser")
    @ResponseBody
    public ServerResponse addUser(UserInfo userInfo){
        loginService.addUser(userInfo);
        return  ServerResponse.success();
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 验证用户名唯一
     * 创建时间： 2019/6/20 
     * @Param [userName]
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("checkUserName")
    @ResponseBody
    public ServerResponse checkUserName(String userName){
        UserInfo userDB = loginService.findLogin(userName);
        if (userDB!=null){
            //用户名存在
            return ServerResponse.error(ResponseEnum.USER_IS_EXIST);
        }

        return ServerResponse.success();
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 注销用户
     * 创建时间： 2019/6/22 
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("logOut")
    @ResponseBody
    public ServerResponse logOut(HttpServletRequest request,HttpServletResponse response){
        //清除session中的数据
        request.getSession().invalidate();
        //清除redis中的数据
        String sessionId = DistributedSessionUtil.getSessionId(request, response);
        //删除cookie
        CookieUtil.deleteCookie(SystemConstant.COOKIE_NAME,SystemConstant.DOMAIN,response);
        //RedisUtil.del(KeyUtil.UserKey(sessionId));
        return ServerResponse.success();
    }

}
