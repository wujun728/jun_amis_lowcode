package com.jun.plugin.ow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.jun.plugin.common.base.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 新闻表 ow_news
 * 
 * @author admin
 * @date 2018-12-10
 */
@Table(name = "ow_news")
public class News extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 新闻ID */
	@Id
	private Long newsId;
	/** 1最新咨询 2相关政策 */
	private String newsType;
	/** 标题 */
	private String newsTitle;
	/** 新闻内容 */
	private String newsContent;
	/** 状态（0正常 1关闭） */
	private String status;
    /**
     * 新闻简介
     */
	private String synopsis;

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
	 * 封面图片
	 */
	private String coverImgUrl;

	public String getCoverImgUrl() {
		return coverImgUrl;
	}

	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public void setNewsType(String newsType)
	{
		this.newsType = newsType;
	}

	public String getNewsType() 
	{
		return newsType;
	}
	public void setNewsTitle(String newsTitle) 
	{
		this.newsTitle = newsTitle;
	}

	public String getNewsTitle() 
	{
		return newsTitle;
	}
	public void setNewsContent(String newsContent) 
	{
		this.newsContent = newsContent;
	}

	public String getNewsContent()
	{
		return newsContent;
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
            .append("newsId", getNewsId())
            .append("newsType", getNewsType())
            .append("newsTitle", getNewsTitle())
            .append("newsContent", getNewsContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
				.append("coverImgUrl", getCoverImgUrl())
				.append("synopsis", getSynopsis())
            .toString();
    }
}
