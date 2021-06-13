package com.qingshop.mall.modules.mall.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * 评论表
 */
public class MallComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("comment_id")
    private Long commentId;

    /**
     * 关联id
     */
    @TableField("value_id")
    private Long valueId;

    /**
     * 0则是商品评论
     */
    @TableField("type")
    private Integer type;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 用户表的用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 是否含有图片
     */
    @TableField("is_show")
    private Integer isShow;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    @TableField("replay")
    private String replay;

    /**
     * 评分， 1-5
     */
    @TableField("star")
    private Integer star;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    public Long getValueId() {
        return valueId;
    }

    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }
   
    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getReplay() {
		return replay;
	}

	public void setReplay(String replay) {
		this.replay = replay;
	}

	public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MallComment{" +
        "commentId=" + commentId +
        ", valueId=" + valueId +
        ", type=" + type +
        ", content=" + content +
        ", userId=" + userId +
        ", isShow=" + isShow +
        ", replay=" + replay +
        ", star=" + star +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
