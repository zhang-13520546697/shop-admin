package com.fh.shop.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * <pre>包名称：com.fh.shop.util
 * 类名称：DistributedSessionUtil
 * 类描述：
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/1817:04
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class  DistributedSessionUtil {
    //根据客户端传过来的cookie获取sessionId

    public static String getSessionId(HttpServletRequest request, HttpServletResponse response) {
        String redisSessionId = CookieUtil.readCookie(SystemConstant.COOKIE_NAME,request);
        if(StringUtils.isEmpty(redisSessionId)){
            redisSessionId = UUID.randomUUID().toString();
            CookieUtil.writeCookie(SystemConstant.COOKIE_NAME,redisSessionId,SystemConstant.DOMAIN,response);
        }
        return redisSessionId;
    }
}
