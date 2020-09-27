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
package cn.kiwipeach.demo.data;

import cn.kiwipeach.demo.domain.SysUser;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 描述：模拟内存数据库
 *
 * @author kiwipeach
 * @create 2019-03-30
 */
public class UsersDatabase {

    private static Set<SysUser> sysUsers = new LinkedHashSet<SysUser>();

    static {
        sysUsers.add(new SysUser("1001", "kiwipeach-1", "123456"));
        sysUsers.add(new SysUser("1002", "kiwipeach-2", "123456"));
        sysUsers.add(new SysUser("1003", "kiwipeach-3", "123456"));
        sysUsers.add(new SysUser("1004", "kiwipeach-4", "123456"));
        sysUsers.add(new SysUser("1005", "kiwipeach-5", "123456"));
    }

    /**
     * 获取所有的用户数据
     *
     * @return
     */
    public static Set<SysUser> getDatabaseUsers() {
        return sysUsers;
    }

    /**
     * 重置数据库状态
     */
    public static void reset() {
        sysUsers.clear();
        sysUsers.add(new SysUser("1001", "kiwipeach-1", "123456"));
        sysUsers.add(new SysUser("1002", "kiwipeach-2", "123456"));
        sysUsers.add(new SysUser("1003", "kiwipeach-3", "123456"));
        sysUsers.add(new SysUser("1004", "kiwipeach-4", "123456"));
        sysUsers.add(new SysUser("1005", "kiwipeach-5", "123456"));
    }

}
