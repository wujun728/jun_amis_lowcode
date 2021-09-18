package com.jun.biz.manager.service;

import java.util.Set;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.user.ChangeStatusDTO;
import com.jun.biz.manager.dto.user.ListUserDTO;
import com.jun.biz.manager.dto.user.ModifyUserDTO;
import com.jun.biz.manager.vo.user.ListUserVO;


public interface UserService {


	ResultVO<Boolean> modify(ModifyUserDTO dto);

	ResultVO<Boolean> delete(Set<Long> ids);

	ResultVO<ListUserVO> list(ListUserDTO dto);


    ResultVO<Boolean> changeStatus(ChangeStatusDTO dto);

}
