package com.fh.shop.mapper.brand;

import com.fh.shop.common.Page;
import com.fh.shop.po.brand.BrandInfo;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface IBrandMapper {

    

    List<BrandInfo> queryBrandList(@Param("page") Page page, @Param("brandInfo")BrandInfo brandInfo);

    Long queryCount(BrandInfo brandInfo);

    List<BrandInfo> findBrand();

    void addBrand(BrandInfo brandInfo);

    void deletebrand(Integer id);

    BrandInfo getBrandById(Integer id);

    void updateBrand(BrandInfo brandInfo);


    void updateBrandIsRecommend(@Param("isRecommend") String isRecommend,@Param("id") int id);
}
