package com.fh.shop.po.area;

import java.io.Serializable;

/**
 * <pre>包名称：com.fh.shop.po.area
 * 类名称：AreaInfo
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/2321:51
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class AreaInfo implements Serializable {
    private static final long serialVersionUID = -1460936641532504711L;

    private Integer id;

    private String  areaName;

    private Integer fid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }
}
