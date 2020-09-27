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
package cn.kiwipeach.demo.service;

import cn.kiwipeach.demo.dao.SysUserDao;
import cn.kiwipeach.demo.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 描述：用户服务实现类
 *
 * @author kiwipeach
 * @create 2019-03-30
 */
@Service
@CacheConfig(cacheNames = {"SYSUSER_CACHE"})
public class SysUserDaoServiceImpl {

    //* @Cacheable : Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
    //* @CacheEvict : 清除缓存。
    //* @CachePut : @CachePut也可以声明一个方法支持缓存功能。使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。

    @Autowired
    private SysUserDao sysUserDao;


    @Cacheable(key = "'sysuser_id_'+#userId")
    public SysUser queryById(String userId) {
        //缓存中没有查询到，将会查询数据库
        return sysUserDao.selectById(userId);
    }

    @Cacheable(key = "'sysuser_id_'+#sysUser.id")
    public SysUser createUser(SysUser sysUser) {
        //数据库插入，同时缓存当前插入对象
        sysUserDao.insert(sysUser);
        return sysUser;
    }

    @CachePut(key = "'sysuser_id_'+#sysUser.id")
    public SysUser updateUser(SysUser sysUser) {
        //数据库更新，同时更新缓存
        sysUserDao.update(sysUser);
        return sysUser;
    }

    @CacheEvict(key = "'sysuser_id_'+#userId")
    public SysUser deleteSysUser(String userId) {
        //数据库删除，同时让缓存失效
        SysUser sysUser = sysUserDao.deleteById(userId);
        return sysUser;
    }

}
