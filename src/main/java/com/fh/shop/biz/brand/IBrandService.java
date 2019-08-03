package com.fh.shop.biz.brand;

import com.fh.shop.common.Page;
import com.fh.shop.po.brand.BrandInfo;
import com.fh.shop.vo.brand.BrandVo;

import java.util.List;
import java.util.Map;

/**
 * <pre>包名称：com.fh.shop.biz.brand
 * 类名称：IBrandService
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/139:10
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public interface IBrandService {

    Map queryBrand(Page page,BrandInfo brandInfo);

    List<BrandInfo> findBrand();

    void addBrand(BrandInfo brandInfo);

    void deletebrand(Integer id);

    BrandVo getBrandById(Integer id);

    void updateBrand(BrandInfo brandInfo);

    void updateBrandIsRecommend(String isRecommend, int id);
}
