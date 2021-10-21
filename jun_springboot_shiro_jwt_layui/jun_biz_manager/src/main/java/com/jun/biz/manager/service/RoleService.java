package com.jun.biz.manager.service;

import java.util.Set;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.role.CreateRoleDTO;
import com.jun.biz.manager.dto.role.ListRoleDTO;
import com.jun.biz.manager.dto.role.ModifyRoleDTO;
import com.jun.biz.manager.vo.role.ListRoleVO;


public interface RoleService {


	ResultVO<Boolean> create(CreateRoleDTO dto);

	ResultVO<Boolean> modify(ModifyRoleDTO dto);
	ResultVO<Boolean> delete(Set<Long> id) ;

	ResultVO<ListRoleVO>  list(ListRoleDTO dto) ;
	
	ResultVO<Set<Long>> selectPermissionIds(Long id);
}
