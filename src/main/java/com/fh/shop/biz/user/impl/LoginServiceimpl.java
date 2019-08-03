package com.fh.shop.biz.user.impl;

import com.fh.shop.biz.user.ILoginService;
import com.fh.shop.mapper.user.ILoginMapper;
import com.fh.shop.po.log.LogInfo;
import com.fh.shop.po.user.UserInfo;
import com.fh.shop.util.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * <pre>包名称：com.fh.shop.biz.user.impl
 * 类名称：LoginServiceimpl
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/1720:28
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Service("loginService")
public class LoginServiceimpl implements ILoginService {

    @Resource
    private ILoginMapper loginMapper;

    @Override
    public UserInfo findLogin(String userName) {
        UserInfo login = loginMapper.findLogin(userName);
        return login;


    }

    @Override
    public void addUser(UserInfo userInfo) {
        String salt = UUID.randomUUID().toString();
        userInfo.setSalt(salt);
        userInfo.setUserPwd(Md5Util.md5(userInfo.getUserPwd()+salt));
        loginMapper.addUser(userInfo);
    }

    @Override
    public void updateLogTime(Date date, Integer userId) {
        loginMapper.updateLogTime(date,userId);
    }


}

