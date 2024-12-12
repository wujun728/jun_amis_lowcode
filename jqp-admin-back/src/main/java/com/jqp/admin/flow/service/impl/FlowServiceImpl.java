package com.jqp.admin.flow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.jqp.admin.common.Result;
import com.jqp.admin.common.config.SessionContext;
import com.jqp.admin.common.config.UserSession;
import com.jqp.admin.db.service.JdbcService;
import com.jqp.admin.flow.constants.CheckType;
import com.jqp.admin.flow.constants.NodeType;
import com.jqp.admin.flow.data.Flow;
import com.jqp.admin.flow.data.FlowDeploy;
import com.jqp.admin.flow.data.FlowInstance;
import com.jqp.admin.flow.data.FlowInstanceTask;
import com.jqp.admin.flow.graphData.Edge;
import com.jqp.admin.flow.graphData.GraphContext;
import com.jqp.admin.flow.graphData.Node;
import com.jqp.admin.flow.service.FlowService;
import com.jqp.admin.page.constants.Whether;
import com.jqp.admin.page.service.DicService;
import com.jqp.admin.rbac.data.User;
import com.jqp.admin.rbac.service.ApiService;
import com.jqp.admin.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssssssss.script.MagicScriptContext;

import javax.annotation.Resource;
import java.util.*;

@Service("flowService")
public class FlowServiceImpl implements FlowService {
    @Resource
    private JdbcService jdbcService;
    @Resource
    private DicService dicService;
    @Resource
    private ApiService apiService;
    @Override
    @Transactional
    public FlowDeploy deploy(Flow flow) {
        FlowDeploy deploy = new FlowDeploy();
        BeanUtil.copyProperties(flow,deploy);
        deploy.setId(null);
        deploy.setFlowId(flow.getId());

        Integer maxVersion = jdbcService.findOneForObject("select max(version) from flow_deploy where flow_id = ? ", Integer.class, flow.getId());
        deploy.setVersion(maxVersion == null ? 1 : maxVersion + 1);
        flow.setLastVersion(deploy.getVersion());

        jdbcService.saveOrUpdate(deploy);
        jdbcService.saveOrUpdate(flow);

        return deploy;
    }

    @Override
    @Transactional
    public void start(String flowCode, Long id, String name) {
        Flow flow = jdbcService.findOne(Flow.class, Flow.Fields.code, flowCode);
        if(flow == null){
            throw new RuntimeException(StrUtil.format("流程【{}】不存在",flowCode));
        }
        FlowInstance flowInstance = jdbcService.findOne(FlowInstance.class, new String[]{
            FlowInstance.Fields.flowId,
            FlowInstance.Fields.refId,
        }, new Object[]{
            flow.getId(),
            id
        });
        FlowDeploy flowDeploy = null;
        if(flowInstance != null){
            flowDeploy = jdbcService.getById(FlowDeploy.class,flowInstance.getFlowDeployId());
        }else{
            if(flow.getLastVersion() == null){
                throw new RuntimeException(StrUtil.format("流程【{}】未部署",flowCode));
            }
            flowDeploy = jdbcService.findOne(FlowDeploy.class,new String[]{
                FlowDeploy.Fields.flowId,
                FlowDeploy.Fields.version
            },new Object[]{
                flow.getId(),
                flow.getLastVersion()
            });
            flowInstance = new FlowInstance();
            flowInstance.setFlowId(flow.getId());
            flowInstance.setFlowDeployId(flowDeploy.getId());
            flowInstance.setFlowCode(flowDeploy.getCode());
            flowInstance.setFlowName(flowDeploy.getName());
            flowInstance.setRefId(id);
            flowInstance.setName(name);
            flowInstance.setStartTime(new Date());
            flowInstance.setViewForm(flowDeploy.getViewForm());
            try{
                UserSession userSession = SessionContext.getSession();
                User user = SessionContext.getUser();
                flowInstance.setCreateUserId(user.getId());
                flowInstance.setCreateUserName(user.getName());
                flowInstance.setEnterpriseId(userSession.getEnterpriseId());
            }catch (Exception e){}
        }

        GraphContext graphContext = new GraphContext(flowDeploy.getGraphData());
        FlowInstanceTask task = null;
        if(flowInstance.getId() == null){
            Node startNode = graphContext.getStartNode();
            List<Edge> edges = graphContext.getEdges(startNode.getId());
            if(edges.size() != 1){
                throw new RuntimeException("流程配置错误,开始节点有且只能有一个流转");
            }
            Node initNode = graphContext.getNode(edges.get(0).getTargetNodeId());
            flowInstance.setCurrentTaskId(initNode.getId());
            flowInstance.setCurrentTaskName(initNode.getText().getValue());

            jdbcService.saveOrUpdate(flowInstance);

            task = createTask(flowDeploy, flowInstance, id, initNode);
            jdbcService.saveOrUpdate(task);
        }else{
            task = jdbcService.findOne("select * from flow_instance_task where flow_instance_id = ? order by start_time desc limit 1",FlowInstanceTask.class,flowInstance.getId());
        }
        Map<String, Object> obj = jdbcService.getById(flowDeploy.getTableName(), id);

        //当前节点
        Node currentNode = graphContext.getNode(flowInstance.getCurrentTaskId());

        jdbcService.saveOrUpdate(flowInstance);

        //后面的节点
        List<Edge> edges = graphContext.getEdges(currentNode.getId());

        //暂时不判断条件流转
        if(edges.size() != 1){
            throw new RuntimeException("流程配置错误,发起节点有且只能有一个流转");
        }
        Edge edge = edges.get(0);

        //完成提交任务
        completeTask(task.getId(),edge.getId(),null);

    }

    @Override
    @Transactional
    public void completeTask(Long id,String edgeId,Map<String,Object> body) {
        FlowInstanceTask task = jdbcService.getById(FlowInstanceTask.class, id);
        if(task == null || task.getEndTime() != null){
            throw new RuntimeException("任务已完成");
        }
        FlowInstance flowInstance = jdbcService.getById(FlowInstance.class, task.getFlowInstanceId());
        FlowDeploy flowDeploy = jdbcService.getById(FlowDeploy.class, flowInstance.getFlowDeployId());

        String viewForm = flowDeploy.getViewForm();
        String tableName = flowDeploy.getTableName();
        String statusField = StringUtil.toFieldColumn(flowDeploy.getStatusField());
        String beforeApi = flowDeploy.getBeforeApi();
        String afterApi = flowDeploy.getAfterApi();
        String statusDic = flowDeploy.getStatusDic();
        Long refId = flowInstance.getRefId();
        GraphContext graphContext = new GraphContext(flowDeploy.getGraphData());

        Map<String, Object> obj = jdbcService.getById(tableName, refId);
        String objStatus = (String) obj.get(statusField);
        if(org.apache.commons.lang.StringUtils.isBlank(objStatus)){
            throw new RuntimeException("审核失败,单据状态错误");
        }
        Node currentNode = graphContext.getNode(task.getTaskId());
        Edge edge = graphContext.getEdge(edgeId);

        String nodeStatus = currentNode.getProperties().getStatus();
        if(StringUtils.isNotBlank(nodeStatus)){
            String[] arr = StringUtil.splitStr(nodeStatus, ",");
            boolean flag = false;
            for(String s:arr){
                if(s.equals(objStatus)){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                throw new RuntimeException("审核失败,单据状态可能已经发生变化!");
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("prevStatus",objStatus);
        params.put("nextStatus",edge.getProperties().getStatus());
        params.put("params",body);
        //executionId 和processInstanceId 是一样的
        params.put("flowInstance",flowInstance);
        params.put("task",task);
        params.put("obj",obj);
        params.putAll(obj);

        Result<String> call = apiService.call(beforeApi, params);
        if(!call.isSuccess()){
            throw new RuntimeException(call.getMsg());
        }

        //设置动态参数必须在任务完成之前设置,完成任务找不到参数直接报错
        Result<String> _call = apiService.call(afterApi, params);
        if(!_call.isSuccess()){
            throw new RuntimeException(_call.getMsg());
        }
        String auditRemark = null,
                auditImgs = null,
                auditFiles = null;
        if(body != null){
            auditRemark = (String)body.get("auditRemark");
            auditImgs = (String)body.get("auditImgs");
            auditFiles = (String)body.get("auditFiles");
        }

        task.setOrderStatus(edge.getProperties().getStatus());
        task.setOrderStatusName(dicService.getLabel(flowDeploy.getStatusDic(),edge.getProperties().getStatus()));
        task.setEndTime(new Date());
        task.setPass(Whether.NO.equals(edge.getProperties().getPass()) ? Whether.NO : Whether.YES);
        task.setEdgeId(edge.getId());
        task.setEdgeName(edge.getText().getValue());
        if(StringUtils.isNotBlank(auditFiles)){
            task.setAuditFiles(auditFiles);
        }
        if(StringUtils.isNotBlank(auditImgs)){
            task.setAuditImgs(auditImgs);
        }
        if(StringUtils.isNotBlank(auditRemark)){
            task.setAuditRemark(auditRemark);
        }

        try{
            User user = SessionContext.getUser();

            task.setAuditUserId(user.getId());
            task.setAuditUserName(user.getName());
        }catch (Exception e){}

        jdbcService.update(task);

        if(StringUtils.isNotBlank(edge.getProperties().getStatus())){
            obj.put(statusField,edge.getProperties().getStatus());
            jdbcService.update(obj,tableName);
        }

        Node nextNode = graphContext.getNode(edge.getTargetNodeId());

        FlowInstanceTask nextTask = this.createTask(flowDeploy, flowInstance, id, nextNode);
        jdbcService.saveOrUpdate(nextTask);

        while(NodeType.Condition.equals(nextNode.getType())){
            List<Edge> nextEdges = graphContext.getEdges(nextNode.getId());
            Collections.sort(nextEdges);
            Edge resultEdge = null;
            for(Edge nextEdge:nextEdges){
                if(CheckType.Expression.equals(nextEdge.getProperties().getCheckType())){
                    MagicScriptContext magicScriptContext = new MagicScriptContext();
                    Boolean result = (Boolean) magicScriptContext.eval(nextEdge.getProperties().getCheckConfig(), params);
                    if(result){
                        resultEdge = nextEdge;
                        break;
                    }
                }else if(CheckType.MagicApi.equals(nextEdge.getProperties().getCheckType())){
                    Result result = apiService.call("post", nextEdge.getProperties().getCheckConfig(), params);
                    if((Boolean)result.getData()){
                        resultEdge = nextEdge;
                        break;
                    }
                }
            }
            if(resultEdge != null){

                nextTask.setOrderStatus(resultEdge.getProperties().getStatus());
                nextTask.setOrderStatusName(dicService.getLabel(flowDeploy.getStatusDic(),resultEdge.getProperties().getStatus()));
                nextTask.setEndTime(new Date());
                nextTask.setPass(Whether.YES);
                nextTask.setEdgeId(resultEdge.getId());
                nextTask.setEdgeName(resultEdge.getText().getValue());
                try{
                    User user = SessionContext.getUser();

                    nextTask.setAuditUserId(user.getId());
                    nextTask.setAuditUserName(user.getName());
                }catch (Exception e){}
                jdbcService.saveOrUpdate(nextTask);

                if(StringUtils.isNotBlank(resultEdge.getProperties().getStatus())){
                    obj.put(statusField,resultEdge.getProperties().getStatus());
                    jdbcService.update(obj,tableName);
                }

                nextNode = graphContext.getNode(resultEdge.getTargetNodeId());

                if(!NodeType.End.equals(nextNode.getType())){
                    nextTask = this.createTask(flowDeploy, flowInstance, id, nextNode);
                    jdbcService.saveOrUpdate(nextTask);
                }
            }else{
                throw new RuntimeException("流程配置错误,条件判断节点["+nextNode.getText().getValue()+"],条件流转没有满足条件的流转");
            }
        }

        flowInstance.setCurrentTaskId(nextNode.getId());
        flowInstance.setCurrentTaskName(nextNode.getText().getValue());
        jdbcService.update(flowInstance);
        if(NodeType.End.equals(nextNode.getType())){
            //结束节点
            flowInstance.setEndTime(new Date());
            jdbcService.update(flowInstance);
        }
    }

    @Override
    public FlowInstanceTask createTask(FlowDeploy flowDeploy, FlowInstance flowInstance, Long id, Node node){
        FlowInstanceTask task = new FlowInstanceTask();
        task.setTaskId(node.getId());
        task.setFlowCode(flowDeploy.getCode());
        task.setFlowInstanceId(flowInstance.getId());
        task.setFlowCode(flowDeploy.getCode());
        task.setFlowName(flowDeploy.getName());
        task.setFlowInstanceName(flowInstance.getName());
        task.setTableName(flowDeploy.getTableName());

        if(StringUtils.isNotBlank(node.getProperties().getViewForm())){
            task.setViewForm(node.getProperties().getViewForm());
        }else{
            task.setViewForm(flowDeploy.getViewForm());
        }
        task.setStartTime(new Date());
        task.setTaskName(node.getText().getValue());
        task.setCandidateUserIds(node.getProperties().getUsers());
        task.setCandidatePositionCodes(node.getProperties().getPositionCodes());
        task.setOrderStatusDic(flowDeploy.getStatusDic());
        task.setRefId(id);
        try{
            UserSession userSession = SessionContext.getSession();
            User user = SessionContext.getUser();
            task.setEnterpriseId(userSession.getEnterpriseId());

            task.setCreateUserId(user.getId());
            task.setCreateUserName(user.getName());
        }catch (Exception e){}
        return task;
    }
}
