package com.jqp.admin.activity.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jqp.admin.activity.constants.ModelExtraField;
import com.jqp.admin.activity.data.AuditRecord;
import com.jqp.admin.activity.data.ModelData;
import com.jqp.admin.activity.service.ActivityService;
import com.jqp.admin.common.Result;
import com.jqp.admin.common.config.SessionContext;
import com.jqp.admin.common.config.UserSession;
import com.jqp.admin.common.data.Obj;
import com.jqp.admin.db.service.JdbcService;
import com.jqp.admin.page.constants.ActionType;
import com.jqp.admin.page.constants.DataType;
import com.jqp.admin.page.constants.Whether;
import com.jqp.admin.page.data.*;
import com.jqp.admin.page.service.DicCacheService;
import com.jqp.admin.page.service.DicService;
import com.jqp.admin.page.service.FormService;
import com.jqp.admin.rbac.data.User;
import com.jqp.admin.rbac.service.ApiService;
import com.jqp.admin.util.StringUtil;
import com.jqp.admin.util.TemplateUtil;
import com.jqp.admin.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Zhan Xinjian
 * @date 2021/1/18
 */

@RestController
@RequestMapping("/admin/models")
@Slf4j
public class ActivitiModelController {


    @Resource
    ProcessEngine processEngine;
    @Resource
    ObjectMapper objectMapper;

    @Resource
    ActivityService activityService;

    @Resource
    private JdbcService jdbcService;

    @Resource
    private ApiService apiService;

    @Resource
    private DicCacheService dicCacheService;

    @Resource
    private FormService formService;

    /**
     * 新建一个空模型
     */
    @RequestMapping("/save")
    public Result save(@RequestBody ModelData modelData) throws IOException {
        //response.sendRedirect("/activity-web/modeler.html?modelId="+id);
        Model model = activityService.saveOrUpdate(modelData);
        if(model == null){
            return Result.error("流程编码不能重复");
        }
        return Result.success();
    }
    /**
     * 获取
     */
    @RequestMapping("/get")
    public Result get(String id) throws IOException {
        //response.sendRedirect("/activity-web/modeler.html?modelId="+id);
        if(StringUtils.isBlank(id)){
            return Result.success(new ModelData());
        }
        ModelData modelData = activityService.getModelData(id);
        return Result.success(modelData);
    }

    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable String id){
        processEngine.getRepositoryService().deleteModel(id);
        return Result.success();
    }

    /**
     * 发布模型为流程定义
     */
    @RequestMapping("/deploy/{modelId}")
    public Result deploy(@PathVariable String modelId) throws Exception {

        //获取模型
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Model modelData = repositoryService.getModel(modelId);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

        if (bytes == null) {
            return Result.error("模型数据为空，请先设计流程并成功保存，再进行发布。");
        }
        JsonNode modelNode = new ObjectMapper().readTree(bytes);



        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);

        if(model.getProcesses().size()==0){
            return Result.error("数据模型不符要求，请至少设计一条主线流程。");
        }

        List<Process> processes = model.getProcesses();
        for(Process process:processes){
            for(FlowElement element:process.getFlowElements()){
                if(element instanceof UserTask){
                    UserTask task = (UserTask) element;
                    if(StringUtils.isBlank(task.getDocumentation())){
                        return Result.error("任务["+task.getName()+"]没有设置状态值");
                    }
                    for(SequenceFlow node:task.getOutgoingFlows()){
                        if(StringUtils.isBlank(node.getDocumentation())){
                            return Result.error("任务["+task.getName()+"]流转["+node.getName()+"]没有设置状态值");
                        }
                        if(task.getOutgoingFlows().size() > 1){
                            if(StringUtils.isBlank(node.getConditionExpression())){
                                return Result.error("任务["+task.getName()+"]流转["+node.getName()+"]没有设置流转条件");
                            }
                        }
                    }
                }
            }
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        //发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
                .addString(processName, new String(bpmnBytes, "UTF-8"))
                .addString("metaInfo", modelData.getMetaInfo())
                .deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);

        return Result.success();
    }

    /**
     *  启动流程
     */
    @RequestMapping("/start/{keyName}/{id}/{name}")
    @ResponseBody
    public Object start(@PathVariable String keyName,@PathVariable Long id,@PathVariable String name) {

        ProcessDefinition processDefinition = activityService.getLastVersion(keyName);

        if(processDefinition == null){
            return Result.error("流程不存在["+keyName+"]");
        }
        JSONObject metaInfo = activityService.getMetaInfo(processDefinition.getDeploymentId());
        if(metaInfo == null){
            return Result.error("流程["+keyName+"]配置错误,没有元数据");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);

        String initSql = metaInfo.getStr(ModelExtraField.InitSql);
        String tableName = metaInfo.getStr(ModelExtraField.TableName);
        String statusField_ = metaInfo.getStr(ModelExtraField.StatusField);
        String beforeApi = metaInfo.getStr(ModelExtraField.BeforeApi);
        String afterApi = metaInfo.getStr(ModelExtraField.AfterApi);
        String statusDic = metaInfo.getStr(ModelExtraField.StatusDic);
        if(StringUtils.isBlank(statusField_)){
            return Result.error("流程配置错误,状态字段不能为空");
        }
        if(StringUtils.isBlank(statusDic)){
            return Result.error("流程配置错误,状态字典不能为空");
        }
        String statusField = StringUtil.toFieldColumn(statusField_);


        if(StringUtils.isBlank(tableName)){
            return Result.error("流程配置错误,表不能为空");
        }
        Map<String, Object> obj = jdbcService.getById(tableName, id);
        if(obj != null){
            obj.entrySet().forEach(entry -> {
                params.put(entry.getKey(),entry.getValue() == null ? "" : entry.getValue().toString());
            });
        }else{
            return Result.error("查不到数据");
        }
        String objStatus = (String) obj.get(statusField);
        if(StringUtils.isBlank(objStatus)){
            return Result.error("当前状态不能为空");
        }
        if(StringUtils.isNotBlank(initSql)){
            Map<String, Object> sqlParams = new HashMap<>();
            SessionContext.putUserSessionParams(sqlParams);
            initSql = TemplateUtil.getValue(initSql,sqlParams);

            Map<String, Object> data = jdbcService.findOne(initSql);
            if(data != null){
                params.putAll(data);
            }
        }

        Result<String> result = apiService.call(beforeApi, params);
        if(!result.isSuccess()){
            return result;
        }
        Obj<ProcessInstance> instanceObj = new Obj<>(null);

        try{
            jdbcService.transactionOption(()->{
                ProcessInstance processInstance = processEngine
                        .getRuntimeService()
                        .createProcessInstanceQuery()
                        .processDefinitionKey(keyName)
                        .processInstanceBusinessKey(id.toString()).singleResult();
                if(processInstance == null){
                    processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(keyName,id.toString(),params);
                    processEngine.getRuntimeService().setProcessInstanceName(processInstance.getId(),name);
                }
                List<Task> tasks = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).list();
                if(tasks.size() != 1){
                    throw new RuntimeException("提交失败,单据状态可能发生改变,无法提交");
                }
                Task task = tasks.get(0);
                String curStatus = task.getDescription();
                if(StringUtils.isBlank(curStatus)){
                    throw new RuntimeException("流程配置错误,任务没有配置状态");
                }
                if(!objStatus.equals(curStatus)){
                    throw new RuntimeException("提交失败,状态可能已经发生变化");
                }

                List<SequenceFlow> nodes = activityService.getNextNode(task.getId());
                if(nodes.size() != 1){
                    throw new RuntimeException("流程配置错误,提交流转数量错误");
                }

                SequenceFlow node = nodes.get(0);
                String nodeStatus = node.getDocumentation();
                if(StringUtils.isBlank(nodeStatus)){
                    throw new RuntimeException("流程配置错误,提交流转没有配置状态");
                }
                params.put("prevStatus",task.getDescription());
                params.put("nextStatus",node.getDocumentation());
                processEngine.getTaskService().setOwner(task.getId(),SessionContext.getSession().getUserId().toString());
                processEngine.getTaskService().complete(task.getId(),params);

                obj.put(statusField,node.getDocumentation());
                jdbcService.saveOrUpdate(obj,tableName);

                AuditRecord record = createRecord(obj, id, tableName, statusDic, processInstance,processDefinition, task, node);
                record.setProcessInstanceName(name);
                jdbcService.saveOrUpdate(record);
                instanceObj.setValue(processInstance);

                Result<String> call = apiService.call(afterApi, params);
                if(!call.isSuccess()){
                    throw new RuntimeException(call.getMsg());
                }
            });
        }catch (Exception e){
            log.error("提交失败:"+e.getMessage(),e);
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    /**
     *  任务js
     */
    @RequestMapping(value="/taskAudit/js/{taskId}.js",produces = "text/javascript; charset=utf-8")
    @ResponseBody
    public Object taskAudit(@PathVariable String taskId) {
        HistoricTaskInstance task = processEngine.getHistoryService().createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        return auditJs(task,task.getProcessInstanceId(),false);
    }
    /**
     *  任务js
     */
    @RequestMapping(value="/taskView/js/{taskId}.js",produces = "text/javascript; charset=utf-8")
    @ResponseBody
    public Object taskView(@PathVariable String taskId) {
        HistoricTaskInstance task = processEngine.getHistoryService().createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        return auditJs(task,task.getProcessInstanceId(),true);
    }

    /**
     *  任务js
     */
    @RequestMapping(value="/auditRecord/js/{model}/{id}.js",produces = "text/javascript; charset=utf-8")
    @ResponseBody
    public Object auditRecordJs(@PathVariable String model,@PathVariable Long id) {
        String tableName = StringUtil.toSqlColumn(model);
        String processInstanceId = jdbcService.findOneForObject("select process_instance_id from audit_record where table_name = ? and ref_id = ? order by end_time desc ",String.class,tableName,id);
        if(processInstanceId == null){
            Map<String,Object> json = new HashMap<>();
            json.put("type","page");
            json.put("body","暂无审核记录");
            return "AMIS_JSON="+JSONUtil.toJsonPrettyStr(json);
        }
        return auditJs(null,processInstanceId,true);
    }

    private String auditJs(HistoricTaskInstance task,String processInstanceId,boolean view){
        HistoricProcessInstance processInstance = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        ProcessDefinition processDefinition = processEngine.
                getRepositoryService().
                createProcessDefinitionQuery().
                processDefinitionId(processInstance.getProcessDefinitionId()).
                singleResult();
//        ProcessInstance processInstance = processEngine.
//                getRuntimeService().
//                createProcessInstanceQuery().
//                processInstanceId(task.getProcessInstanceId()).
//                singleResult();
        JSONObject metaInfo = activityService.getMetaInfo(processDefinition.getDeploymentId());
        String viewForm = metaInfo.getStr(ModelExtraField.ViewForm);
        String tableName = metaInfo.getStr(ModelExtraField.TableName);
        String statusField = metaInfo.getStr(ModelExtraField.StatusField);
        String beforeApi = metaInfo.getStr(ModelExtraField.BeforeApi);
        String afterApi = metaInfo.getStr(ModelExtraField.AfterApi);
        if(task != null && StringUtils.isNotBlank(task.getFormKey())){
            viewForm = task.getFormKey();
        }
        Form f = formService.get(viewForm);
        f = Util.clone(f);
        if(view){
            f.setDisabled(Whether.YES);
        }
        BaseButton baseButton = new BaseButton();
        baseButton.setLabel("审核");
        if(task != null){
            List<SequenceFlow> nodes = activityService.getNextNode(task.getId());
//            f.setDisabled(Whether.NO);
//            f.getFormFields().forEach(field->{
//                field.setDisabled(Whether.YES);
//            });

            if(task.getEndTime() == null){

                FormField formField = new FormField();
                formField.setWidth(12);
                formField.setLabel("审核备注");
                formField.setField("auditRemark");
                formField.setType(DataType.LONG_TEXT);
                formField.setComponentType("textarea");

                //审核备注字段
                f.getFormFields().add(formField);

                formField = new FormField();
                formField.setWidth(12);
                formField.setField("auditImgs");
                formField.setLabel("图片");
                formField.setType(DataType.IMAGE);
                formField.setMulti(Whether.YES);
                formField.setComponentType("input-image");

                //审核图片字段
                f.getFormFields().add(formField);

                formField = new FormField();
                formField.setWidth(12);
                formField.setField("auditFiles");
                formField.setLabel("附件");
                formField.setType(DataType.STRING);
                formField.setMulti(Whether.YES);
                formField.setComponentType("input-file");

                //审核附件字段
                f.getFormFields().add(formField);

                for(SequenceFlow node:nodes){
                    FormButton formButton = new FormButton();
                    formButton.setLabel(node.getName());
                    formButton.setOptionType(ActionType.IframeAjax);
                    formButton.setOptionValue(StrUtil.format("post:/admin/models/task/complete/{}/{}",
                            task.getId(),node.getDocumentation()));
                    if(node.getName().contains("不")
                            || node.getName().contains("打回")
                            || node.getName().contains("撤销")){
                        formButton.setLevel("danger");
                        formButton.setJsRule("auditRemark==''||auditRemark==null");
                    }else{
                        formButton.setLevel("primary");
                    }
                    f.getFormButtons().add(formButton);
                }
            }
        }


        Long id = Long.valueOf(processInstance.getBusinessKey());
        Map<String, Object> obj = jdbcService.getById(tableName, id);

        Map<String, Object> dialog = formService.getFormJson(f, baseButton);
        Map<String,Object> formJson = (Map<String, Object>) dialog.get("body");
        String initApi = (String) formJson.get("initApi");
        Map<String,Object> params = new HashMap<>();
        params.put("id",processInstance.getBusinessKey());
        initApi = TemplateUtil.getValue(initApi,params);
        formJson.put("initApi",initApi);
        formJson.remove("api");

        List<Map<String,Object>> actions = (List<Map<String, Object>>) dialog.get("actions");
//        List<Map<String,Object>> columns = (List<Map<String, Object>>) ((Map<String,Object>)formJson.get("body")).get("columns");

        List<Map<String,Object>> columns = (List<Map<String, Object>>) formJson.get("body");

        Map<String,Object> flowImg = new HashMap<>();
        flowImg.put("type","iframe");
        flowImg.put("lg",12);
        flowImg.put("md",12);
        flowImg.put("sm",12);
        flowImg.put("xs",12);
        flowImg.put("height","300px");
        flowImg.put("src","/activity-web/diagram-viewer/index.html?processDefinitionId="+processDefinition.getId()+"&processInstanceId="+processInstance.getId());
        columns.add(0,flowImg);

        Map<String,Object> records = new HashMap<>();
        records.put("lg",12);
        records.put("md",12);
        records.put("sm",12);
        records.put("xs",12);
        records.put("type","table");

        List<Map<String,Object>> recordColumns = new ArrayList<>();

        Map<String,Object> col = null ;

        col = new HashMap<>();
        col.put("name","taskName");
        col.put("label","节点");
        recordColumns.add(col);

        col = new HashMap<>();
        col.put("name","startTime");
        col.put("label","开始时间");
        recordColumns.add(col);

        col = new HashMap<>();
        col.put("name","endTime");
        col.put("label","完成时间");
        recordColumns.add(col);

        col = new HashMap<>();
        col.put("name","auditUserName");
        col.put("label","审核人");
        recordColumns.add(col);

        col = new HashMap<>();
        col.put("name","resultName");
        col.put("label","审核结果");
        recordColumns.add(col);

        col = new HashMap<>();
        col.put("name","remark");
        col.put("label","审核备注");
        recordColumns.add(col);

        col = new HashMap<>();
        col.put("name","imgs");
        col.put("label","图片");
        col.put("type","images");
        col.put("enlargeAble",true);
        recordColumns.add(col);

        col = new HashMap<>();
        col.put("name","files");
        col.put("label","附件");
        col.put("type","input-file");
        col.put("multiple",true);
        col.put("disabled",true);
        recordColumns.add(col);

        records.put("columns",recordColumns);


        Map<String,Object> recordData = new HashMap<>();

        List<Map<String, Object>> recordList = jdbcService.find("select * from audit_record where table_name = ? and ref_id = ? order by end_time desc ", tableName, id);
        recordList.forEach(r->{
            Object startTime = r.get("startTime");
            Object endTime = r.get("endTime");
            r.put("startTime",DateUtil.format((LocalDateTime) startTime,"yyyy-MM-dd HH:mm:ss"));
            r.put("endTime",DateUtil.format((LocalDateTime) endTime,"yyyy-MM-dd HH:mm:ss"));
        });
        recordData.put("items",recordList);
        records.put("data",recordData);

        columns.add(1,records);
        actions = actions == null ? new ArrayList<>():actions;
//        if(!actions.isEmpty()){
//            actions.remove(actions.size()-1);
//        }
        if(task != null){
            actions.forEach(m->{
                m.put("redirect",StrUtil.format("/taskAudit/{}?t=",task.getId(), System.currentTimeMillis()));
            });
        }
        formJson.put("actions",actions);
        formJson.put("mode","horizontal");
        return "AMIS_JSON="+ JSONUtil.toJsonPrettyStr(formJson);
    }


    private AuditRecord createRecord(Map<String,Object> obj,Long id,String tableName,String statusDic,ProcessInstance processInstance,ProcessDefinition processDefinition,Task task,SequenceFlow node){
        AuditRecord record = new AuditRecord();
        record.setTableName(tableName);
        record.setRefId(id);
        User user = SessionContext.getUser();
        UserSession session = SessionContext.getSession();
        record.setAuditUserId(user.getId());
        record.setEnterpriseId(session.getEnterpriseId());
        record.setAuditUserName(user.getName());
        record.setProcessInstanceId(processInstance.getId());
        record.setDeploymentId(processDefinition.getDeploymentId());
        record.setTaskId(task.getId());
        record.setProcessDefinitionId(processInstance.getProcessDefinitionId());
        record.setNextStatus(node.getDocumentation());
        record.setNextStatusName(dicCacheService.getLabel(statusDic,node.getDocumentation()));
        record.setPrevStatus(task.getDescription());
        record.setPrevStatusName(dicCacheService.getLabel(statusDic,task.getDescription()));
        record.setTaskName(task.getName());
        record.setProcessKey(processInstance.getProcessDefinitionKey());
        record.setResultName(node.getName());
        record.setStartTime(task.getCreateTime());
        record.setEndTime(new Date());
        record.setStatusDic(statusDic);
        return record;
    }

    /**
     *  提交任务
     */
    @RequestMapping("/run")
    @ResponseBody
    public Object run(String processInstanceId) {
        Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstanceId).singleResult();
        task.getAssignee();
        processEngine.getTaskService().complete(task.getId());

        return "SUCCESS";
    }

    @RequestMapping("/getDeploymentMeta/{id}")
    public String getDeploymentMeta(@PathVariable String id){
        JSONObject metaInfo = activityService.getMetaInfo(id);
        if(metaInfo == null){
            metaInfo = new JSONObject();
        }

        return JSONUtil.toJsonPrettyStr(Result.success(metaInfo));
    }

    @RequestMapping("/getNextNode/{id}")
    public Object getTaskFlows(@PathVariable String id){
        List<SequenceFlow> nodes = activityService.getNextNode(id);
        return Result.success(nodes);
    }

    @RequestMapping("/task/complete/{id}/{result}")
    public Object taskComplete(@PathVariable String id,@PathVariable String result,@RequestBody Map<String,Object> body){
        Task task = processEngine.getTaskService().createTaskQuery().taskId(id).singleResult();
        if(task == null){
            return Result.error("任务已完成");
        }

        ProcessDefinition processDefinition = processEngine.
                getRepositoryService().
                createProcessDefinitionQuery().
                processDefinitionId(task.getProcessDefinitionId()).
                singleResult();
        ProcessInstance processInstance = processEngine.
                getRuntimeService().
                createProcessInstanceQuery().
                processInstanceId(task.getProcessInstanceId()).
                singleResult();
        JSONObject metaInfo = activityService.getMetaInfo(processDefinition.getDeploymentId());
        String viewForm = metaInfo.getStr(ModelExtraField.ViewForm);
        String tableName = metaInfo.getStr(ModelExtraField.TableName);
        String statusField = StringUtil.toFieldColumn(metaInfo.getStr(ModelExtraField.StatusField));
        String beforeApi = metaInfo.getStr(ModelExtraField.BeforeApi);
        String afterApi = metaInfo.getStr(ModelExtraField.AfterApi);
        String statusDic = metaInfo.getStr(ModelExtraField.StatusDic);
        Long refId = Long.valueOf(processInstance.getBusinessKey());
        Map<String, Object> obj = jdbcService.getById(tableName, refId);
        String objStatus = (String) obj.get(statusField);
        if(StringUtils.isBlank(objStatus)){
            return Result.error("审核失败,单据状态错误");
        }
        if(!objStatus.equals(task.getDescription())){
            return Result.error("审核失败,单据状态可能已经发生变化!");
        }
        Map<String, Object> params = processInstance.getProcessVariables();
        params.putAll(obj);
        params.put("prevStatus",objStatus);
        params.put("nextStatus",result);
        params.put("params",body);
        //executionId 和processInstanceId 是一样的
        params.put("processInstanceId",task.getProcessInstanceId());
        params.put("taskId",task.getId());
        Result<String> call = apiService.call(beforeApi, params);
        if(!call.isSuccess()){
            return call;
        }
        Map<String,Object> taskParams = new HashMap<>();
        taskParams.put("result",result);

        try{
            jdbcService.transactionOption(()->{

                //设置动态参数必须在任务完成之前设置,完成任务找不到参数直接报错
                Result<String> _call = apiService.call(afterApi, params);
                if(!_call.isSuccess()){
                    throw new RuntimeException(_call.getMsg());
                }

                processEngine.getTaskService().setOwner(id,SessionContext.getSession().getUserId().toString());
                processEngine.getTaskService().complete(id,taskParams);

                obj.put(statusField,result);
                jdbcService.update(obj,tableName);

                List<SequenceFlow> nodes = activityService.getNextNode(id);
                SequenceFlow node = null;
                for(SequenceFlow n:nodes){
                    if(n.getDocumentation().equals(result)){
                        node = n;
                        break;
                    }
                }

                AuditRecord record = createRecord(obj, refId, tableName, statusDic, processInstance,processDefinition, task, node);
                record.setProcessInstanceName(processInstance.getName());

                String auditRemark = (String)body.get("auditRemark");
                String auditImgs = (String)body.get("auditImgs");
                String auditFiles = (String)body.get("auditFiles");
                if(StringUtils.isNotBlank(auditRemark)){
                    record.setRemark(auditRemark);
                }
                if(StringUtils.isNotBlank(auditImgs)){
                    record.setImgs(auditImgs);
                }
                if(StringUtils.isNotBlank(auditImgs)){
                    record.setFiles(auditFiles);
                }
                jdbcService.saveOrUpdate(record);


            });
        }catch (Exception e){
            log.error("审核失败:"+e.getMessage(),e);
            return Result.error(e.getMessage());
        }

        return Result.success();
    }


}
