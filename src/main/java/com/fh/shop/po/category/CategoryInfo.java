package com.fh.shop.po.category;

import java.io.Serializable;

/**
 * <pre>包名称：com.fh.shop.po.category
 * 类名称：CategoryInfo
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/2614:15
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class CategoryInfo implements Serializable {
    private static final long serialVersionUID = -5346346228715982674L;

    private Integer id;

    private String categoryName;

    private Integer fid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }
}
