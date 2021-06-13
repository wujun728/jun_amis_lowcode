package com.qingshop.mall.modules.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 文件存储表
 */
@TableName("sys_upload_file")
public class SysUploadFile implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件id
	 */
	@TableId(value = "file_id", type = IdType.INPUT)
	private Long fileId;
	/**
	 * 存储方式
	 */
	@TableField("oss_type")
	private Integer ossType;
	/**
	 * 文件分组id
	 */
	@TableField("group_id")
	private Long groupId;
	/**
	 * 存储域名
	 */
	@TableField("file_url")
	private String fileUrl;
	/**
	 * 文件名
	 */
	@TableField("file_name")
	private String fileName;
	/**
	 * 文件路径
	 */
	@TableField("file_path")
	private String filePath;
	/**
	 * 文件大小(字节)
	 */
	@TableField("file_size")
	private Long fileSize;

	/**
	 * 文件类型
	 */
	@TableField("file_type")
	private String fileType;

	/**
	 * 文件hash
	 */
	@TableField("file_hash")
	private String fileHash;

	/**
	 * 文件名
	 */
	@TableField("original_name")
	private String originalName;
	/**
	 * 软删除
	 */
	@TableField("is_delete")
	private Integer isDelete;
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

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
	public Integer getOssType() {
		return ossType;
	}

	public void setOssType(Integer ossType) {
		this.ossType = ossType;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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

	public SysUploadFile withOriginalName(String originalName) {
		this.originalName = originalName;
		return this;
	}

	public SysUploadFile withFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public SysUploadFile withFileType(String fileType) {
		this.fileType = fileType;
		return this;
	}

	public SysUploadFile withFileSize(Long fileSize) {
		this.fileSize = fileSize;
		return this;
	}

	public SysUploadFile withFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}

	public SysUploadFile withFileFullPath(String fileUrl) {
		this.fileUrl = fileUrl;
		return this;
	}

	public SysUploadFile withFileHash(String fileHash) {
		this.fileHash = fileHash;
		return this;
	}

	public SysUploadFile withOssType(Integer ossType) {
		this.ossType = ossType;
		return this;
	}

	public SysUploadFile withStatus(Integer isDelete) {
		this.isDelete = isDelete;
		return this;
	}

	public SysUploadFile withCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public SysUploadFile withUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
}
