package com.jun.plugin.ow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.jun.plugin.common.base.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 幻灯片表 ow_slide
 * 
 * @author admin
 * @date 2018-12-13
 */
@Table(name = "ow_slide")
public class Slide extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	@Id
	private Long id;
	/** 图片 */
	private String img;
	/** 跳转链接 */
	private String url;
	/** 排序 */
	private String sort;
	/** 内容 */
	private String content;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImg(String img)
	{
		this.img = img;
	}

	public String getImg() 
	{
		return img;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getUrl() 
	{
		return url;
	}
	public void setSort(String sort) 
	{
		this.sort = sort;
	}

	public String getSort() 
	{
		return sort;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}


    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("img", getImg())
            .append("url", getUrl())
            .append("sort", getSort())
            .append("content", getContent())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
