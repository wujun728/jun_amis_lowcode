package com.jun.biz.manager.mapstruct;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.jun.biz.manager.dto.permission.CreatePermissionDTO;
import com.jun.biz.manager.dto.permission.ModifyPermissionDTO;
import com.jun.biz.manager.model.Permission;
import com.jun.biz.manager.vo.admin.PermissionVO;

import java.util.List;
import java.util.Set;

/**
 * Created on 2020/10/14 18:51
 * <p>
 *
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionConverter {

    Set<PermissionVO> toVo(Set<Permission> entityList);

    List<PermissionVO> toVo(List<Permission> entityList);

    Permission createDtoToEntity(CreatePermissionDTO dto);

    Permission modifyDtoToEntity(ModifyPermissionDTO dto);
}
