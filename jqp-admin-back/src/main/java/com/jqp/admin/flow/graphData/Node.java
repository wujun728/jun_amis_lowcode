package com.jqp.admin.flow.graphData;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Node {
    private String id;
    private String type;
    private Integer x;
    private Integer y;
    private NodeProperties properties = new NodeProperties();
    private NodeText text = new NodeText();
}
