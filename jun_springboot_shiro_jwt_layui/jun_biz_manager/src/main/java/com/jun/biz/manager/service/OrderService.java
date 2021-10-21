package com.jun.biz.manager.service;

import java.util.Set;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.order.CreateOrderDTO;
import com.jun.biz.manager.dto.order.ListOrderDTO;
import com.jun.biz.manager.dto.order.ModifyOrderDTO;
import com.jun.biz.manager.vo.order.ListOrderVO;


public interface OrderService {


	ResultVO<Boolean> create(CreateOrderDTO dto);

	ResultVO<Boolean> modify(ModifyOrderDTO dto);

	ResultVO<Boolean> delete(Set<Long> ids);

	ResultVO<ListOrderVO> list(ListOrderDTO dto);


}
