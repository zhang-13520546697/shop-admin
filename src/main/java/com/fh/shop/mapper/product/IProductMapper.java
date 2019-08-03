/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:IProductMapper.java 
 * 包名:com.fh.shop.mapper.product 
 * 创建日期:2019年6月5日下午7:29:54 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.mapper.product;

import java.util.List;

import com.fh.shop.param.product.ProductSearchParam;

import com.fh.shop.po.product.ProductInfo;
import org.apache.ibatis.annotations.Param;

/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：IProductMapper    
 * 类描述：    
 * 创建人：Mr.zhang    2424968072@qq.com    
 * 创建时间：2019年6月5日 下午7:29:54    
 * 修改人：Mr.zhang     2424968072@qq.com        
 * 修改时间：2019年6月5日 下午7:29:54    
 * 修改备注：       
 * @version </pre>    
 */
public interface IProductMapper {


	void addProduct(ProductInfo product);

	Long queryCount(ProductSearchParam productSearchParam);

	List<ProductInfo> queryProductList(ProductSearchParam productSearchParam);


	void deleteproduct(Integer id);

	ProductInfo getProductById(Integer id);

	void updateProduct(ProductInfo product);

    void deleteProductAll(List<Integer> checkedIds);

    List<ProductInfo> finAll(ProductSearchParam productSearchParam);

	void addBatchProduct(List productList);

	void updateProductStatus(@Param("id") Integer id,@Param("status") Integer status);
}
