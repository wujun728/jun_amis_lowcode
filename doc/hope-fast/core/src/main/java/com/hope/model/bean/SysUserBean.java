package com.hope.model.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author aodeng
 **/
@Data
@TableName("sys_user")
public class SysUserBean implements Serializable {

    private static final long serialVersionUID = -4080167041530353373L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;
}
