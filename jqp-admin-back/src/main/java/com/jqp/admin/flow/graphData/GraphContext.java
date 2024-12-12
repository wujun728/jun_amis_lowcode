package com.jqp.admin.flow.graphData;

import cn.hutool.json.JSONUtil;
import com.jqp.admin.flow.constants.NodeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphContext {
    private GraphData graphData;
    private Map<String,Node> nodeMap = new HashMap<>();
    private Map<String,List<Edge>> nodeEdgeMap = new HashMap<>();
    private Map<String,Edge> edgeMap = new HashMap<>();

    private Node startNode = null;
    private Node endNode = null;
    public GraphContext(String graphData){
        this.graphData = JSONUtil.toBean(graphData,GraphData.class);
        this.buildContext();
    }
    private void buildContext(){
        List<Node> nodes = graphData.getNodes();
        for(Node node:nodes){
            nodeMap.put(node.getId(),node);
            if(NodeType.Start.equals(node.getType())){
                startNode = node;
            }else if(NodeType.End.equals(node.getType())){
                endNode = node;
            }
        }

        List<Edge> edges = graphData.getEdges();
        for (Edge edge:edges){
            edgeMap.put(edge.getId(),edge);
            if(!nodeEdgeMap.containsKey(edge.getSourceNodeId())){
                nodeEdgeMap.put(edge.getSourceNodeId(),new ArrayList<>());
            }
            nodeEdgeMap.get(edge.getSourceNodeId()).add(edge);
        }
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }
    public Node getNode(String nodeId){
        return nodeMap.get(nodeId);
    }
    public List<Edge> getEdges(String nodeId){
        return nodeEdgeMap.get(nodeId) == null ? new ArrayList<>() : nodeEdgeMap.get(nodeId);
    }

    public GraphData getGraphData() {
        return graphData;
    }

    public Edge getEdge(String edgeId){
        return edgeMap.get(edgeId);
    }

}
