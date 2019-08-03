package com.fh.shop.controller.area;

import com.fh.shop.annotation.LogAnnotation;
import com.fh.shop.biz.area.IAreaService;
import com.fh.shop.common.ServerResponse;
import com.fh.shop.po.area.AreaInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * <pre>包名称：com.fh.shop.controller.area
 * 类名称：AreaController
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/2321:53
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Controller
@RequestMapping("/area")
public class AreaController {

    @Resource
    private IAreaService areaService;
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 跳转地区管理页面
     * 创建时间： 2019/6/30
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("toAreaPage")
    @LogAnnotation(msg ="跳转地区管理页面")
    public ModelAndView toAreaPage(){
        ModelAndView mav = new ModelAndView("area/arealist");
        return mav;
    }

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 展示树
     * 创建时间： 2019/6/24
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
   @RequestMapping("queryArea")
    @ResponseBody
    @LogAnnotation(msg ="查询地区管理页面")
    public ServerResponse queryArea(){
        List<AreaInfo> areaList =  areaService.queryArea();
        return ServerResponse.success(areaList);
    }

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 增加树节点
     * 创建时间： 2019/6/24 
     * @Param [areaInfo]
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("addArea")
    @ResponseBody
    @LogAnnotation(msg ="地区管理增加树节点")
    public ServerResponse addArea(AreaInfo areaInfo){
        areaService.addArea(areaInfo);
        return ServerResponse.success();
    }
    /**
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 修改树节点
     * 创建时间： 2019/6/30
     * @Param [areaInfo]
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("updateArea")
    @ResponseBody
    @LogAnnotation(msg ="地区管理修改树节点")
    public ServerResponse updateArea(AreaInfo areaInfo){
        areaService.updateArea(areaInfo);
        return ServerResponse.success();
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 删除树节点
     * 创建时间： 2019/6/30
     * @Param [ids]
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("deleteArea")
    @ResponseBody
    @LogAnnotation(msg ="地区管理删除树节点")
    public ServerResponse deleteArea(@RequestParam("ids[]") List<Integer> ids){
        areaService.deleteArea(ids);
        return ServerResponse.success();
    }

}
