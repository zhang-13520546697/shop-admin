package com.fh.shop.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.annotation.IgnoreCheck;
import com.fh.shop.po.user.UserInfo;
import com.fh.shop.util.DistributedSessionUtil;
import com.fh.shop.util.KeyUtil;
import com.fh.shop.util.RedisUtil;
import com.fh.shop.util.SystemConstant;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * <pre>包名称：com.fh.shop.interceptor
 * 类名称：LoginInterceptor
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/1721:06
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        StringBuffer requestURL = request.getRequestURL();
        String requestURI =  request.getRequestURI();
        String contextPath = request.getContextPath();
       /* //白名单  要放行的方法
        if(requestURI.endsWith(SystemConstant.RESULT_URI)){
            //放行
            return true;
        }*/
       //白名单升级版  通过注解来断定是否是安全请求
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(IgnoreCheck.class)){
            return true;
        }
        //UserInfo user = (UserInfo) request.getSession().getAttribute(SystemConstant.CURR_USER);
        //从客户端传过来的cookie中获取sessionid
        String sessionId = DistributedSessionUtil.getSessionId(request, response);
        //判断用户信息是否存在
        boolean exists = RedisUtil.exists(KeyUtil.UserKey(sessionId));
        //取出用户信息
        String userJson = RedisUtil.get(KeyUtil.UserKey(sessionId));
        //如果用户为空
       if(!exists){
           //返回登录页面
           response.sendRedirect(SystemConstant.RESULT_URL);
           //拦截  不放行
           return false;
       }else{
           //放行  可访问后台资源
           //分布式session续命
           RedisUtil.expire(KeyUtil.UserKey(sessionId),SystemConstant.USER_TIMEOUT);

           UserInfo user = JSONObject.parseObject(userJson, UserInfo.class);
           request.getSession().setAttribute(SystemConstant.CURR_USER,user);
           return true;
       }

    }

}
