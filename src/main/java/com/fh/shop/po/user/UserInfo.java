package com.fh.shop.po.user;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>包名称：com.fh.shop.po.user
 * 类名称：UserInfo
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/1714:08
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -866346112356310461L;

    private Integer  id;

    private String  userName;

    private String userPwd;

    private String realName;//真实姓名

    private Integer sex;

    private Date birthday;

    private Date logTime;

    private String salt;//加密盐

    private String phoneNumber;//电话
    private String email;//邮箱
    private String headImagePath;//头像
    private String education;//学历
    private String nation;//民族
    private String idNumber;//身份证号
    private String areaName;//省市县 冗余字段
    private Integer ar1;
    private Integer ar2;
    private Integer ar3;
    private String address;//详细住址

    private String imgCode;

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImagePath() {
        return headImagePath;
    }

    public void setHeadImagePath(String headImagePath) {
        this.headImagePath = headImagePath;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAr1() {
        return ar1;
    }

    public void setAr1(Integer ar1) {
        this.ar1 = ar1;
    }

    public Integer getAr2() {
        return ar2;
    }

    public void setAr2(Integer ar2) {
        this.ar2 = ar2;
    }

    public Integer getAr3() {
        return ar3;
    }

    public void setAr3(Integer ar3) {
        this.ar3 = ar3;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
