package com.fh.shop.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>包名称：com.fh.shop.exception
 * 类名称：ControllerExceptionHandler
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/1722:07
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Map handleException(Exception ex){
        ex.printStackTrace();
        Map result = new HashMap();
        result.put("code","-1");
        result.put("msg","error");
        return result;
    }
}
