package com.lanyu.admin.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanyu.admin.service.ModuleService;
import com.lanyu.common.model.Module;
import com.lanyu.common.model.vo.ResponseData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/module")
public class ModuleController {
    private Logger logger = Logger.getLogger(ModuleController.class);
    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "/modulePage")
    public String userListPage(Map<String,Object> map){
        return "admin/module_list";
    }

    @ResponseBody
    @RequestMapping(value = "/selectModule", produces = {"application/json;charset=UTF-8"})
    public PageInfo selectModule(HttpServletRequest request){
        HashMap<String,Object> queryparm = new HashMap<String,Object>();
        int limit = Integer.parseInt(request.getParameter("limit"));
        int offset = Integer.parseInt(request.getParameter("offset"));
        String searchText = request.getParameter("search");
        if(!"".equals(searchText)&&searchText!=null){
            queryparm.put("searchText","%"+searchText+"%");
        }
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        queryparm.put("sort",sort);
        queryparm.put("order",order);
        PageHelper.offsetPage(offset,limit);
        List<Module> users = moduleService.selectModuleList(queryparm);
        PageInfo pageInfo=new PageInfo(users);
        return pageInfo;
    }

    @ResponseBody
    @RequestMapping(value = "/selectZtreeModule", produces = {"application/json;charset=UTF-8"})
    public List<Module> selectZtreeModule(HttpServletRequest request){
        List<Module> modules = new ArrayList<Module>();
        try {
            HashMap param = new HashMap();
            modules= moduleService.selectModuleList(param);
            modules=putChildren(modules);
        } catch (Exception e) {
            modules=null;
        }
        return modules;
    }

    public List<Module> putChildren(List<Module> moduleList){
        if(moduleList.size()>0){
            for(Module mod2:moduleList){
                HashMap param = new HashMap();
                param.put("parent",mod2.getMcode());
                List<Module> res2 = moduleService.selectModuleList(param);
                if(res2.size()>0){res2=putChildren(res2);}
                mod2.setChildren(res2);
            }
        }
        return moduleList;
    }

    @ResponseBody
    @RequestMapping(value = "/addModule", produces = {"application/json;charset=UTF-8"})
    public ResponseData addModule(Module module){
        ResponseData res = new ResponseData();
        try {
            moduleService.insert(module);
            res.setSuccess(true);
            res.setMsg("操作成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg(e.getMessage());
        }
        return res;
    }
    @ResponseBody
    @RequestMapping(value = "/editModule", produces = {"application/json;charset=UTF-8"})
    public ResponseData editModule(Module module){
        ResponseData res = new ResponseData();
        try {
            moduleService.updateByKeySelective(module);
            res.setSuccess(true);
            res.setMsg("操作成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg(e.getMessage());
        }
        return res;
    }
    @ResponseBody
    @RequestMapping(value = "/delModule", produces = {"application/json;charset=UTF-8"})
    public ResponseData delModule(@RequestParam("mcode") String mcode){
        ResponseData res = new ResponseData();
        try {
            moduleService.deleteByKey(mcode);
            res.setSuccess(true);
            res.setMsg("操作成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg(e.getMessage());
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/getzTreeModule", produces = {"application/json;charset=UTF-8"})
    public  List<HashMap<String, Object>> getzTreeModule(@RequestParam("rcode") String rcode){
        List<HashMap<String, Object>> res = new ArrayList<HashMap<String, Object>>();
        try {
            res = moduleService.selectRoleModule(rcode);
        } catch (Exception e) {
            res=null;
        }
        return res;
    }
}
