package com.fh.shop.param.product;

import com.fh.shop.common.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProductSearchParam extends Page {
    private  String productName;

    private Float minPrice;

    private Float maxPrice;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private Integer brandId;

    private Integer gc1;

    private Integer gc2;

    private Integer gc3;

    private String categoryName;

    private Integer isHot;

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

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
