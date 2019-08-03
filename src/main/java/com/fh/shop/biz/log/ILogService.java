package com.fh.shop.biz.log;

import com.fh.shop.param.log.LogSearchParam;
import com.fh.shop.po.log.LogInfo;

import java.util.Map;

public interface ILogService {

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 添加日志信息
     * 创建时间： 2019/6/20
     * @Param [logInfo]
     * @return void
     **/
    void addLog(LogInfo logInfo);
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 条件 分页查询
     * 创建时间： 2019/6/20
     * @Param [logSearchParam]
     * @return java.util.Map
     **/
    Map queryLog(LogSearchParam logSearchParam);
}
