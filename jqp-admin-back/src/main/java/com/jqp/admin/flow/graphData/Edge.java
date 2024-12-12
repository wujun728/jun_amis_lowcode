package com.jqp.admin.flow.graphData;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Edge implements Comparable<Edge>{
    private String id;
    private String type;
    private String sourceNodeId;
    private String targetNodeId;
    private EdgeProperties properties = new EdgeProperties();
    private NodeText text = new NodeText();

    //位置信息,忽略
    private Point startPoint = new Point();
    private Point endPoint = new Point();
    private List<Point> pointsList = new ArrayList<>();

    @Override
    public int compareTo(Edge o) {
        Integer checkSeq1 = this.getProperties().getCheckSeq();
        Integer checkSeq2 = o.getProperties().getCheckSeq();
        checkSeq1 = checkSeq1 == null ? 0 : checkSeq1;
        checkSeq2 = checkSeq2 == null ? 0 : checkSeq2;
        return checkSeq1.compareTo(checkSeq2);
    }
}
