package com.jun.plugin.modules.oss.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName("sys_file")
public class SysFile {
    /**
     * 文件id
     */
	@TableId("file_id")
    private Long fileId;

    /**
     * 原始文件名称
     */
	@TableField("original_name")
    private String originalName;

    /**
     * 文件名称
     */
	@TableField("file_name")
    private String fileName;

    /**
     * 文件类型
     */
	@TableField("file_type")
    private String fileType;

    /**
     * 友链图片地址
     */
	@TableField("file_size")
    private String fileSize;

    /**
     * 文件相对路径
     */
	@TableField("file_path")
    private String filePath;

    /**
     * 文件绝对路径
     */
	@TableField("file_full_path")
    private String fileFullPath;

	@TableField("file_hash")
    private String fileHash;

    /**
     * oss存储类型
     */
    @TableField("oss_type")
    private Integer ossType;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 添加时间
     */
    @TableField("create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 获取文件id
     *
     * @return file_id - 文件id
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 设置文件id
     *
     * @param fileId 文件id
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * 获取原始文件名称
     *
     * @return original_name - 原始文件名称
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * 设置原始文件名称
     *
     * @param originalName 原始文件名称
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    /**
     * 获取文件名称
     *
     * @return file_name - 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件类型
     *
     * @return file_type - 文件类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置文件类型
     *
     * @param fileType 文件类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 获取友链图片地址
     *
     * @return file_size - 友链图片地址
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * 设置友链图片地址
     *
     * @param fileSize 友链图片地址
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 获取文件相对路径
     *
     * @return file_path - 文件相对路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置文件相对路径
     *
     * @param filePath 文件相对路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取文件绝对路径
     *
     * @return file_full_path - 文件绝对路径
     */
    public String getFileFullPath() {
        return fileFullPath;
    }

    /**
     * 设置文件绝对路径
     *
     * @param fileFullPath 文件绝对路径
     */
    public void setFileFullPath(String fileFullPath) {
        this.fileFullPath = fileFullPath;
    }

    /**
     * @return file_hash
     */
    public String getFileHash() {
        return fileHash;
    }

    /**
     * @param fileHash
     */
    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    /**
     * 获取oss存储类型
     *
     * @return oss_type - oss存储类型
     */
    public Integer getOssType() {
        return ossType;
    }

    /**
     * 设置oss存储类型
     *
     * @param ossType oss存储类型
     */
    public void setOssType(Integer ossType) {
        this.ossType = ossType;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取添加时间
     *
     * @return create_time - 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置添加时间
     *
     * @param createTime 添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public SysFile withOriginalName(String originalName) {
        this.originalName = originalName;
        return this;
    }
    public SysFile withFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
    public SysFile withFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }
    public SysFile withFileSize(String fileSize) {
        this.fileSize = fileSize;
        return this;
    }
    public SysFile withFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }
    public SysFile withFileFullPath(String fileFullPath) {
        this.fileFullPath = fileFullPath;
        return this;
    }
    public SysFile withFileHash(String fileHash) {
        this.fileHash = fileHash;
        return this;
    }
    public SysFile withOssType(Integer ossType) {
        this.ossType = ossType;
        return this;
    }
    public SysFile withStatus(Integer status) {
        this.status = status;
        return this;
    }
    public SysFile withCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    public SysFile withUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}