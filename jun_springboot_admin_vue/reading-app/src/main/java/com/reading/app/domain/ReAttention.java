package com.reading.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.reading.common.annotation.Excel;
import com.reading.common.core.domain.BaseEntity;

/**
 * 关注对象 re_attention
 * 
 * @author reading
 * @date 2021-03-29
 */
public class ReAttention extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 账号id */
    @Excel(name = "账号id")
    private Long uid;

    /** 被关注的账号id */
    @Excel(name = "被关注的账号id")
    private Long auid;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUid(Long uid) 
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }
    public void setAuid(Long auid) 
    {
        this.auid = auid;
    }

    public Long getAuid() 
    {
        return auid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("auid", getAuid())
            .toString();
    }
}
