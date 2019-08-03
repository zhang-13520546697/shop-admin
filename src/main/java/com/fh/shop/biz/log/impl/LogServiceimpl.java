package com.fh.shop.biz.log.impl;

import com.fh.shop.biz.log.ILogService;
import com.fh.shop.biz.user.ILoginService;
import com.fh.shop.mapper.log.ILogMapper;
import com.fh.shop.param.log.LogSearchParam;
import com.fh.shop.po.log.LogInfo;
import com.fh.shop.po.user.UserInfo;
import com.fh.shop.util.DateAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>包名称：com.fh.shop.biz.log.impl
 * 类名称：LogServiceimpl
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/207:42
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Service("logService")
public class LogServiceimpl implements ILogService {

    @Autowired
    private ILogMapper logMapper;
    /*
     *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 日志添加
     * 创建时间： 2019/6/20
     * @Param [logInfo]
     * @return void
     **/
    @Override
    public void addLog(LogInfo logInfo) {
        logMapper.addLog(logInfo);
    }
    /*
     *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 分页 条件查询
     * 创建时间： 2019/6/20
     * @Param [logSearchParam]
     * @return java.util.Map
     **/
    @Override
    public Map queryLog(LogSearchParam logSearchParam) {
        //获取总条数
        long count = logMapper.queryCount(logSearchParam);
        //获取分页信息
        List<LogInfo> logInfoList = logMapper.queryLog(logSearchParam);
        //数据转化 并放到list里
        List<Map> loglist = buildLogList(logInfoList);
        //将转化后的数据返回前台
        Map itme =buildMap(logSearchParam, count, loglist);
        return itme;
    }

    private Map buildMap(LogSearchParam logSearchParam, long count, List<Map> loglist) {
        Map itme = new HashMap();
        itme.put("draw",logSearchParam.getDraw());
        itme.put("recordsTotal",count);
        itme.put("recordsFiltered",count);
        itme.put("data",loglist);
        return itme;
    }

    private List<Map> buildLogList(List<LogInfo> logInfoList) {
        List<Map> loglist = new ArrayList<>();
        for(LogInfo logs: logInfoList){
            Map map = new HashMap();
            map.put("id",logs.getId());
            map.put("userName",logs.getUserName());
            map.put("content",logs.getContent());
            map.put("detailDesc",logs.getDetailDesc());
            map.put("status",logs.getStatus());
            map.put("info",logs.getInfo());
            map.put("createDate",DateAUtil.datastr(logs.getCreateDate(),DateAUtil.FULL_TIME));
            loglist.add(map);
        }
        return loglist;
    }


}
