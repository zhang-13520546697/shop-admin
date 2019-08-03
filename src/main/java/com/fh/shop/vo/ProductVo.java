package com.fh.shop.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>包名称：com.fh.shop.vo
 * 类名称：ProductVo
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/131:46
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class ProductVo implements Serializable {
    private static final long serialVersionUID = 2487518844597756210L;

    private Integer id ;

    private String productName;

    private Float productPrice;

    private String createTime;

    private Integer brandId;

    private String  productPhoto;

    private Integer gc1;

    private Integer gc2;

    private Integer gc3;

    private String categoryName;

    private Integer isHot ;

    private Integer status;

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

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
