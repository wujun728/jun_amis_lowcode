package com.jqp.admin.flow.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jqp.admin.activity.constants.ModelExtraField;
import com.jqp.admin.activity.data.AuditRecord;
import com.jqp.admin.common.Result;
import com.jqp.admin.common.config.SessionContext;
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
import com.jqp.admin.page.constants.ActionType;
import com.jqp.admin.page.constants.DataType;
import com.jqp.admin.page.constants.Whether;
import com.jqp.admin.page.data.BaseButton;
import com.jqp.admin.page.data.Form;
import com.jqp.admin.page.data.FormButton;
import com.jqp.admin.page.data.FormField;
import com.jqp.admin.page.service.DicService;
import com.jqp.admin.page.service.FormService;
import com.jqp.admin.rbac.data.User;
import com.jqp.admin.rbac.service.ApiService;
import com.jqp.admin.util.StringUtil;
import com.jqp.admin.util.TemplateUtil;
import com.jqp.admin.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.ssssssss.script.MagicScriptContext;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/admin/flow")
@Slf4j
public class FlowController {
    @Resource
    private JdbcService jdbcService;
    @Resource
    private FlowService flowService;
    @Resource
    private DicService dicService;
    @Resource
    private FormService formService;
    @Resource
    private ApiService apiService;

    @GetMapping("/editor/{id}")
    public String editor(@PathVariable(name="id") Long id, Model model){
        Flow flow = jdbcService.getById(Flow.class, id);
        String graphData = flow.getGraphData();
        if(StringUtils.isBlank(graphData)){
            graphData = "{}";
        }
        model.addAttribute("graphData",graphData);
        model.addAttribute("readOnly",false);
        model.addAttribute("showProperties",true);
        List<Map<String, Object>> options = dicService.options(flow.getStatusDic());
        model.addAttribute("statusOptions",JSONUtil.toJsonStr(options));
        return "flow";
    }
    @PostMapping("/saveGraphData/{id}")
    @ResponseBody
    public Result saveGraphData(@PathVariable(name="id") Long id, @RequestParam Map<String,Object> data){
        String graphData = (String) data.get("graphData");
        Flow flow = jdbcService.getById(Flow.class, id);
        flow.setGraphData(graphData);
        jdbcService.saveOrUpdate(flow);
        return Result.success();
    }
    @GetMapping("/deploy/{id}")
    @ResponseBody
    public Result deploy(@PathVariable(name="id") Long id){
        flowService.deploy(jdbcService.getById(Flow.class,id));
        return Result.success();
    }

    @GetMapping("/start/{code}/{id}/{name}")
    @ResponseBody
    public Result start(@PathVariable(name="code") String code,
                        @PathVariable(name="id") Long id,
                        @PathVariable(name="name") String name){
        flowService.start(code,id,name);
        return Result.success();
    }

    @GetMapping("/deployView/{id}")
    public String deployView(@PathVariable(name="id") Long id, Model model){
        FlowDeploy deploy = jdbcService.getById(FlowDeploy.class, id);
        String graphData = deploy.getGraphData();
        if(StringUtils.isBlank(graphData)){
            graphData = "{}";
        }
        model.addAttribute("graphData",graphData);
        model.addAttribute("readOnly",true);
        model.addAttribute("showProperties",true);
        List<Map<String, Object>> options = dicService.options(deploy.getStatusDic());
        model.addAttribute("statusOptions",JSONUtil.toJsonStr(options));
        return "flow";
    }
    @GetMapping("/instanceView/{id}")
    public String instanceView(@PathVariable(name="id") Long id, Model model){
        FlowInstance flowInstance = jdbcService.getById(FlowInstance.class, id);
        FlowDeploy deploy = jdbcService.getById(FlowDeploy.class, flowInstance.getFlowDeployId());
        String graphData = deploy.getGraphData();
        if(StringUtils.isBlank(graphData)){
            graphData = "{}";
        }

        GraphContext context = new GraphContext(graphData);
        Node startNode = context.getStartNode();
        startNode.getProperties().setActive("true");
        context.getEdges(startNode.getId()).get(0).getProperties().setActive("true");

        List<FlowInstanceTask> flowInstanceTasks = jdbcService.find(FlowInstanceTask.class, new String[]{
                FlowInstanceTask.Fields.flowInstanceId
        }, new Object[]{
                flowInstance.getId()
        });
        for(FlowInstanceTask flowInstanceTask:flowInstanceTasks){
            Node node = context.getNode(flowInstanceTask.getTaskId());
            node.getProperties().setActive("true");
            Edge edge = context.getEdge(flowInstanceTask.getEdgeId());
            if(edge != null){
                edge.getProperties().setActive("true");
            }
        }
        if(flowInstance.getEndTime() != null){
            context.getEndNode().getProperties().setActive("true");
        }
        Node node = context.getNode(flowInstance.getCurrentTaskId());
        node.getProperties().setCurrent("true");
        List<Edge> edges = context.getEdges(node.getId());
        for (Edge edge : edges) {
            edge.getProperties().setCurrent("true");
        }

        model.addAttribute("graphData",JSONUtil.toJsonStr(context.getGraphData()));
        model.addAttribute("readOnly",true);
        model.addAttribute("showProperties",false);
        List<Map<String, Object>> options = dicService.options(deploy.getStatusDic());
        model.addAttribute("statusOptions",JSONUtil.toJsonStr(options));
        return "flow";
    }

    @RequestMapping("/taskAudit/{taskId}")
    public String taskAudit(Model model,@PathVariable("taskId") String taskId){
        model.addAttribute("js","/admin/flow/taskAudit/js/"+taskId+".js?_rt="+System.currentTimeMillis());
        return "page";
    }
    @RequestMapping("/taskView/{taskId}")
    public String taskView(Model model,@PathVariable("taskId") String taskId){
        model.addAttribute("js","/admin/flow/taskView/js/"+taskId+".js?_rt="+System.currentTimeMillis());
        return "page";
    }
    @RequestMapping("/auditRecord/{modelName}/{id}")
    public String auditRecord(Model model,@PathVariable("modelName") String modelName,@PathVariable("id") String id ){
        model.addAttribute("js","/admin/flow/auditRecord/js/"+modelName+"/"+id+".js?_rt="+System.currentTimeMillis());
        return "page";
    }

    /**
     *  任务js
     */
    @RequestMapping(value="/taskAudit/js/{taskId}.js",produces = "text/javascript; charset=utf-8")
    @ResponseBody
    public Object taskAudit(@PathVariable Long taskId) {

        FlowInstanceTask task = jdbcService.getById(FlowInstanceTask.class, taskId);

        return auditJs(task,task.getFlowInstanceId(),false);
    }
    /**
     *  任务js
     */
    @RequestMapping(value="/taskView/js/{taskId}.js",produces = "text/javascript; charset=utf-8")
    @ResponseBody
    public Object taskView(@PathVariable Long taskId) {
        FlowInstanceTask task = jdbcService.getById(FlowInstanceTask.class, taskId);
        return auditJs(task,task.getFlowInstanceId(),true);
    }

    /**
     *  任务js
     */
    @RequestMapping(value="/auditRecord/js/{model}/{id}.js",produces = "text/javascript; charset=utf-8")
    @ResponseBody
    public Object auditRecordJs(@PathVariable String model,@PathVariable Long id) {
        String tableName = StringUtil.toSqlColumn(model);

        Long flowInstanceId = jdbcService.findOneForObject("select fi.id from flow_instance fi " +
                "left join flow_deploy fd on fd.id = fi.flow_deploy_id " +
                "where fd.table_name = ? and fi.ref_id = ? order by fi.start_time desc ", Long.class, tableName, id);

        if(flowInstanceId == null){
            Map<String,Object> json = new HashMap<>();
            json.put("type","page");
            json.put("body","暂无审核记录");
            return "AMIS_JSON="+JSONUtil.toJsonPrettyStr(json);
        }
        return auditJs(null,flowInstanceId,true);
    }

    private String auditJs(FlowInstanceTask task,Long flowInstanceId,boolean view){
        FlowInstance flowInstance = jdbcService.getById(FlowInstance.class,flowInstanceId);
        FlowDeploy flowDeploy = jdbcService.getById(FlowDeploy.class,flowInstance.getFlowDeployId());

        String viewForm = flowDeploy.getViewForm();
        String tableName = flowDeploy.getTableName();
        String statusField = flowDeploy.getStatusField();
        String beforeApi = flowDeploy.getBeforeApi();
        String afterApi = flowDeploy.getAfterApi();
        GraphContext graphContext = new GraphContext(flowDeploy.getGraphData());
        if(task != null && org.apache.commons.lang.StringUtils.isNotBlank(task.getViewForm())){
            viewForm = task.getViewForm();
        }
        Form f = formService.get(viewForm);
        f = Util.clone(f);
        if(view){
            f.setDisabled(Whether.YES);
        }
        BaseButton baseButton = new BaseButton();
        baseButton.setLabel("审核");
        if(task != null){
            List<Edge> edges = graphContext.getEdges(task.getTaskId());
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

                for(Edge edge:edges){
                    FormButton formButton = new FormButton();
                    formButton.setLabel(edge.getText().getValue());
                    formButton.setOptionType(ActionType.IframeAjax);
                    formButton.setOptionValue(StrUtil.format("post:/admin/flow/task/complete/{}/{}",
                            task.getId(),edge.getId()));
                    if(Whether.NO.equals(edge.getProperties().getPass())){
                        formButton.setLevel("danger");
                        formButton.setJsRule("auditRemark==''||auditRemark==null");
                    }else{
                        formButton.setLevel("primary");
                    }
                    f.getFormButtons().add(formButton);
                }
            }
        }

        Long id = flowInstance.getRefId();
        Map<String, Object> obj = jdbcService.getById(tableName, id);

        Map<String, Object> dialog = formService.getFormJson(f, baseButton);
        Map<String,Object> formJson = (Map<String, Object>) dialog.get("body");
        String initApi = (String) formJson.get("initApi");
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
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
        flowImg.put("src","/admin/flow/instanceView/"+flowInstance.getId());
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
        col.put("name","edgeName");
        col.put("label","审核结果");
        recordColumns.add(col);

        col = new HashMap<>();
        col.put("name","auditRemark");
        col.put("label","审核备注");
        recordColumns.add(col);

        col = new HashMap<>();
        col.put("name","auditImgs");
        col.put("label","图片");
        col.put("type","images");
        col.put("enlargeAble",true);
        recordColumns.add(col);

        col = new HashMap<>();
        col.put("name","auditFiles");
        col.put("label","附件");
        col.put("type","input-file");
        col.put("multiple",true);
        col.put("disabled",true);
        recordColumns.add(col);

        records.put("columns",recordColumns);


        Map<String,Object> recordData = new HashMap<>();

        List<Map<String, Object>> recordList = jdbcService.find("select * from flow_instance_task where flow_instance_id = ? and end_time is not null order by end_time desc,id desc ", flowInstance.getId());

//        List<Map<String, Object>> recordList = jdbcService.find("select * from audit_record where table_name = ? and ref_id = ? order by end_time desc ", tableName, id);
        recordList.forEach(r->{
            Object startTime = r.get("startTime");
            Object endTime = r.get("endTime");
            r.put("startTime", DateUtil.format((LocalDateTime) startTime,"yyyy-MM-dd HH:mm:ss"));
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
        if(view){
            actions.clear();
        }
        formJson.put("actions",actions);
        formJson.put("mode","horizontal");
        return "AMIS_JSON="+ JSONUtil.toJsonPrettyStr(formJson);
    }

    @RequestMapping("/task/complete/{id}/{edgeId}")
    @ResponseBody
    public Object taskComplete(@PathVariable Long id,@PathVariable String edgeId,@RequestBody Map<String,Object> body){
        flowService.completeTask(id,edgeId,body);
        return Result.success();
    }
}
