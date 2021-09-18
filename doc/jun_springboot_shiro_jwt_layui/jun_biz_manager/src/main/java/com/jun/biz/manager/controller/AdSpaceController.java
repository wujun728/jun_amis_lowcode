package com.jun.biz.manager.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jun.biz.common.annotation.PerCode;
import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.ad.space.CreateAdSpaceDTO;
import com.jun.biz.manager.dto.ad.space.ListAdSpaceDTO;
import com.jun.biz.manager.dto.ad.space.ModifyAdSpaceDTO;
import com.jun.biz.manager.service.AdSpaceService;
import com.jun.biz.manager.vo.ad.space.AdSpaceVO;
import com.jun.biz.manager.vo.ad.space.ListAdSpaceVO;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Validated
@RestController
@RequestMapping("/api/ad-space")
public class AdSpaceController {

	@Resource
	private AdSpaceService adSpaceService;

	@PerCode("ad-space:mgt")
	@GetMapping("list")
	public ResultVO<ListAdSpaceVO> list(ListAdSpaceDTO dto) {
		return adSpaceService.list(dto);
	}

	@PerCode("ad-space:add")
	@PostMapping("create")
	public ResultVO<Boolean> create(@Validated @RequestBody CreateAdSpaceDTO dto) {
		return adSpaceService.create(dto);
	}

	@PerCode("ad-space:update")
	@PostMapping("modify")
	public ResultVO<Boolean> modify(@Validated @RequestBody ModifyAdSpaceDTO dto) {
		return adSpaceService.modify(dto);
	}

	@PerCode("ad-space:del")
	@DeleteMapping("delete")
	public ResultVO<Boolean> delete(@RequestBody @Validated @NotNull @Size(min = 1) Set<Long> ids) {
		return adSpaceService.delete(ids);
	}


	@PerCode("ad:mgt")
	@GetMapping("detail")
	public ResultVO<AdSpaceVO> detail(@Validated @NotNull Long id) {
		return adSpaceService.detail(id);
	}

	@PerCode("ad:publish")
	@GetMapping("publish")
	public ResultVO<Boolean> publish(@Validated @NotNull Long id) {
		return adSpaceService.publish(id);
	}


}
