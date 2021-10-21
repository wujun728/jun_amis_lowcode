package com.jun.biz.manager.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jun.biz.common.annotation.PerCode;
import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.user.ChangeStatusDTO;
import com.jun.biz.manager.dto.user.ListUserDTO;
import com.jun.biz.manager.dto.user.ModifyUserDTO;
import com.jun.biz.manager.service.UserService;
import com.jun.biz.manager.vo.user.ListUserVO;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Resource
	private UserService userService;

	@PerCode("user:mgt")
	@GetMapping("list")
	public ResultVO<ListUserVO> list(ListUserDTO dto) {
		return userService.list(dto);
	}

	@PerCode("user:update")
	@PostMapping("modify")
	public ResultVO<Boolean> modify(@Validated @RequestBody ModifyUserDTO dto) {
		return userService.modify(dto);
	}

	@PerCode("user:del")
	@DeleteMapping("delete")
	public ResultVO<Boolean> delete(@RequestBody @Validated @NotNull @Size(min = 1) Set<Long> ids) {
		return userService.delete(ids);
	}

	@PerCode("user:status")
	@PostMapping("change-status")
	public ResultVO<Boolean> changeStatus(@Validated @RequestBody ChangeStatusDTO dto) {
		return userService.changeStatus(dto);
	}

}
