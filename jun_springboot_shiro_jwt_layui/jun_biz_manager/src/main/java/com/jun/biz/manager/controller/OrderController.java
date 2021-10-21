package com.jun.biz.manager.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jun.biz.common.annotation.PerCode;
import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.order.CreateOrderDTO;
import com.jun.biz.manager.dto.order.ListOrderDTO;
import com.jun.biz.manager.dto.order.ModifyOrderDTO;
import com.jun.biz.manager.service.OrderService;
import com.jun.biz.manager.vo.order.ListOrderVO;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Validated
@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Resource
	private OrderService orderService;

	@PerCode("order:mgt")
	@GetMapping("list")
	public ResultVO<ListOrderVO> list(ListOrderDTO dto) {
		return orderService.list(dto);
	}

	@PerCode("order:add")
	@PostMapping("create")
	public ResultVO<Boolean> create(@Validated @RequestBody CreateOrderDTO dto) {
		return orderService.create(dto);
	}

	@PerCode("order:update")
	@PostMapping("modify")
	public ResultVO<Boolean> modify(@Validated @RequestBody ModifyOrderDTO dto) {
		return orderService.modify(dto);
	}

	@PerCode("order:del")
	@DeleteMapping("delete")
	public ResultVO<Boolean> delete(@RequestBody @Validated @NotNull @Size(min = 1) Set<Long> ids) {
		return orderService.delete(ids);
	}


}
