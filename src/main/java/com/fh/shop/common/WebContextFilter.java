package com.fh.shop.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <pre>包名称：com.fh.shop.common
 * 类名称：WebContextFilter
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/1912:26
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class WebContextFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //在每次前台发送请求时，都先将request存入WebContext
        WebContext.requestThreadLocal.set((HttpServletRequest) servletRequest);

        //继续进行后续的访问
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        } finally {
            //访问完后从WebContext中删除请求  必执行
            WebContext.requestThreadLocal.remove();
        }
    }
    @Override
    public void destroy() {

    }
}
