package com.fh.shop.controller.rubbish;

import com.fh.shop.annotation.LogAnnotation;
import com.fh.shop.biz.rubbish.IRubbishService;
import com.fh.shop.common.ServerResponse;
import com.fh.shop.param.product.ProductSearchParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <pre>包名称：com.fh.shop.controller.rubbish
 * 类名称：RubbishController
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/7/515:46
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Controller
@RequestMapping("/rubbish")
public class RubbishController {
    @Resource(name="rubbishService")
    private IRubbishService rubbishService;


    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 跳转垃圾回收站页面
     * 创建时间： 2019/7/6 
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("toRubbishPage")
    public String toRubbishPage(){
        return "rubbish/rubbishlist";
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 垃圾回收站页面查询
     * 创建时间： 2019/7/6
     * @Param
     * @return
     **/
    @RequestMapping("queryProductRubbish")
    @ResponseBody
    @LogAnnotation(msg="垃圾回收站页面查询")
    public Map queryProductRubbish(ProductSearchParam productSearchParam){
        Map productRubbisList =  rubbishService.queryProductRubbish(productSearchParam);
        productRubbisList.put("code","200");
        productRubbisList.put("mag","ok");
        return productRubbisList;
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 还原数据
     * 创建时间： 2019/7/6
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("/restoretAll")
    @ResponseBody
    @LogAnnotation(msg="还原数据")
    public ServerResponse restoretAll(@RequestParam("checkedIds[]") List<Integer> checkedIds){
        rubbishService.restoretAll(checkedIds);
        return ServerResponse.success();
    }

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 批量删除
     * 创建时间： 2019/7/6
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("deleteProductAll")
    @ResponseBody
    @LogAnnotation(msg="批量删除")
    public ServerResponse deleteProductAll(@RequestParam("checkedIds[]") List<Integer> checkedIds ){
        rubbishService.deleteProductAll(checkedIds);
        return ServerResponse.success();
    }



}
