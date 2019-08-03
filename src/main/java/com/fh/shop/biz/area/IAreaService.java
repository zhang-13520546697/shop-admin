package com.fh.shop.biz.area;

import com.fh.shop.po.area.AreaInfo;

import java.util.List;

public interface IAreaService {


    void addArea(AreaInfo areaInfo);

    void updateArea(AreaInfo areaInfo);


    void deleteArea(List<Integer> ids);


    List<AreaInfo> queryArea();
}
