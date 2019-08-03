package com.fh.shop.po.log;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>包名称：com.fh.shop.po.log
 * 类名称：LogInfo
 * 类描述：TODO
 * 创建人：Mr.zhang   2424968072@qq.com
 * 创建时间：2019/6/1912:57
 * 修改人：Mr.zhang     2424968072@qq.com
 * 修改备注：
 * @version </pre>
 */
public class LogInfo implements Serializable {
    private static final long serialVersionUID = -3852549062754140130L;

    private Integer id;

    private String userName;//操作人名称

    private String content;//操作内容

    private Integer status;//执行状态

    private Date createDate;//操作时间

    private String detailDesc;//详细信息

    private String info;//方法信息

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
