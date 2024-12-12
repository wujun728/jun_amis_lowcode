package com.jqp.admin.activity.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jqp.admin.activity.constants.ModelExtraField;
import com.jqp.admin.activity.data.ModelData;
import com.jqp.admin.activity.service.ActivityService;
import com.jqp.admin.common.config.SessionContext;
import com.jqp.admin.common.config.UserSession;
import com.jqp.admin.db.service.JdbcService;
import com.jqp.admin.rbac.data.User;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service("activityService")
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    @Resource
    ProcessEngine processEngine;
    @Resource
    ObjectMapper objectMapper;
    @Resource
    JdbcService jdbcService;
    @Override
    public Model saveOrUpdate(ModelData modelData) {
        RepositoryService repositoryService = processEngine.getRepositoryService();

        String _id = "EMPTY";

        //初始化一个空模型
        boolean isSave = StringUtils.isBlank(modelData.getId());

        if(!isSave){
            _id= modelData.getId();
        }
        Long count = jdbcService.findOneForObject("select count(1) from act_re_model where KEY_=? and ID_ <> ?", Long.class, modelData.getKey(), _id);
        if(count != null && count.intValue() >0){
            return null;
        }

        Model model = null;
        if(isSave){
            model = repositoryService.newModel();
        }else{
            model = repositoryService.getModel(modelData.getId());

        }


        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, modelData.getName());
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, modelData.getDescription());
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, modelData.getRevision());
        modelNode.put(ModelExtraField.InitSql, modelData.getInitSql());
        modelNode.put(ModelExtraField.BeforeApi, modelData.getBeforeApi());
        modelNode.put(ModelExtraField.AfterApi, modelData.getAfterApi());
        modelNode.put(ModelExtraField.TableName, modelData.getTableName());
        modelNode.put(ModelExtraField.StatusField, modelData.getStatusField());
        modelNode.put(ModelExtraField.StatusDic, modelData.getStatusDic());
        modelNode.put(ModelExtraField.ViewForm, modelData.getViewForm());

        model.setName(modelData.getName());
        model.setKey(modelData.getKey());
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        UserSession session = SessionContext.getSession();
        User user = jdbcService.getById(User.class, session.getUserId());
        if(isSave){
            String id = model.getId();

            //完善ModelEditorSource
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace",
                    "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.set("stencilset", stencilSetNode);

            ObjectNode properties = objectMapper.createObjectNode();
            properties.put("process_id",modelData.getKey());
            properties.put("documentation",modelData.getDescription());
            properties.put("name",modelData.getName());
            properties.put("process_author", user.getName());
            editorNode.set("properties",properties);

            try {
                repositoryService.addModelEditorSource(id,editorNode.toString().getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            try {
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(
                        new String(repositoryService.getModelEditorSource(model.getId()), "utf-8"));
                ObjectNode properties = (ObjectNode) editorJsonNode.get("properties");
                if(properties == null){
                    properties = objectMapper.createObjectNode();
                }
                properties.put("process_id",modelData.getKey());
                properties.put("documentation",modelData.getDescription());
                properties.put("name",modelData.getName());
                properties.put("process_author", user.getName());
                Integer version = jdbcService.findOneForObject("select d.VERSION_ from act_re_model m " +
                        "left join act_re_procdef d on d.DEPLOYMENT_ID_ = m.DEPLOYMENT_ID_" +
                        " where m.id_ = ? ", Integer.class, modelData.getId());
                if(version != null){
                    properties.put("process_version",version);
                }

                editorJsonNode.set("properties",properties);
                repositoryService.addModelEditorSource(modelData.getId(),editorJsonNode.toString().getBytes("utf-8"));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return model;
    }

    @Override
    public ModelData getModelData(String id) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Model model = repositoryService.getModel(id);

        ModelData modelData = new ModelData();
        modelData.setId(model.getId());
        modelData.setKey(model.getKey());
        modelData.setName(model.getName());

        JSONObject json = JSONUtil.parseObj(model.getMetaInfo());
        modelData.setDescription(json.getStr(ModelDataJsonConstants.MODEL_DESCRIPTION));
        modelData.setRevision(json.getInt(ModelDataJsonConstants.MODEL_REVISION));
        modelData.setInitSql(json.getStr(ModelExtraField.InitSql));
        modelData.setBeforeApi(json.getStr(ModelExtraField.BeforeApi));
        modelData.setAfterApi(json.getStr(ModelExtraField.AfterApi));
        modelData.setTableName(json.getStr(ModelExtraField.TableName));
        modelData.setStatusField(json.getStr(ModelExtraField.StatusField));
        modelData.setStatusDic(json.getStr(ModelExtraField.StatusDic));
        modelData.setViewForm(json.getStr(ModelExtraField.ViewForm));
        return modelData;
    }

    @Override
    public ProcessDefinition getLastVersion(String key) {
        return processEngine
                .getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(key)
                .latestVersion()
                .singleResult();
    }

    @Override
    public JSONObject getMetaInfo(String deploymentId) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<String> deploymentResourceNames = repositoryService.getDeploymentResourceNames(deploymentId);
        if(!deploymentResourceNames.contains(ModelExtraField.MetaInfo)){
            return null;
        }
        InputStream metaInfo = processEngine.getRepositoryService().getResourceAsStream(deploymentId, ModelExtraField.MetaInfo);
        String metaStr = IoUtil.read(metaInfo, "UTF-8");
        return JSONUtil.parseObj(metaStr);
    }

    @Override
    public List<SequenceFlow> getNextNode(String taskId) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        HistoryService historyService = processEngine.getHistoryService();
        HistoricTaskInstance myTask = processEngine.getHistoryService().createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        String processDefinitionId = myTask.getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        List<HistoricActivityInstance> haiList = historyService.createHistoricActivityInstanceQuery()
                .executionId(myTask.getExecutionId()).finished().list();
        String myActivityId = myTask.getTaskDefinitionKey();
        if (StringUtils.isEmpty(myActivityId)) {
            return new ArrayList();
        }
        FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(myActivityId);
        List<SequenceFlow> list = flowNode.getOutgoingFlows();
        return list;
    }

    @Override
    public void setParams(String processInstanceId, String key, Object value) {
        log.info("设置流程参数:{},{}={}",processInstanceId,key,value);
        processEngine.getRuntimeService().setVariable(processInstanceId,key,value == null ? "":value.toString());
    }
}
