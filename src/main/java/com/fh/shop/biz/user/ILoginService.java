package com.fh.shop.biz.user;

import com.fh.shop.po.log.LogInfo;
import com.fh.shop.po.user.UserInfo;

import java.util.Date;

public interface ILoginService {

    UserInfo findLogin(String userName);


    void addUser(UserInfo userInfo);

    void updateLogTime(Date date, Integer userId);

}
