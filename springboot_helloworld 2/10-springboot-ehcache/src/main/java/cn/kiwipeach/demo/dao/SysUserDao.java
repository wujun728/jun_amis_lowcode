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
package cn.kiwipeach.demo.dao;

import cn.kiwipeach.demo.data.UsersDatabase;
import cn.kiwipeach.demo.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * 描述：模拟数据库持久层操作类,持久层只做持久层该干的事情
 *
 * @author Wujun
 * @create 2019-03-30
 */
@Repository
@Slf4j
public class SysUserDao {

    /**
     * 模拟插入
     */
    public int insert(SysUser sysUser) {
        UsersDatabase.getDatabaseUsers().add(sysUser);
        log.info("(模拟SQL)插入用户信息成功:{}", sysUser);
        return 1;
    }

    /**
     * 模拟删除
     */
    public SysUser deleteById(String id) {
        Set<SysUser> databaseUsers = UsersDatabase.getDatabaseUsers();
        if (StringUtils.isEmpty(id)) {
            throw new RuntimeException("当前用户ID不能为空");
        }
        for (SysUser u : databaseUsers) {
            if (u.getId().equals(id)) {
                databaseUsers.remove(u);
                log.info("(模拟SQL)删除用户信息成功:Id={}", id);
                return u;
            }
        }
        return null;
    }

    /**
     * 更新系统用户
     */
    public int update(SysUser sysUser) {
        String id = sysUser.getId();
        if (StringUtils.isEmpty(id)) {
            throw new RuntimeException("当前用户ID不能为空");
        }
        for (SysUser u : UsersDatabase.getDatabaseUsers()) {
            if (u.getId().equals(id)) {
                u.setUserName(sysUser.getUserName());
                u.setPassword(sysUser.getPassword());
                log.info("(模拟SQL)更新用户信息成功:{}", sysUser);
                return 1;
            }
        }
        return 0;

    }

    /**
     * 查询用户信息
     */
    public SysUser selectById(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new RuntimeException("当前用户ID不能为空");
        }
        for (SysUser u : UsersDatabase.getDatabaseUsers()) {
            if (u.getId().equals(id)) {
                log.info("(模拟SQL)查询用户信息成功:{}", u);
                return u;
            }
        }
        return null;
    }

}
