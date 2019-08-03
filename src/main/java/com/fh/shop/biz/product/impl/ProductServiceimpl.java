/**
 * <pre>项目名称:shop-admin-v1
 * 文件名称:ProductServiceimpl.java
 * 包名:com.fh.shop.biz.product.impl
 * 创建日期:2019年6月5日下午7:13:16
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre>
 */
package com.fh.shop.biz.product.impl;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.biz.product.IProductService;
import com.fh.shop.common.ServerResponse;
import com.fh.shop.mapper.brand.IBrandMapper;
import com.fh.shop.mapper.product.IProductMapper;
import com.fh.shop.param.product.ProductSearchParam;
import com.fh.shop.po.brand.BrandInfo;
import com.fh.shop.po.product.ProductInfo;
import com.fh.shop.util.DateAUtil;
import com.fh.shop.util.HttpClientUtil;
import com.fh.shop.util.SystemConstant;
import com.fh.shop.vo.ProductVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * <pre>项目名称：shop-admin-v1
 * 类名称：ProductServiceimpl
 * 类描述：
 * 创建人：Mr.zhang    2424968072@qq.com
 * 创建时间：2019年6月5日 下午7:13:16
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改时间：2019年6月5日 下午7:13:16
 * 修改备注：
 * @version </pre>
 */
@Service("productService")
public class ProductServiceimpl implements IProductService{

	@Resource
	public IProductMapper productMapper;

	@Resource
	public IBrandMapper brandMapper;

	@Value("${api.product.hot.list}")
	private String url;

	/**
	 * code : 200
	 * msg : ok
	 * data : [{"id":141,"productName":"ZTE blade A2  ","productPrice":1199,"productPhoto":"/userimg/ec974f40f1924863b0e0ccea11510518.jpg"},{"id":136,"productName":"华为p30","productPrice":4500,"productPhoto":"/userimg/d119f70471f3481a97324139b443fb1f.jpg"},{"id":135,"productName":"联想5A","productPrice":21,"productPhoto":"/userimg/c33882d4fa614d788ae467c034ea7876.jpg"},{"id":130,"productName":"iPhone XS Max","productPrice":7000,"productPhoto":"/userimg/5f3d2c15ceff4f328383f5f9fbdffafd.jpg"},{"id":129,"productName":"Haier500","productPrice":30,"productPhoto":"/userimg/003f43441ec64bcd9b297ac11451bca5.jpg"}]
	 */

	private int code;
	private String msg;
	private List<DataBean> data;

	/*
	 *
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述：新增
	 * 创建时间： 2019/6/12
	 * @Param [product]
	 * @return void
	 **/
	@Override
	public void addProduct(ProductInfo product) {
		productMapper.addProduct(product);
		//throw new RuntimeException("================");
	}
	/*
	 *
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 查询商品
	 * 创建时间： 2019/6/13
	 * @Param [productSearchParam]
	 * @return java.util.Map
	 **/
	@Override
	public Map queryProduct(ProductSearchParam productSearchParam) {
		// 获取总条数
		Long count =  productMapper.queryCount(productSearchParam);
		//获取分页数据
		List<ProductInfo> list =  productMapper.queryProductList(productSearchParam);
		//数据转换 再将转换后的数据放到map里
		List<Map> productList = convertListMap(list);
		// 数据处理
		Map map = buildData(productSearchParam, count, productList);
		return map;

	}


	private Map buildData(ProductSearchParam productSearchParam, Long count, List<Map> productList) {
		Map map = new HashMap();
		map.put("draw", productSearchParam.getDraw());
		map.put("recordsTotal", count);
		map.put("recordsFiltered",count);
		map.put("data", productList);
		return map;
	}

	private List<Map> convertListMap(List<ProductInfo> list) {
		List <Map> productList = new ArrayList<>();
		for (ProductInfo pro : list) {
			Map item = new HashMap();
			item.put("id",pro.getId());
			item.put("productName",pro.getProductName());
			item.put("productPrice",pro.getProductPrice());
			item.put("createTime",DateAUtil.datastr(pro.getCreateTime(), DateAUtil.YMD));
			item.put("productPhoto",pro.getProductPhoto());
			item.put("brandName",pro.getBrand().getBrandName());
			item.put("categoryName",pro.getCategoryName());
			item.put("isHot",pro.getIsHot());
			item.put("status",pro.getStatus());
			productList.add(item);
		}
		return productList;
	}

	/*
	 *
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 删除
	 * 创建时间： 2019/6/13
	 * @Param [id]
	 * @return void
	 **/
	@Override
	public void deleteproduct(Integer id) {
		productMapper.deleteproduct(id);
	}
	/*
	 *
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述：回显
	 * 创建时间： 2019/6/13
	 * @Param [id]
	 * @return com.fh.shop.vo.ProductVo
	 **/
	@Override
	public ProductVo getProductById(Integer id) {
		ProductInfo product = productMapper.getProductById(id);
		//po转vo
		ProductVo productvo = convertProductVo(product);
		return productvo;
	}
	private ProductVo convertProductVo(ProductInfo product) {
		ProductVo productvo = new ProductVo();
		productvo.setId(product.getId());
		productvo.setProductName(product.getProductName());
		productvo.setProductPrice(product.getProductPrice());
		productvo.setCreateTime(DateAUtil.datastr(product.getCreateTime(),DateAUtil.YMD));
		productvo.setBrandId(product.getBrand().getId());
		productvo.setProductPhoto(product.getProductPhoto());
		productvo.setGc1(product.getGc1());
		productvo.setGc2(product.getGc2());
		productvo.setGc3(product.getGc3());
		productvo.setCategoryName(product.getCategoryName());
		productvo.setIsHot(product.getIsHot());
		return productvo;
	}
	/*
	 *
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 修改
	 * 创建时间： 2019/6/13
	 * @Param [product]
	 * @return void
	 **/
	@Override
	public void updateProduct(ProductInfo product) {
		productMapper.updateProduct(product);
	}
	
	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 批量删除
	 * 创建时间： 2019/6/16 
	 * @Param [checkedIds]
	 * @return void
	 **/
	@Override
	public void deleteProductAll(List<Integer> checkedIds) {
		productMapper.deleteProductAll(checkedIds);
	}

	@Override
	public List<ProductInfo> finAll(ProductSearchParam productSearchParam) {
		List<ProductInfo> list = productMapper.finAll(productSearchParam);
		return list;
	}

	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： Excel导入
	 * 创建时间： 2019/6/25
	 * @Param [path]
	 * @return void
	 **/
    @Override
    public void importExcel(String path) {
		//查询品牌列表
		List<BrandInfo> brandList = brandMapper.findBrand();
		//将excel文件转为workbook   拿Workbook new 值  即解决了版本问题
		Workbook workbook = buildWorkbook(path);
		//添加数据库
		buildAddProduct(brandList, workbook,path);
    }
    /*
    *
     * 创建人： Mr.zhang  2424968072@qq.com
     * 方法描述： 修改商品状态
     * 创建时间： 2019/7/11
     * @Param [id, status]
     * @return void
     **/
	@Override
	public void updateProductStatus(Integer id, Integer status) {
		productMapper.updateProductStatus(id,status);
	}

    @Override
    public ServerResponse findHotList() {
		//调用远程接口
		String result = HttpClientUtil.sendGet(url);
		if(StringUtils.isNotEmpty(result)){
			return  JSONObject.parseObject(result,ServerResponse.class);
		}else{
			return ServerResponse.error();
		}
    }

    private void buildAddProduct(List<BrandInfo> brandList, Workbook workbook,String path) {
		Sheet sheet = workbook.getSheetAt(0);
		int firstRowNum = sheet.getFirstRowNum();//文件数据开始下标
		int lastRowNum = sheet.getLastRowNum();//文件数据结束下标
		//第一行是表头 不需要填入数据库
		List productList = new ArrayList();
		for (int i = firstRowNum+1; i <= lastRowNum; i++) {
			//获取出文件里的id并把他赋到对象中去
			ProductInfo productInfo = converProduct(sheet,i,brandList);
			productList.add(productInfo);
			//分段提交数据
			if (productList.size()==SystemConstant.BATCH_SIZE){
				//批量提交
				productMapper.addBatchProduct(productList);
				productList = new ArrayList();
			}
		}
		//处理末尾数据
		if(productList.size()>0){
			productMapper.addBatchProduct(productList);
		}
		//删除文件 释放硬盘资源
		deleteFile(path);
	}
	private void deleteFile(String path) {
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
	}

	private Workbook buildWorkbook(String path) {
		Workbook workbook = null;
		try {
			if(path.endsWith("xlsx")){
				workbook = new XSSFWorkbook(new FileInputStream(path));
			}else{
				workbook = new HSSFWorkbook(new FileInputStream(path));
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return workbook;
	}

	private ProductInfo converProduct(Sheet sheet, int i,List<BrandInfo> brandList) {
		Row row = sheet.getRow(i);
		String productName = row.getCell(0).getStringCellValue();
		double price = row.getCell(1).getNumericCellValue();
		String brandName = row.getCell(2).getStringCellValue();
		//根据品牌名获取品牌id
		//将文件中的品牌名跟数据库的品牌名作比较
		int brandId = buildBrandId(brandList, brandName);

		Date createDate = row.getCell(3).getDateCellValue();
		//转换po
		ProductInfo product = new ProductInfo();
		product.setProductName(productName);
		product.setProductPrice((float) price);
		product.getBrand().setId(brandId);
		product.setCreateTime(createDate);
		return product;
	}

	private int buildBrandId(List<BrandInfo> brandList, String brandName) {
		for (BrandInfo brand : brandList) {
			if(brand.getBrandName().equals(brandName)){
				return brand.getId();
			}
		}
		BrandInfo brand = new BrandInfo();
		//查询没有匹配的品牌 先插入品牌 在获取品牌id
		brand.setBrandName(brandName);
		brandMapper.addBrand(brand);
		Integer id = brand.getId();//获取后插入品牌的id
		brandList.add(brand);//把这些品牌放入list中 可直接进行比较 避免再次查询数据库
		return id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * id : 141
		 * productName : ZTE blade A2
		 * productPrice : 1199
		 * productPhoto : /userimg/ec974f40f1924863b0e0ccea11510518.jpg
		 */

		private int id;
		private String productName;
		private int productPrice;
		private String productPhoto;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public int getProductPrice() {
			return productPrice;
		}

		public void setProductPrice(int productPrice) {
			this.productPrice = productPrice;
		}

		public String getProductPhoto() {
			return productPhoto;
		}

		public void setProductPhoto(String productPhoto) {
			this.productPhoto = productPhoto;
		}
	}
}
