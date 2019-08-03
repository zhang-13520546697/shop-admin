package com.fh.shop.biz.rubbish.impl;

import com.fh.shop.biz.rubbish.IRubbishService;
import com.fh.shop.mapper.rubbish.RubbishMapper;
import com.fh.shop.param.product.ProductSearchParam;
import com.fh.shop.po.product.ProductInfo;
import com.fh.shop.util.DateAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>包名称：com.fh.shop.biz.rubbish.impl
 * 类名称：RubbishServiceimpl
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/515:48
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Service("rubbishService")
public class RubbishServiceimpl implements IRubbishService {

    @Autowired
    private RubbishMapper rubbishMapper;


    @Override
    public Map queryProductRubbish(ProductSearchParam productSearchParam) {
        //查询总条数
        Long count =  rubbishMapper.queryCount(productSearchParam);
        //查询所有数据
        List<ProductInfo> rubbistlist =  rubbishMapper.queryProductRubbishList(productSearchParam);
        //数据转换 响应前台视图
        List<Map> rubbistMapList = buildConvertMapList(rubbistlist);
        //将Map类型数据返回 过去
        Map map = buildData(productSearchParam, count, rubbistMapList);
        return map;
    }

    @Override
    public void restoretAll(List<Integer> checkedIds) {
        rubbishMapper.restoretAll(checkedIds);
    }

    @Override
    public void deleteProductAll(List<Integer> checkedIds) {
        rubbishMapper.deleteProductAll(checkedIds);
    }

    private Map buildData(ProductSearchParam productSearchParam, Long count, List<Map> rubbistMapList) {
        Map map = new HashMap();
        map.put("darw",productSearchParam.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data",rubbistMapList);
        return map;
    }
    private List<Map> buildConvertMapList(List<ProductInfo> rubbistlist) {
        List<Map> rubbistMapList = new ArrayList<>();
        for (ProductInfo product : rubbistlist) {
            Map item = new HashMap();
            item.put("id",product.getId());
            item.put("productName",product.getProductName());
            item.put("productPrice",product.getProductPrice());
            item.put("createTime",DateAUtil.datastr(product.getCreateTime(), DateAUtil.YMD));
            item.put("productPhoto",product.getProductPhoto());
            item.put("brandName",product.getBrand().getBrandName());
            item.put("categoryName",product.getCategoryName());
            item.put("isHot",product.getIsHot());
            item.put("status",product.getStatus());
            rubbistMapList.add(item);
        }
        return rubbistMapList;
    }
}
