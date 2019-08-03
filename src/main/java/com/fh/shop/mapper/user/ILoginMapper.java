package com.fh.shop.mapper.user;

import com.fh.shop.po.log.LogInfo;
import com.fh.shop.po.user.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ILoginMapper {

   public UserInfo findLogin(String userName);


   void addUser(UserInfo userInfo);

   void updateLogTime(@Param("date")Date date, @Param("userId")Integer userId);
}
