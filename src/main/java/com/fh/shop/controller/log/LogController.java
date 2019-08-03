package com.fh.shop.controller.log;

import com.fh.shop.biz.log.ILogService;
import com.fh.shop.common.Page;
import com.fh.shop.param.log.LogSearchParam;
import com.fh.shop.po.log.LogInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <pre>包名称：com.fh.shop.controller.log
 * 类名称：LogController
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/207:57
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Controller
@RequestMapping("log")
public class LogController {

    @Resource(name="logService")
    private ILogService logService;

    /*
     *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 跳转日志信息列表
     * 创建时间： 2019/6/20
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("toLogPage")
    public String toLogPage(){
        return "log/loglist";
    }

    /*
     *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 分页 条件查询
     * 创建时间： 2019/6/20
     * @Param [page, logInfo]
     * @return java.util.Map
     **/
    @RequestMapping("queryLog")
    @ResponseBody
    public Map queryLog(LogSearchParam logSearchParam){
        Map loglist = logService.queryLog(logSearchParam);
        loglist.put("code","200");
        loglist.put("msg","ok");
        return loglist;
    }

}
