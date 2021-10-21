package com.jun.plugin.ow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.jun.plugin.common.base.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 设备介绍表 ow_equipment
 * 
 * @author admin
 * @date 2018-12-13
 */
@Table(name = "ow_equipment")
public class Equipment extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	@Id
	private Long id;
	/** 设备名字 */
	private String name;
	/** 设备介绍 */
	private String content;
	/** 是否启用 */
	private String status;
	/**
	 * 方面图片
	 */
	private String coverImgUrl;

	public String getCoverImgUrl() {
		return coverImgUrl;
	}

	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
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


    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("content", getContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
