
package com.jun.biz.manager.mapstruct;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.jun.biz.manager.dto.admin.AdminDTO;
import com.jun.biz.manager.model.Admin;
import com.jun.biz.manager.vo.admin.AdminVO;

import java.util.List;

/**
 * Created on 2020/10/14 18:51
 * <p>
 *
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminConverter {

    List<AdminVO> toVo(List<Admin> entityList);

    AdminVO toVo(Admin admin);

    Admin toEntity(AdminDTO dto);

}
