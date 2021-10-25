package com.reading.app.controller;

import com.reading.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.reading.app.domain.User;
import com.reading.app.service.IUserService;

/**用户
 * @author cj
 */
@Controller
@RequestMapping("/app/user")
public class AppUserController {

    @Autowired
    IUserService userService;


    /**
     * 根据用户编号获取用户详细信息
     * @param aid
     * @return
     */
    @GetMapping("{aid}")
    @ResponseBody
    public AjaxResult getByAid(@PathVariable("aid") int aid){
        return AjaxResult.success("查询成功",userService.selectUserByAid(aid));
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateUser(@RequestBody User user){
        int ret = userService.updateUser(user);
        if(ret>0){
            return AjaxResult.success("更新成功");
        }
        return AjaxResult.error("更新用户失败");
    }


}
