package com.jqp.admin.chart.data;

import com.jqp.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-04-10 14:41:40
 * @remark 图表
 */
@Data
public class Chart extends BaseData {
    //名称
    private String name;
    //配置
    private String config;
    //查询sql
    private String querysql;
    //编号
    private String code;
}