package com.fh.shop.biz.rubbish;

import com.fh.shop.param.product.ProductSearchParam;

import java.util.List;
import java.util.Map;

public interface IRubbishService {


    Map queryProductRubbish(ProductSearchParam productSearchParam);

    void restoretAll(List<Integer> checkedIds);

    void deleteProductAll(List<Integer> checkedIds);
}
