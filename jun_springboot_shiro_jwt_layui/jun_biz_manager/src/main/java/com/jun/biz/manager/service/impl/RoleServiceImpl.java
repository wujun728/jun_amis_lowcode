
package com.jun.biz.manager.service.impl;

import org.springframework.stereotype.Service;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.common.runtime.AdminSessionHolder;
import com.jun.biz.manager.dao.RoleDao;
import com.jun.biz.manager.dao.RolePermissionDao;
import com.jun.biz.manager.dto.role.CreateRoleDTO;
import com.jun.biz.manager.dto.role.ListRoleDTO;
import com.jun.biz.manager.dto.role.ModifyRoleDTO;
import com.jun.biz.manager.mapstruct.RoleConverter;
import com.jun.biz.manager.model.Role;
import com.jun.biz.manager.model.RolePermission;
import com.jun.biz.manager.service.RoleService;
import com.jun.biz.manager.vo.role.ListRoleVO;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created on 2020/10/14 20:12
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleConverter roleConverter;
    @Resource
    private RoleDao roleDao;
    @Resource
    private RolePermissionDao rolePermissionDao;

    @Override
    public ResultVO<ListRoleVO> list(ListRoleDTO dto) {
        Map<String, Object> cond = dto.toMap();
        long count = roleDao.count(cond);
        List<Role> roles = roleDao.selectList(cond);
        ListRoleVO vo = new ListRoleVO();
        vo.setTotal(count);
        vo.setList(roleConverter.toVo(roles));
        return ResultVO.buildSuccessResult(vo);
    }

    @Override
    public ResultVO<Set<Long>> selectPermissionIds(Long id) {
        List<Long> codes = roleDao.selectPermissionIds(id);
        return ResultVO.buildSuccessResult(new HashSet<>(codes));
    }

    @Override
    public ResultVO<Boolean> create(CreateRoleDTO dto) {
        Role entity = roleConverter.createDtoToEntity(dto);
        entity.setCreateTime(new Date());
        entity.setCreateAdmin(AdminSessionHolder.getCurrentAdmin().getUsername());
        roleDao.insert(entity);
        for (Long permissionId : dto.getPermissionIds()) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(entity.getId());
            rolePermissionDao.insert(rolePermission);
        }
        return ResultVO.buildSuccessResult();
    }

    @Override
    public ResultVO<Boolean> modify(ModifyRoleDTO dto) {
        Role role = roleDao.selectByPk(dto.getId());
        if (role == null) {
            return ResultVO.buildFailResult("角色不存在");
        }
        Role entity = roleConverter.modifyDtoToEntity(dto);
        roleDao.update(entity);
        rolePermissionDao.deleteByRole(dto.getId());
        for (Long permissionId : dto.getPermissionIds()) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(dto.getId());
            rolePermissionDao.insert(rolePermission);
        }
        return ResultVO.buildSuccessResult();
    }

    @Override
    public ResultVO<Boolean> delete(Set<Long> ids) {
        for (Long id : ids) {
            roleDao.delete(id);
        }
        return ResultVO.buildSuccessResult();
    }

}
