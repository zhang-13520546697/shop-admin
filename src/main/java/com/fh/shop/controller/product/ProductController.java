/** 
 * <pre>项目名称:train3-v1 
 * 文件名称:ProductController.java 
 * 包名:com.fh.shop.controller.product 
 * 创建日期:2019年6月4日下午9:02:53 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.controller.product;

import com.alibaba.dubbo.common.json.JSONObject;
import com.fh.shop.annotation.LogAnnotation;
import com.fh.shop.biz.product.IProductService;
import com.fh.shop.common.ServerResponse;
import com.fh.shop.param.product.ProductSearchParam;
import com.fh.shop.po.product.ProductInfo;
import com.fh.shop.util.*;
import com.fh.shop.vo.ProductVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/** 
 * <pre>项目名称：train3-v1    
 * 类名称：ProductController    
 * 类描述：    
 * 创建人：Mr.zhang    2424968072@qq.com    
 * 创建时间：2019年6月4日 下午9:02:53    
 * 修改人：Mr.zhang     2424968072@qq.com        
 * 修改时间：2019年6月4日 下午9:02:53    
 * 修改备注：       
 * @version </pre>    
 */

@Controller
@RequestMapping("product")
public class ProductController {
	
	@Resource(name="productService")
	private IProductService productService;

    //日志
    private static final Logger log = Logger.getLogger(ProductController.class);
	/**
	 * <pre>toAddPage 跳转新增页面  
	 * 创建人：Mr.zhang     2424968072@qq.com     
	 * 创建时间：2019年6月9日 下午8:27:01
	 * 修改人：Mr.zhang    2424968072@qq.com
	 * 修改时间：2019年6月9日 下午8:27:01
	 * 修改备注：
	 * @return</pre>
	 */
	@RequestMapping("toAddPage")
	@LogAnnotation(msg = "跳转新增页面")
	public String toAddPage(){
		return "product/addproduct";
	}
	/**
	 * <pre>addProduct 新增方法
	 * 创建人：Mr.zhang     2424968072@qq.com
	 * 创建时间：2019年6月9日 下午8:27:22
	 * 修改人：Mr.zhang    2424968072@qq.com
	 * 修改时间：2019年6月9日 下午8:27:22
	 * 修改备注：
	 * @param product
	 * @return</pre>
	 */
	@RequestMapping("addProductNow")
	@ResponseBody
	@LogAnnotation(msg = "添加商品信息")
	public ServerResponse addProduct(ProductInfo product){
		productService.addProduct(product);
		return ServerResponse.success();
	}
	/**
	 * <pre>toQueryPage 跳转查询页面
	 * 创建人：Mr.zhang     2424968072@qq.com     
	 * 创建时间：2019年6月9日 下午8:33:17    
	 * 修改人：Mr.zhang    2424968072@qq.com      
	 * 修改时间：2019年6月9日 下午8:33:17    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("toQueryPage")
	@LogAnnotation(msg ="跳转查询页面")
	public String toQueryPage(){
		return "product/productlist";
	}
	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 跳转展示热销商品页面
	 * 创建时间： 2019/7/11
	 * @Param []
	 * @return java.lang.String
	 **/
	@RequestMapping("toHotProduct")
	public String toHotProduct (){
		return "product/hotProduct";
	}
	/**
	 * <pre>queryProduct 查询页面 
	 * 创建人：Mr.zhang     2424968072@qq.com     
	 * 创建时间：2019年6月9日 下午8:28:03    
	 * 修改人：Mr.zhang    2424968072@qq.com      
	 * 修改时间：2019年6月9日 下午8:28:03    
	 * 修改备注： </pre>
	 */
	@RequestMapping("queryProduct")
	@ResponseBody
	@LogAnnotation(msg ="查询页面")
	public Map queryProduct(ProductSearchParam productSearchParam){
		Map productPageList = productService.queryProduct(productSearchParam);
			productPageList.put("code","200");
			productPageList.put("msg","ok");
		return productPageList;
	}


	@RequestMapping("deleteproduct")
	@ResponseBody
	@LogAnnotation(msg = "删除商品信息")
	public  ServerResponse deleteproduct(Integer id){
		productService.deleteproduct(id);
		return ServerResponse.success();
	}
	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述：
	 * 创建时间： 2019/6/12
	 * @Param [id]
	 * @return org.springframework.web.servlet.ModelAndView
	 **/
	@RequestMapping("getProductById")
	@ResponseBody
	@LogAnnotation(msg ="商品回显")
	public ServerResponse getProductById(Integer id){
		ProductVo productvo =  productService.getProductById(id);
		return ServerResponse.success(productvo);
	}
	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 修改商品信息
	 * 创建时间： 2019/6/13 
	 * @Param [product]
	 * @return java.util.Map
	 **/
	@RequestMapping("updateProduct")
	@ResponseBody
	@LogAnnotation(msg = "商品信息修改")
	public ServerResponse updateProduct(ProductInfo product){
		if(StringUtils.isEmpty(product.getProductPhoto())){
			//则没有上传新图片
			product.setProductPhoto(product.getOldProductPhoto());
		}else{
			//则需要删除老图片
			OSSUtil.deleteFile(product.getOldProductPhoto());
		}
		productService.updateProduct(product);
		return ServerResponse.success();
	}
	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 更新商品状态
	 * 创建时间： 2019/7/11
	 * @Param [id, status]
	 * @return com.fh.shop.common.ServerResponse
	 **/
	@RequestMapping("updateProductStatus")
	@LogAnnotation(msg = "更新商品状态")
	public @ResponseBody ServerResponse updateProductStatus(Integer id,Integer status){
		productService.updateProductStatus(id,status);
		return ServerResponse.success();
	}

	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 批量删除
	 * 创建时间： 2019/6/16 
	 * @Param []
	 * @return java.util.Map
	 **/
	@RequestMapping("deleteProductAll")
	@ResponseBody
	@LogAnnotation(msg ="商品批量删除")
	public ServerResponse deleteProductAll(@RequestParam("checkedIds[]") List<Integer>checkedIds){
			productService.deleteProductAll(checkedIds);
		return ServerResponse.success();
	}

	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 导出Pdf文件
	 * 创建时间： 2019/6/23
	 * @Param [productSearchParam]
	 * @return com.fh.shop.common.ServerResponse
	 **/
    @RequestMapping("exportPDF")
	@LogAnnotation(msg ="商品导出Pdf文件")
    public void pdf(ProductSearchParam productSearchParam,HttpServletResponse response ) throws IOException, TemplateException {
    	//根据条件动态查询数据
		List<ProductInfo> productList = productService.finAll(productSearchParam);
		//转换其为html
		Configuration configuration = new Configuration();
		//解决freemarker乱码问题
		configuration.setDefaultEncoding("utf-8");
		configuration.setClassForTemplateLoading(this.getClass(),"/template");
		Template template = configuration.getTemplate("/info.html");
		Map map = new HashMap();
		map.put("companyName","郑州飞狐教育");
		map.put("products",productList);
		map.put("createDate",DateAUtil.datastr(new Date(),DateAUtil.YMD));
		StringWriter stringWriter = new StringWriter();
		template.process(map,stringWriter);
		String htmlContent = stringWriter.toString();
		System.out.println(htmlContent);
		//转为pdf并下载
		FileUtil.pdfDownloadFile(response,htmlContent);
	}
	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 商品导出Word文件
	 * 创建时间： 2019/6/30
	 * @Param [request, response, productSearchParam]
	 * @return void
	 **/
	@RequestMapping("exportWord")
	@LogAnnotation(msg ="商品导出Word文件")
   public void exportWord(HttpServletRequest request, HttpServletResponse response,ProductSearchParam productSearchParam) throws IOException {
		//根据条件查询数据
		List<ProductInfo> productList = productService.finAll(productSearchParam);
		//将其转换为word数据格式
		File file = buildWord(productList);
		//下载
		System.out.println();
		FileUtil.downloadWordFile(request,response,file);
		//删除垃圾文件
		FileUtil.deleteFile(file);
	}

	private File buildWord(List<ProductInfo> productList) throws IOException {
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(this.getClass(),"/template");
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("product-template.xml");
		Map map = new HashMap();
		map.put("products",productList);
		File file =null;
		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		try {
			file = new File("e:/FeiH/train3/importWord"+UUID.randomUUID().toString()+".docx");
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out, "utf-8");//freemarker
			template.process(map,osw);
			osw.flush();//利于垃圾回收机制回收
		} catch (TemplateException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(osw != null){
				osw.close();
				osw = null;
			}
			if(out != null){
				out.close();
				out = null;
			}
		}
		return file;
	}

	/*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： Excel导出
     * 创建时间： 2019/6/23
     * @Param []
     * @return void
     **/
    @RequestMapping("exportExcel")
	@LogAnnotation(msg ="商品Excel导出")
    public void exportExcel(ProductSearchParam productSearchParam, HttpServletResponse response){
        //查询符合条件的数据
        List<ProductInfo> productList = productService.finAll(productSearchParam);
        //将其转化为worbook对象
		XSSFWorkbook workbook = buildWorkbook(productList);
		//下载
		FileUtil.xlsDownloadFile(response,workbook);
    }
    //将其转化为worbook对象
    private XSSFWorkbook buildWorkbook(List<ProductInfo> productList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("商品列表【"+productList.size()+"】");
        //创建大标题
		XSSFRow row = sheet.createRow(3);
		XSSFCell cell = row.createCell(7);
		cell.setCellValue("商品列表");
		//合并单元格
		CellRangeAddress cellRangeAddress = new CellRangeAddress(3, 5, 7, 10);
		sheet.addMergedRegion(cellRangeAddress);
		//创建样式
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//竖直居中
		//设置背景颜色
		cellStyle.setFillForegroundColor(HSSFColor.PINK.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//创建字体
		XSSFFont font = workbook.createFont();
		font.setBold(true);//字体加粗
		font.setFontHeightInPoints((short) 22);//设置字体大小
		font.setColor(IndexedColors.RED.index);//设置字体颜色
		cellStyle.setFont(font);//放到样式中

		//创建标题行
        buildTitleRow(sheet);
        //填写创建数据
        buildBody(productList, sheet,workbook);
		cell.setCellStyle(cellStyle);
        return workbook;
    }
    //填数据
    private void buildBody(List<ProductInfo> productList, XSSFSheet sheet, XSSFWorkbook wb) {
		XSSFCellStyle dateStyle = wb.createCellStyle();
		//日期格式
		dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
		for (int i = 0; i < productList.size(); i++) {
            ProductInfo productInfo = productList.get(i);
            XSSFRow row = sheet.createRow(i+7);
            row.createCell(7).setCellValue(productInfo.getProductName());
            row.createCell(8).setCellValue(productInfo.getBrand().getBrandName());
            row.createCell(9).setCellValue(productInfo.getProductPrice());
			XSSFCell cell = row.createCell(10);
			cell.setCellValue(productInfo.getCreateTime());
			cell.setCellStyle(dateStyle);
		}
    }
    //创表头
    private void buildTitleRow(XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(6);
        String[] titles = {"商品名","品牌名","价格","生产日期"};
        for (int i = 0; i < titles.length; i++) {
            row.createCell(i+7).setCellValue(titles[i]);
        }
    }

    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 导入Excel
     * 创建时间： 2019/6/25
     * @Param []
     * @return com.fh.shop.common.ServerResponse
     **/
	@RequestMapping("importExcel")
	@ResponseBody
	@LogAnnotation(msg ="商品导入Excel")
    private ServerResponse importExcel(String path) {
		productService.importExcel(path);
		return ServerResponse.success();
	}


	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： httpclient 调用远程接口 热销商品
	 * 创建时间： 2019/7/11 
	 * @Param []
	 * @return com.fh.shop.common.ServerResponse
	 **/
	@RequestMapping("findHotList")
	public @ResponseBody ServerResponse findHotList(){
		ServerResponse serverResponse =  productService.findHotList();
		return serverResponse;
	}
	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 清除缓存
	 * 创建时间： 2019/7/18
	 * @Param [str]
	 * @return com.fh.shop.common.ServerResponse
	 **/
	@RequestMapping("clearCache")
	public ServerResponse clearCache(@RequestParam("str[]") String[] str){
		RedisUtil.delArr(str);
		return ServerResponse.success();
	}




}
