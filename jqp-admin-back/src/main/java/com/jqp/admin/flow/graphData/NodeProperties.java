package com.jqp.admin.flow.graphData;

import lombok.Data;

@Data
public class NodeProperties{
    //状态
    private String status;
    //审核人id列表,用逗号隔开
    private String users;
    //审核人岗位列表,用逗号隔开
    private String positionCodes;
    //查看表单,编号
    private String viewForm;
    //是否激活,已经流转的会变成激活,值为"true"
    private String active;
    //是否当前,已经流转的会变成当前,值为"true"
    private String current;
}
