package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSS对象存储分页查询对象 sys_oss
 *
 * @author Lion Li
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("OSS对象存储分页查询对象")
public class SysOssBo extends BaseEntity {

    /**
     * 文件名
     */
    @ApiModelProperty("文件名")
    private String fileName;
    /**
     * 原名
     */
    @ApiModelProperty("原名")
    private String originalName;
    /**
     * 文件后缀名
     */
    @ApiModelProperty("文件后缀名")
    private String fileSuffix;
    /**
     * URL地址
     */
    @ApiModelProperty("URL地址")
    private String url;
    /**
     * 服务商
     */
    @ApiModelProperty("服务商")
    private String service;

}
