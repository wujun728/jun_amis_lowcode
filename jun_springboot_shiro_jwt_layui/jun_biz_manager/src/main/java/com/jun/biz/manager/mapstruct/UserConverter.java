
package com.jun.biz.manager.mapstruct;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.jun.biz.manager.dto.user.CreateUserDTO;
import com.jun.biz.manager.dto.user.ModifyUserDTO;
import com.jun.biz.manager.model.User;
import com.jun.biz.manager.vo.user.UserVO;

import java.util.List;

/**
 * Created on 2020/10/14 18:51
 * <p>
 *
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {

    List<UserVO> toVo(List<User> entityList);

    User createDtoToEntity(CreateUserDTO dto);

    User modifyDtoToEntity(ModifyUserDTO dto);
}
