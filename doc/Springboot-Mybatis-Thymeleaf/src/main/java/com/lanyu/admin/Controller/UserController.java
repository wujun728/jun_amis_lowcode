package com.lanyu.admin.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanyu.admin.service.UserService;
import com.lanyu.common.model.User;
import com.lanyu.common.model.vo.ResponseData;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户列表
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * @param page
     * @return 用户列表
     */
    @RequestMapping(value = "/gotoPage/{page}")
    public String userListPage(@PathVariable("page") String page){
        return "admin/"+page;
    }

    /**
     * @param user
     * @return 添加用户
     */
    @ResponseBody
    @RequestMapping(value = "/addUser", produces = {"application/json;charset=UTF-8"})
    public ResponseData addUser(User user){
        ResponseData res = new ResponseData();
        try {
            userService.insert(user);
            res.setSuccess(true);
            res.setMsg("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setMsg("系统异常，添加失败");
        }
        return res;
    }

    /**
     * @param user
     * @return 编辑用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/editUser", produces = {"application/json;charset=UTF-8"})
    public ResponseData editUser(User user){
        ResponseData res = new ResponseData();
        try {
            userService.update(user);
            res.setSuccess(true);
            res.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setMsg("系统异常，添加失败");
        }
        return res;
    }

    /**
     * 批量重置密码
     */
    @ResponseBody
    @RequestMapping(value = "/resetPw", produces = {"application/json;charset=UTF-8"})
    public ResponseData resetPw(HttpServletRequest request){
        ResponseData res = new ResponseData();
        try {
            String uidstr = request.getParameter("uidstr");
            String unamestr = request.getParameter("unamestr");
            userService.batchResetpw(uidstr,unamestr);
            res.setSuccess(true);
            res.setMsg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setMsg("系统异常，添加失败");
        }
        return res;
    }
    /**
     * 批量重置密码
     */
    @ResponseBody
    @RequestMapping(value = "/deleteUser", produces = {"application/json;charset=UTF-8"})
    public ResponseData deleteUser(HttpServletRequest request){
        ResponseData res = new ResponseData();
        try {
            String uidstr = request.getParameter("uidstr").toString();
            userService.deleteUser(uidstr);
            res.setSuccess(true);
            res.setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setMsg("系统异常，删除失败");
        }
        return res;
    }
    /**
     * 根据用户名或手机号获取用户信息，包含角色，权限
     */
    @ResponseBody
    @RequestMapping(value = "/getUserInfo", produces = {"application/json;charset=UTF-8"})
    public User getUserInfo(@RequestParam("username")String username){
        return userService.selectByPhone(username);
    }

    @ResponseBody
    @RequestMapping(value = "/selectUsers", produces = {"application/json;charset=UTF-8"})
    public PageInfo findAllUser(HttpServletRequest request){
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
        List<Map<String,Object>> users = userService.findAllUser(queryparm);
        PageInfo pageInfo=new PageInfo(users);
        return pageInfo;
    }

    public ResponseData editPortrait(HttpServletRequest request, HttpServletResponse response){
        ResponseData res = new ResponseData();

        return res;
    }
}
