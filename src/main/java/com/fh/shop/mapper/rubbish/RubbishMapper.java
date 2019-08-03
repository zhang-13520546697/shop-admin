package com.fh.shop.mapper.rubbish;

import com.fh.shop.param.product.ProductSearchParam;
import com.fh.shop.po.product.ProductInfo;

import java.util.List;

public interface RubbishMapper {


    Long queryCount(ProductSearchParam productSearchParam);

    List<ProductInfo> queryProductRubbishList(ProductSearchParam productSearchParam);

    void restoretAll(List<Integer> checkedIds);

    void deleteProductAll(List<Integer> checkedIds);
}
