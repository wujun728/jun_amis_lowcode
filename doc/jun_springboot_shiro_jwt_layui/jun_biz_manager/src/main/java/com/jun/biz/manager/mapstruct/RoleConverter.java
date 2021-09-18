
package com.jun.biz.manager.mapstruct;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.jun.biz.manager.dto.role.CreateRoleDTO;
import com.jun.biz.manager.dto.role.ModifyRoleDTO;
import com.jun.biz.manager.model.Role;
import com.jun.biz.manager.vo.role.RoleVO;

import java.util.List;

/**
 * Created on 2020/10/14 18:51
 * <p>
 *
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleConverter {

    List<RoleVO> toVo(List<Role> entityList);

    Role createDtoToEntity(CreateRoleDTO dto);

    Role modifyDtoToEntity(ModifyRoleDTO dto);
}
