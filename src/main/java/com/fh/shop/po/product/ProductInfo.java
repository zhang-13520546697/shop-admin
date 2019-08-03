/**
 * <pre>项目名称:shop-admin-v1
 * 文件名称:ProductInfo.java
 * 包名:com.fh.shop.po.product
 * 创建日期:2019年6月4日下午9:13:33
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre>
 */
package com.fh.shop.po.product;

import java.io.Serializable;
import java.util.Date;

import com.fh.shop.po.brand.BrandInfo;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <pre>项目名称：shop-admin-v1
 * 类名称：ProductInfo
 * 类描述：
 * 创建人：Mr.zhang    2424968072@qq.com
 * 创建时间：2019年6月4日 下午9:13:33
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改时间：2019年6月4日 下午9:13:33
 * 修改备注：
 * @version </pre>
 */
public class ProductInfo implements Serializable{

	private static final long serialVersionUID = 5875782390497648558L;

	private Integer id ;

	private String productName;

	private Float productPrice;

	private Integer isHot;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;

	private BrandInfo brand = new BrandInfo();

	private String productPhoto;

	private String oldProductPhoto;

	private Integer gc1;

	private Integer gc2;

	private Integer gc3;

	private String categoryName;

	private Integer status;

	public String getOldProductPhoto() {
		return oldProductPhoto;
	}

	public void setOldProductPhoto(String oldProductPhoto) {
		this.oldProductPhoto = oldProductPhoto;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getGc1() {
		return gc1;
	}

	public void setGc1(Integer gc1) {
		this.gc1 = gc1;
	}

	public Integer getGc2() {
		return gc2;
	}

	public void setGc2(Integer gc2) {
		this.gc2 = gc2;
	}

	public Integer getGc3() {
		return gc3;
	}

	public void setGc3(Integer gc3) {
		this.gc3 = gc3;
	}

	public String getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
	}

	public BrandInfo getBrand() {
		return brand;
	}

	public void setBrand(BrandInfo brand) {
		this.brand = brand;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
