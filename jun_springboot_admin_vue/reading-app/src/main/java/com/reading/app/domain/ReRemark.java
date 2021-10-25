package com.reading.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.reading.common.annotation.Excel;
import com.reading.common.core.domain.BaseEntity;

/**
 * 评论对象 re_remark
 * 
 * @author ruoyi
 * @date 2021-03-28
 */
public class ReRemark extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 评论人员 */
    @Excel(name = "评论人员")
    private Long aid;

    private String remark;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 帖子id */
    @Excel(name = "帖子id")
    private Long pid;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAid(Long aid) 
    {
        this.aid = aid;
    }

    public Long getAid() 
    {
        return aid;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }

    private User reUser;

    public User getReUser() {
        return reUser;
    }

    public void setReUser(User reUser) {
        this.reUser = reUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("aid", getAid())
            .append("pid", getPid())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
