package com.ifast.common.base.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

/**
 * <pre>
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
@TableName("sys_dict")
@Data
public class DictDO extends Model<DictDO> implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    public final static DictDO dao = new DictDO();
    
    // 编号
    @TableId
    private Long id;
    // 标签名
    private String name;
    // 数据值
    private String value;
    // 类型
    private String type;
    // 描述
    private String description;
    // 排序（升序）
    private BigDecimal sort;
    // 父级编号
    @TableField("parent_id")
    private Long parentId;
    // 创建者
    @TableField("create_by")
    private Integer createBy;
    // 创建时间
    @TableField("create_date")
    private Date createDate;
    // 更新者
    @TableField("update_by")
    private Long updateBy;
    // 更新时间
    @TableField("update_date")
    private Date updateDate;
    // 备注信息
    private String remarks;
    // 删除标记
    @TableField("del_flag")
    private String delFlag;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
