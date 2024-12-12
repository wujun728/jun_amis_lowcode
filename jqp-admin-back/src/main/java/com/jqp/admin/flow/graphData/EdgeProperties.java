package com.jqp.admin.flow.graphData;

import lombok.Data;

@Data
public class EdgeProperties {
    //状态
    private String status;
    //前置接口
    private String beforeApi;
    //后置接口
    private String afterApi;
    //是否通过
    private String pass;
    //是否激活
    private String active;
    //是否当前,已经流转的会变成当前,值为"true"
    private String current;

    //判断类型
    private String checkType;
    //判断配置
    private String checkConfig;
    //判断顺序
    private Integer checkSeq;
}
