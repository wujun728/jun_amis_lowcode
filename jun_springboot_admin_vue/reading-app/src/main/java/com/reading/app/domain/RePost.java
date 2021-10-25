package com.reading.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.reading.common.annotation.Excel;
import com.reading.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 帖子对象 re_post
 * 
 * @author cj
 * @date 2021-03-24
 */
public class RePost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 帖子类型 */
    @Excel(name = "帖子类型")
    private Long type;

    /** 帖子标题 */
    @Excel(name = "帖子标题")
    private String title;

    /** 用户账号id */
    @Excel(name = "用户账号id")
    private Long aid;

    /** 帖子分类 */
    @Excel(name = "帖子分类")
    private String classType;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 配图 */
    @Excel(name = "配图")
    private String pictures;

    /** 图片名称 */
    @Excel(name = "图片名称")
    private String picnames;

    private Date createtime;

    private User reUser;

    List<ReRemark> remarks;

    public List<ReRemark> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<ReRemark> remarks) {
        this.remarks = remarks;
    }

    public User getReUser() {
        return reUser;
    }

    public void setReUser(User reUser) {
        this.reUser = reUser;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setAid(Long aid) 
    {
        this.aid = aid;
    }

    public Long getAid() 
    {
        return aid;
    }
    public void setClassType(String classType) 
    {
        this.classType = classType;
    }

    public String getClassType() 
    {
        return classType;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPictures(String pictures) 
    {
        this.pictures = pictures;
    }

    public String getPictures() 
    {
        return pictures;
    }
    public void setPicnames(String picnames) 
    {
        this.picnames = picnames;
    }

    public String getPicnames() 
    {
        return picnames;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("title", getTitle())
            .append("aid", getAid())
            .append("createtime", getCreatetime())
            .append("classType", getClassType())
            .append("content", getContent())
            .append("pictures", getPictures())
            .append("picnames", getPicnames())
            .toString();
    }
}
