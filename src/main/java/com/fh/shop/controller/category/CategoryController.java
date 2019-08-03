package com.fh.shop.controller.category;

import com.fh.shop.annotation.LogAnnotation;
import com.fh.shop.biz.category.ICategoryService;
import com.fh.shop.common.ServerResponse;
import com.fh.shop.po.category.CategoryInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <pre>包名称：com.fh.shop.category
 * 类名称：CategoryController
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/2614:18
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Resource(name="categoryService")
    private ICategoryService categoryService;
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 商品分类 三级联动 查询
     * 创建时间： 2019/6/30
     * @Param [id]
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("/findChildCategoryList")
    @ResponseBody
    @LogAnnotation(msg = "商品分类 三级联动 查询")
    public ServerResponse findChildCategoryList(Integer id){
        List<CategoryInfo> categoryInfo = categoryService.findChildCategoryList(id);
        return ServerResponse.success(categoryInfo);
    }

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 跳转分类树展示页面
     * 创建时间： 2019/6/30
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/toCategoryPage")
    public String toCategoryPage(){
        return "category/categorylist";
    }

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 分类树展示
     * 创建时间： 2019/6/30 
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("/queryCategory")
    @ResponseBody
    @LogAnnotation(msg = "分类树展示")
    public ServerResponse queryCategory(){
        List<CategoryInfo> categoryInfo =  categoryService.queryCategory();
        return ServerResponse.success(categoryInfo);
    }
    /**
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 商品分类增加节点
     * 创建时间： 2019/6/30
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("/addCategory")
    @ResponseBody
    @LogAnnotation(msg = "商品分类增加节点")
    public ServerResponse addCategory(CategoryInfo categoryInfo){
        categoryService.addCategory(categoryInfo);
        return ServerResponse.success();
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 商品分类修改节点
     * 创建时间： 2019/6/30
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("/updateCategory")
    @ResponseBody
    @LogAnnotation(msg = "商品分类修改节点")
    public ServerResponse updateCategory(CategoryInfo categoryInfo){
        categoryService.updateCategory(categoryInfo);
        return  ServerResponse.success();
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述：商品分类批量删除节点
     * 创建时间： 2019/6/30 
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("/deleteCategory")
    @ResponseBody
    @LogAnnotation(msg = "商品分类批量删除节点")
    public ServerResponse deleteCategory(@RequestParam("ids[]") List<Integer> ids ){
        categoryService.deleteCategory(ids);
        return ServerResponse.success();
    }

}
