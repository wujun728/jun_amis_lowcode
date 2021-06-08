package com.lanyu.admin.controller;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanyu.admin.service.RoleService;
import com.lanyu.common.model.Role;
import com.lanyu.common.model.vo.ResponseData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {
    private Logger logger = Logger.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/listPage")
    public String listPage(){
        return "admin/role_list";
    }

    @ResponseBody
    @RequestMapping(value = "/selectRoles", produces = {"application/json;charset=UTF-8"})
    public PageInfo selectRoles(HttpServletRequest request){
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
        List<Role> users = roleService.selectRoleList(queryparm);
        PageInfo pageInfo=new PageInfo(users);
        return pageInfo;
    }

    @ResponseBody
    @RequestMapping(value = "/addRole", produces = {"application/json;charset=UTF-8"})
    public ResponseData addRole(HttpServletRequest request){
        ResponseData res = new ResponseData();
        try {
            HashMap<String,Object> role = new HashMap<String,Object>();
            role.put("rcode",request.getParameter("rcode"));
            role.put("rname",request.getParameter("rname"));
            role.put("flag",Integer.parseInt(request.getParameter("flag")));
            roleService.insert(role);
            res.setSuccess(true);
            res.setMsg("操作成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg(e.getMessage());
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/editRole", produces = {"application/json;charset=UTF-8"})
    public ResponseData editRole(HttpServletRequest request){
        ResponseData res = new ResponseData();
        try {
            HashMap role = new HashMap();
            role.put("rcode",request.getParameter("rcode"));
            role.put("rname",request.getParameter("rname"));
            role.put("oldrcode",request.getParameter("oldrcode"));
            role.put("flag",Integer.parseInt(request.getParameter("flag")));
            roleService.update(role);
            res.setSuccess(true);
            res.setMsg("操作成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg(e.getMessage());
        }
        return res;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteRole", produces = {"application/json;charset=UTF-8"})
    public ResponseData deleteRole(@RequestParam("rcode")String rcode){
        ResponseData res = new ResponseData();
        try {
            roleService.deleteRole(rcode);
            res.setSuccess(true);
            res.setMsg("操作成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg(e.getMessage());
        }
        return res;
    }



    @ResponseBody
    @RequestMapping(value = "/editRoleMdoules", produces = {"application/json;charset=UTF-8"})
    public ResponseData editRoleMdoules(HttpServletRequest request){
        ResponseData res = new ResponseData();
        try {
            String rcode = request.getParameter("rcode");
            String rolejson=request.getParameter("moduleList");
            List<HashMap>rolemodules = (List<HashMap>) JSON.parse(rolejson);
            roleService.editModules(rolemodules,rcode);
            res.setSuccess(true);
            res.setMsg("操作成功");
        } catch (Exception e) {
            res.setSuccess(false);
            e.printStackTrace();
            res.setMsg("系统异常");
        }
        return res;
    }
    /**
     * 获取用户角色多选多数据
     */
    @ResponseBody
    @RequestMapping(value = "/getMultselectRoles", produces = {"application/json;charset=UTF-8"})
    public ResponseData getMultselectRoles(@RequestParam("uid")int uid){
        ResponseData res = new ResponseData();
        try {
            List<Map> rolelist=roleService.getMultselectRoles(uid);
            res.setSuccess(true);
            res.setData(rolelist);
            res.setMsg("操作成功");
        } catch (Exception e) {
            res.setSuccess(false);
            e.printStackTrace();
            res.setMsg("系统异常");
        }
        return res;
    }
    /**
     * 修改用户角色多选多数据
     */
    @ResponseBody
    @RequestMapping(value = "/editUserRoles", produces = {"application/json;charset=UTF-8"})
    public ResponseData editUserRoles(HttpServletRequest request){
        ResponseData res = new ResponseData();
        try {
            int uid = Integer.parseInt(request.getParameter("uid"));
            String rolejson=request.getParameter("multselect");
            List<HashMap>  rolemodules = (List<HashMap>) JSON.parse(rolejson);
            roleService.editUserRole(rolemodules,uid);
            res.setSuccess(true);
            res.setMsg("操作成功");
        } catch (Exception e) {
            res.setSuccess(false);
            e.printStackTrace();
            res.setMsg("系统异常");
        }
        return res;
    }
}
