/*
 * Copyright 2019 liuburu@qq.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.demo.controller;

import cn.kiwipeach.demo.data.UsersDatabase;
import cn.kiwipeach.demo.domain.SysUser;
import cn.kiwipeach.demo.service.SysUserDaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 描述：控制器
 *
 * @author kiwipeach
 * @create 2019-03-30
 */
@Controller
@RequestMapping("demo/user")
public class EhcacheController {

    @Autowired
    private SysUserDaoServiceImpl sysUserDaoService;

    @Autowired
    CacheManager cacheManager;


    @GetMapping("reloadCache")
    @ResponseBody
    public String reloadEhcache() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        // 重置缓存
        for (String cacheName : cacheNames) {
            Cache cache = cacheManager.getCache(cacheName);
            cache.clear();
        }
        // 重置模拟数据库
        UsersDatabase.reset();
        return "重置缓存成功";
    }

    @GetMapping("query")
    @ResponseBody
    public Set<SysUser> querySysUser() {
        Set<SysUser> resultUsers = new HashSet<>();
        for (SysUser u : UsersDatabase.getDatabaseUsers()) {
            resultUsers.add(sysUserDaoService.queryById(u.getId()));
        }
        return resultUsers;
    }

    @GetMapping("query/{userId}")
    @ResponseBody
    public String querySysUser(@PathVariable("userId") String userId) {
        //第一种方法
        //SysUser sysUser = sysUserDaoService.queryById(userId);
        //第二种方法
        Cache sysuser_cache = cacheManager.getCache("SYSUSER_CACHE");
        SysUser sysUser = sysuser_cache.get("sysuser_id_" + userId, SysUser.class);
        if (sysUser == null) {
            return "查无此对象";
        }
        return "查询成功：" + sysUser.toString();
    }

    @GetMapping("update/{userId}")
    @ResponseBody
    public SysUser updateSysUser(@PathVariable("userId") String userId) {
        String updateUserName = "卡卡罗特-" + UUID.randomUUID().toString().replace("-", "").substring(0, 4);
        SysUser u = new SysUser(userId, updateUserName, "123456");
        SysUser sysUser = sysUserDaoService.updateUser(u);
        return sysUser;
    }

    @GetMapping("create")
    @ResponseBody
    public SysUser createSysUser() {
        String updateUserName = "卡卡罗特-" + UUID.randomUUID().toString().replace("-", "").substring(0, 4);
        SysUser u = new SysUser("10086", updateUserName, "123456");
        SysUser sysUser = sysUserDaoService.createUser(u);
        return sysUser;
    }

    @GetMapping("delete/{userId}")
    @ResponseBody
    public String deleteSusIser(@PathVariable("userId") String userId) {
        SysUser sysUser = sysUserDaoService.deleteSysUser(userId);
        return "删除结果:" + sysUser;
    }


}
