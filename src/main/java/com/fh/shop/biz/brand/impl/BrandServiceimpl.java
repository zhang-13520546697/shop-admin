package com.fh.shop.biz.brand.impl;


import com.alibaba.fastjson.JSONObject;
import com.fh.shop.biz.brand.IBrandService;
import com.fh.shop.common.Page;
import com.fh.shop.mapper.brand.IBrandMapper;
import com.fh.shop.po.brand.BrandInfo;
import com.fh.shop.util.DateAUtil;
import com.fh.shop.util.RedisPool;
import com.fh.shop.util.RedisUtil;
import com.fh.shop.vo.brand.BrandVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>包名称：com.fh.shop.biz.brand.impl
 * 类名称：BrandServiceimpl
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/139:11
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Service("brandService")
public class BrandServiceimpl implements IBrandService{

    @Resource
    public IBrandMapper brandMapper;

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 分页查询
     * 创建时间： 2019/6/18
     * @Param [page]
     * @return java.util.Map
     **/
    @Override
    public Map queryBrand(Page page,BrandInfo brandInfo) {
        // 获取总条数
        Long count = brandMapper.queryCount(brandInfo);
        //获取分页数据
        List<BrandInfo> list = brandMapper.queryBrandList(page,brandInfo);
        //数据转换 再将转换后的数据放到map里
        List<Map> brandList = brandListMap(list);
        // 数据处理
        Map map = brandMap(page, count, brandList);
        return map;
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 普通查询
     * 创建时间： 2019/6/18 
     * @Param []
     * @return java.util.List<com.fh.shop.po.brand.BrandInfo>
     **/
    @Override
    public List<BrandInfo> findBrand() {
        String brandListStr = RedisUtil.get("brandList");
        if(StringUtils.isNotBlank(brandListStr)){
            List<BrandInfo> brandInfo = JSONObject.parseArray(brandListStr, BrandInfo.class);
            return brandInfo;
        }
        //查数据库
        List<BrandInfo> brand = brandMapper.findBrand();
        //转换为json格式的字符串
        String brandListJson = JSONObject.toJSONString(brand);
        //存缓存
        RedisUtil.set("brandList",brandListJson);
        //返回前台
        return brand;
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 品牌新增
     * 创建时间： 2019/6/18 
     * @Param [brandInfo]
     * @return void
     **/
    @Override
    public void addBrand(BrandInfo brandInfo) {
        brandMapper.addBrand(brandInfo);
    }

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 删除数据
     * 创建时间： 2019/6/27
     * @Param [id]
     * @return void
     **/
    @Override
    public void deletebrand(Integer id) {
        brandMapper.deletebrand(id);
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 回显数据
     * 创建时间： 2019/6/27
     * @Param [id]
     * @return com.fh.shop.po.brand.BrandInfo
     **/
    @Override
    public BrandVo getBrandById(Integer id) {
        BrandInfo brand =  brandMapper.getBrandById(id);
        BrandVo brandVo = buildBrandVo(brand);
        return brandVo;
    }

    @Override
    public void updateBrand(BrandInfo brandInfo) {
        brandMapper.updateBrand(brandInfo);
    }

    @Override
    public void updateBrandIsRecommend(String isRecommend, int id) {
        brandMapper.updateBrandIsRecommend(isRecommend,id);
    }

    private BrandVo buildBrandVo(BrandInfo brand) {
        BrandVo brandVo = new BrandVo();
        //po转vo
        brandVo.setId(brand.getId());
        brandVo.setBrandName(brand.getBrandName());
        brandVo.setCreateDate(DateAUtil.datastr(brand.getCreateDate(),DateAUtil.YMD));
        brandVo.setLogoUrl(brand.getLogoUrl());
        brandVo.setSort(brand.getSort());
        return brandVo;
    }

    private Map brandMap(Page page,Long count, List<Map> brandList) {
        Map map = new HashMap();
        map.put("draw", page.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", brandList);
        return map;
    }

    private List<Map> brandListMap(List<BrandInfo> list) {
        List <Map> brandList = new ArrayList<>();
        for (BrandInfo brand : list){
            Map ban = new HashMap();
            ban.put("id",brand.getId());
            ban.put("brandName",brand.getBrandName());
            ban.put("createDate",DateAUtil.datastr(brand.getCreateDate(),DateAUtil.Y));
            ban.put("logoUrl",brand.getLogoUrl());
            ban.put("sort",brand.getSort());
            ban.put("isRecommend",brand.getIsRecommend());
            brandList.add(ban);
        }
        return brandList;
    }
}
