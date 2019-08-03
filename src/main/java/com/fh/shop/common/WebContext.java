package com.fh.shop.common;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>包名称：com.fh.shop.common
 * 类名称：WebContext
 * 类描述：获取request的工具类
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/1912:13
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class WebContext{

        public static  ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();

        public static void setRequest(HttpServletRequest request){
            requestThreadLocal.set(request);
        }

        public static HttpServletRequest getRequest(){
            return requestThreadLocal.get();
        }

        public static void remove(){
            requestThreadLocal.remove();
        }


}
