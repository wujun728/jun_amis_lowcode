package com.element.modules.sys.service.impl;

import com.element.common.utils.Constant;
import com.element.modules.sys.mapper.SysUserMapper;
import com.element.modules.sys.entity.SysUserEntity;
import com.element.modules.sys.entity.SysUserTokenEntity;
import com.element.modules.sys.service.ShiroService;
import com.element.modules.sys.mapper.SysMenuMapper;
import com.element.modules.sys.mapper.SysUserTokenMapper;
import com.element.modules.sys.entity.SysMenuEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;
        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            List<SysMenuEntity> menuList = sysMenuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (SysMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserMapper.listPermsByUserId(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity getTokenInfo(String token) {
        return sysUserTokenMapper.getTokenInfo(token);
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return sysUserMapper.selectById(userId);
    }
}
