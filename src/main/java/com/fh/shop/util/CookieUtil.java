package com.fh.shop.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>包名称：com.fh.shop.util
 * 类名称：CookieUtil
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/1814:21
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class CookieUtil {

    //写cookie
    public static final void writeCookie(String key ,String value, String domain,HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setDomain(domain);//设置域
        cookie.setPath("/");
        //如果是持久化cookie则需要设置maxAge 单位秒 而会话cookie则不需要
        //cookie.setMaxAge(); //设置过期时间
        response.addCookie(cookie);
    }

    //删cookie
    public static final void deleteCookie(String key , String domain,HttpServletResponse response){
        Cookie cookie = new Cookie(key, null);
        cookie.setDomain(domain);//设置域
        cookie.setPath("/");
        //将过期时间设为0 立即删除
        cookie.setMaxAge(0); //设置过期时间
        response.addCookie(cookie);
    }

    //读cookie
    public static final String readCookie(String key,HttpServletRequest request){
        //获取客户端传过来的cookie数组
        Cookie[] cookies = request.getCookies(  );
        if(null == cookies || cookies.length==0){
            return "";

        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(key)){
                return cookie.getValue();
            }
        }
        return "";
    }

}
