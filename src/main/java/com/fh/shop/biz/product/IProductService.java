/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:IProductService.java 
 * 包名:com.fh.shop.biz.product 
 * 创建日期:2019年6月5日下午7:12:51 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.biz.product;

import java.util.List;
import java.util.Map;

import com.fh.shop.common.ServerResponse;
import com.fh.shop.param.product.ProductSearchParam;
import com.fh.shop.po.product.ProductInfo;
import com.fh.shop.vo.ProductVo;

/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：IProductService    
 * 类描述：    
 * 创建人：Mr.zhang    2424968072@qq.com    
 * 创建时间：2019年6月5日 下午7:12:51    
 * 修改人：Mr.zhang     2424968072@qq.com        
 * 修改时间：2019年6月5日 下午7:12:51    
 * 修改备注：       
 * @version </pre>    
 */
public interface IProductService {

	/** <pre>addProduct 添加方法
	 * 创建人：Mr.zhang     2424968072@qq.com     
	 * 创建时间：2019年6月5日 下午7:42:54    
	 * 修改人：Mr.zhang    2424968072@qq.com      
	 * 修改时间：2019年6月5日 下午7:42:54    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	void addProduct(ProductInfo product);
	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 删除
	 * 创建时间： 2019/6/12 
	 * @Param [id]
	 * @return void
	 **/
	void deleteproduct(Integer id);
	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 回显
	 * 创建时间： 2019/6/12 
	 * @Param [id]
	 * @return com.fh.shop.po.product.ProductInfo
	 **/
	ProductVo getProductById(Integer id);
	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述： 修改商品信息
	 * 创建时间： 2019/6/12 
	 * @Param [product]
	 * @return void
	 **/
	void updateProduct(ProductInfo product);
	/*
	*
	 * 创建人： Mr.zhang  2424968072@qq.com
	 * 方法描述：查询商品信息
	 * 创建时间： 2019/6/12 
	 * @Param [productSearchParam]
	 * @return java.util.Map
	 **/
	Map queryProduct(ProductSearchParam productSearchParam);

    void deleteProductAll(List<Integer> checkedIds);

    List<ProductInfo> finAll(ProductSearchParam productSearchParam);

    void importExcel(String path);


	void updateProductStatus(Integer id, Integer status);

	ServerResponse findHotList();
}
