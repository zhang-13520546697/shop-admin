package com.fh.shop.controller.brand;

import com.fh.shop.annotation.IgnoreCheck;
import com.fh.shop.annotation.LogAnnotation;
import com.fh.shop.biz.brand.IBrandService;
import com.fh.shop.common.Page;
import com.fh.shop.common.ServerResponse;
import com.fh.shop.po.brand.BrandInfo;
import com.fh.shop.util.CopyFile;
import com.fh.shop.vo.brand.BrandVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>包名称：com.fh.shop.controller.brand
 * 类名称：BrandController
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/139:07
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Controller
@RequestMapping("brand")
public class BrandController {

    @Resource(name = "brandService")
    private IBrandService brandService;


    /*
     *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述：  跳转查询页面
     * 创建时间： 2019/6/13
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("toBrandPage")
    @LogAnnotation(msg = "跳转品牌查询页面")
    public String toBrandPage() {
        return "brand/brandlist";
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： toAddBrandPage
     * 创建时间： 2019/7/10 
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("toAddBrandPage")
    @LogAnnotation(msg = "跳转品牌添加页面")
    public String toAddBrandPage() {
        return "brand/addbrand";
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 分页查询
     * 创建时间： 2019/6/18
     * @Param [page]
     * @return java.util.Map
     **/
    @RequestMapping("queryBrand")
    @ResponseBody
    @LogAnnotation(msg = "查询品牌页面")
    public Map queryBrand(Page page,BrandInfo brandInfo) {
        Map brandPageList = brandService.queryBrand(page,brandInfo);
        brandPageList.put("code",200);
        brandPageList.put("msg","ok");
        return brandPageList;
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 普通查询 动态品牌下拉框
     * 创建时间： 2019/6/18 
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("findBrand")
    @ResponseBody
    @LogAnnotation(msg = "普通查询 动态品牌下拉框")
    public ServerResponse findBrand() {
        List<BrandInfo> branList = brandService.findBrand();
        return ServerResponse.success(branList);
    }
    

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 新增方法
     * 创建时间： 2019/6/18 
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("addBrand")
    @ResponseBody
    @LogAnnotation(msg = "品牌新增")
    public ServerResponse addBrand(BrandInfo brandInfo){
        brandService.addBrand(brandInfo);
        return ServerResponse.success();
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 删除
     * 创建时间： 2019/6/27 
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("deletebrand")
    @ResponseBody
    @LogAnnotation(msg = "品牌删除")
    public ServerResponse deletebrand(Integer id){
        brandService.deletebrand(id);
        return ServerResponse.success();
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 回显数据
     * 创建时间： 2019/6/27 
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("getBrandById")
    @ResponseBody
    @LogAnnotation(msg = "品牌回显")
    public ServerResponse getBrandById(Integer id){
        BrandVo brand =  brandService.getBrandById(id);
        return ServerResponse.success(brand);
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 品牌修改
     * 创建时间： 2019/6/30
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("updateBrand")
    @ResponseBody
    @LogAnnotation(msg = "品牌修改")
    public ServerResponse updateBrand(BrandInfo brandInfo){
        brandService.updateBrand(brandInfo);
        return ServerResponse.success();
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 推荐品牌
     * 创建时间： 2019/7/10 
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("updateBrandIsRecommend")
    @ResponseBody
    @LogAnnotation(msg = "推荐品牌")
    public ServerResponse updateBrandIsRecommend(String isRecommend ,int id){
        brandService.updateBrandIsRecommend(isRecommend,id);
        return ServerResponse.success();
    }


}
