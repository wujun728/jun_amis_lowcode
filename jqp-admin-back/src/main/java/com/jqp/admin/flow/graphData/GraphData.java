package com.jqp.admin.flow.graphData;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GraphData {
    private List<Node> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
}
