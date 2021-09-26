package com.royal.ow.domain;

import com.royal.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 惠民服务表 ow_huiming
 * 
 * @author royal
 * @date 2018-12-13
 */
@Table(name = "ow_huiming")
public class Huiming extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	@Id
	private Long id;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 状态（0正常 1关闭） */
	private String status;

	/** 封面图片 */
	private String coverImgUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}


	public void setCoverImgUrl(String coverImgUrl) 
	{
		this.coverImgUrl = coverImgUrl;
	}

	public String getCoverImgUrl() 
	{
		return coverImgUrl;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("coverImgUrl", getCoverImgUrl())
            .toString();
    }
}
