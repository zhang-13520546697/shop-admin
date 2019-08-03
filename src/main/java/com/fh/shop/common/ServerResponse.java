package com.fh.shop.common;

import java.io.Serializable;

/**
 * <pre>包名称：com.fh.shop.common
 * 类名称：ServerResponse
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/1722:58
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class ServerResponse implements Serializable {
    private static final long serialVersionUID = 8156187193274650130L;

    private int code;

    private String msg;

    private Object data ;

    public ServerResponse(){}

    private ServerResponse(int code, String msg, Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public static ServerResponse success(){
        return new  ServerResponse (ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getMsg(),null);
    }

    public static ServerResponse success(Object data){
        return new  ServerResponse (ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getMsg(),data);
    }

    public static  ServerResponse error(){
        return  new ServerResponse(ResponseEnum.ERROR.getCode(),ResponseEnum.ERROR.getMsg(),null);
    }
    public static  ServerResponse error(ResponseEnum responseEnum){
        return  new ServerResponse(responseEnum.getCode(),responseEnum.getMsg(),null);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
