package com.jqp.admin.activity.service;

import cn.hutool.json.JSONObject;
import com.jqp.admin.activity.data.ModelData;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.List;

public interface ActivityService {
    Model saveOrUpdate(ModelData modelData);
    ModelData getModelData(String id);
    ProcessDefinition getLastVersion(String key);
    JSONObject getMetaInfo(String deploymentId);
    List<SequenceFlow> getNextNode(String taskId);
    void setParams(String processInstanceId,String key,Object value);
}
