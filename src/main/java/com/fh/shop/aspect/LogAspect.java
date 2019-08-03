package com.fh.shop.aspect;

import com.fh.shop.annotation.LogAnnotation;
import com.fh.shop.biz.log.ILogService;
import com.fh.shop.biz.user.ILoginService;
import com.fh.shop.common.WebContext;
import com.fh.shop.po.log.LogInfo;
import com.fh.shop.po.user.UserInfo;
import com.fh.shop.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * <pre>包名称：com.fh.shop.aspect
 * 类名称：LogAspect
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/1823:45
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class LogAspect {

    @Resource(name="logService")
    private ILogService logService;

    private  static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    public  Object doLog(ProceedingJoinPoint pjp){
        HttpSession session = WebContext.getRequest().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(SystemConstant.CURR_USER);
        HttpServletRequest request = WebContext.getRequest();
        String userName = userInfo.getUserName();
        Integer status=null;
        String Content=null;
        String msg = null;
        Object result = null;
        //根据反射获取类名
        String canonicalName = pjp.getTarget().getClass().getCanonicalName();
        //获取方法
        String name = pjp.getSignature().getName();
        //获取详情
        String detailDesc = buildDetailDesc(request);
        //获取自定义注解方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        //获取方法
        Method method = methodSignature.getMethod();
        //判断方法中是否含有指定注解
        if(method.isAnnotationPresent(LogAnnotation.class)){
            //获取指定注解
            LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
            //获取注解内容
            msg = annotation.msg();

        }
        try {
            LOGGER.info("{}开始执行{}的{}()方法",userName,canonicalName,name);
            //pjp.proceed(); 控制层中的每一个方法
            //result 相当于 return返回的值
            result = pjp.proceed();
            LOGGER.info("{}执行{}的{}()方法成功",userName,canonicalName,name);
            status=SystemConstant.LOG_SUCCESS;
            Content=userName+"执行"+canonicalName+"的"+name+"方法成功";
            LogInfo logInfo= addLog(userName, status, Content,detailDesc,msg);
            logService.addLog(logInfo);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            LOGGER.error("{}执行{}的{}()方法失败：{}",userName,canonicalName,name,throwable.getMessage());
            status=SystemConstant.LOG_ERROR;
            Content=userName+"执行"+canonicalName+"的"+name+"方法失败:"+throwable.getMessage();
            LogInfo logInfo = addLog(userName, status, Content,detailDesc,msg);
            logService.addLog(logInfo);
            throw new RuntimeException(throwable.getMessage());
        }

        return result;
    }

    private LogInfo addLog(String userName, Integer status, String content,String detailDesc,String  msg) {
        LogInfo logInfo = new LogInfo();
        logInfo.setUserName(userName);
        logInfo.setContent(content);
        logInfo.setCreateDate(new Date());
        logInfo.setStatus(status);
        logInfo.setDetailDesc(detailDesc);
        logInfo.setInfo(msg);
        return logInfo;
    }

    private String buildDetailDesc(HttpServletRequest request){
        //大数据拼接
        StringBuffer sb = new StringBuffer();
        //从请求中获取信息并且放到Map集合中去  request.getParameterMap();
        Map<String, String[]> parameterMap = request.getParameterMap();
        //迭代器遍历Map集合
        Iterator<Map.Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
        //判断下一行中是否含有数据
        while (iterator.hasNext()){
            //获取下一行的数据
            Map.Entry<String, String[]> next = iterator.next();
            //获取key值
            String key = next.getKey();
            //获取value值  value里可能有有好几个值
            String[] value = next.getValue();
            sb.append(key).append("=").append(StringUtils.join(value,",")).append(";");
        }
        return sb.toString();
    }

}
