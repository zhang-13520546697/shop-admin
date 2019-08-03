package com.fh.shop.biz.area.impl;

import com.fh.shop.biz.area.IAreaService;
import com.fh.shop.mapper.area.IAreaMapper;
import com.fh.shop.po.area.AreaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <pre>包名称：com.fh.shop.biz.area.impl
 * 类名称：AreaServiceimpl
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/2321:56
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Service
public class AreaServiceimpl implements IAreaService {

    @Autowired
    private IAreaMapper areaMapper;
    @Override
    public void addArea(AreaInfo areaInfo) {
        areaMapper.addArea(areaInfo);
    }

    @Override
    public void updateArea(AreaInfo areaInfo) {
        areaMapper.updateArea(areaInfo);
    }

    @Override
    public void deleteArea(List<Integer> ids) {
        areaMapper.deleteArea(ids);
    }

    @Override
    public List<AreaInfo> queryArea() {
        List<AreaInfo> areaInfos = areaMapper.queryArea();
        return areaInfos;
    }
}
