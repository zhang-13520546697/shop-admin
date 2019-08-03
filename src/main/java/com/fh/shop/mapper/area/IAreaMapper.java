package com.fh.shop.mapper.area;

import com.fh.shop.po.area.AreaInfo;

import java.util.List;

public interface IAreaMapper {


    List<AreaInfo> queryArea();

    void addArea(AreaInfo areaInfo);

    void updateArea(AreaInfo areaInfo);

    void deleteArea(List<Integer> ids);
}
