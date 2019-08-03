package com.fh.shop.common;

public enum ResponseEnum {
    USER_PASSWRD_ISNULL(500,"用户名或密码不能为空"),
    USER_ERROR(501,"用户名错误或不存在"),
    PASSWRD_ERROR(502,"密码错误"),
    USER_IS_EXIST(503,"用户名已存在"),
    FILE_NAME_IS_NULL(1001,"文件名上传失败"),
    CODE_IS_NULL(2000,"验证码不能为空！！"),
    CODE_IS_ERROR(2001,"验证码错误！！"),
    CODE_TIMEOUT(2002,"验证码过期，请刷新！！"),
    ERROR(-1,"error"),
    SUCCESS(200,"ok");

    private int code;

    private String msg;

    private ResponseEnum(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
