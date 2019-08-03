package com.fh.shop.controller;

import com.fh.shop.annotation.LogAnnotation;
import com.fh.shop.common.ResponseEnum;
import com.fh.shop.common.ServerResponse;
import com.fh.shop.util.CopyFile;
import com.fh.shop.util.FileUtil;
import com.fh.shop.util.OSSUtil;
import com.fh.shop.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <pre>包名称：com.fh.shop.controller
 * 类名称：FileController
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/257:31
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("uploadFile")
    @ResponseBody
    @LogAnnotation(msg ="文件上传导入Pdf")
    public ServerResponse uploadFile(@RequestParam MultipartFile importExcel) throws IOException {
        //把文件从客户端上传到服务端的工作  springMvc帮忙做
        //我只是用代码做了一个复制的操作
        String fileNewName = FileUtil.copyFile(importExcel.getInputStream(), importExcel.getOriginalFilename(), SystemConstant.UPLOAD_FILE_PATH);
        return ServerResponse.success(SystemConstant.UPLOAD_FILE_PATH+fileNewName);
    }
    /*
     *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 商品图片上传
     * 创建时间： 2019/6/14
     * @Param [path]
     * @return java.util.Map
     **/
    @RequestMapping("uploadProductImage")
    @ResponseBody
    @LogAnnotation(msg ="商品图片上传")
    public ServerResponse uploadProductImage(@RequestParam(value="productPhoto",required=false) MultipartFile productPhoto, HttpServletRequest request) throws IOException {
        String fileName =  productPhoto.getOriginalFilename();
        if(StringUtils.isEmpty(fileName)) {
            return ServerResponse.error(ResponseEnum.FILE_NAME_IS_NULL);
        }
        String filePath = OSSUtil.uploadFile(productPhoto.getInputStream(), fileName);
        return ServerResponse.success(filePath);
    }

    /*
     *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 品牌logo图片上传
     * 创建时间： 2019/6/18
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
    @RequestMapping("UploadBrandImage")
    @ResponseBody
    @LogAnnotation(msg ="品牌logo图片上传")
    public ServerResponse UploadBrandImage(@RequestParam(value = "logoUpload") CommonsMultipartFile logoUrl) throws IOException {
        String filenName = logoUrl.getOriginalFilename();
        if(StringUtils.isEmpty(filenName)){
            return ServerResponse.error(ResponseEnum.FILE_NAME_IS_NULL);
        }
        String logoImgUrl = OSSUtil.uploadFile(logoUrl.getInputStream(), filenName);
        return ServerResponse.success(logoImgUrl);
    }

}
