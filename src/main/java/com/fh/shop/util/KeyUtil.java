package com.fh.shop.util;

/**
 * <pre>包名称：com.fh.shop.util
 * 类名称：KeyUtil
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/1817:01
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class KeyUtil {

    public static String CookieKey(String value){
        return  "code:"+value;
    }

    public static  String UserKey(String value){
        return "user:"+value;
    }


}
