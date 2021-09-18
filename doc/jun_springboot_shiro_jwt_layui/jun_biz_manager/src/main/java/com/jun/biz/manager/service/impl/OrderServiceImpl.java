
package com.jun.biz.manager.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dao.OrderDao;
import com.jun.biz.manager.dto.order.CreateOrderDTO;
import com.jun.biz.manager.dto.order.ListOrderDTO;
import com.jun.biz.manager.dto.order.ModifyOrderDTO;
import com.jun.biz.manager.mapstruct.OrderConverter;
import com.jun.biz.manager.model.Order;
import com.jun.biz.manager.service.OrderService;
import com.jun.biz.manager.vo.order.ListOrderVO;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created on 2020/10/14 20:12
 * <p>
 * Description: [TODO]
 * <p>
 *
 * 
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Resource
	private OrderConverter orderConverter;
	@Resource
	private OrderDao orderDao;

	@Override
	public ResultVO<ListOrderVO> list(ListOrderDTO dto) {
		if (StringUtils.isBlank(dto.getOrderBy())) {
			dto.setOrderBy("id desc");
		}
		Map<String, Object> cond = dto.toMap();
		long count = orderDao.count(cond);
		List<Order> orders = orderDao.selectList(cond);
		ListOrderVO vo = new ListOrderVO();
		vo.setTotal(count);
		vo.setList(orderConverter.toVo(orders));
		return ResultVO.buildSuccessResult(vo);
	}

	@Override
	public ResultVO<Boolean> create(CreateOrderDTO dto) {
		Order entity = orderConverter.createDtoToEntity(dto);
		entity.setCreateTime(new Date());
		orderDao.insert(entity);
		return ResultVO.buildSuccessResult();
	}

	@Override
	public ResultVO<Boolean> modify(ModifyOrderDTO dto) {
		Order order = orderDao.selectByPk(dto.getId());
		if (order == null) {
			return ResultVO.buildFailResult("角色不存在");
		}


		Order entity = orderConverter.modifyDtoToEntity(dto);
		orderDao.update(entity);
		return ResultVO.buildSuccessResult();
	}

	@Override
	public ResultVO<Boolean> delete(Set<Long> ids) {
		for (Long id : ids) {
			orderDao.delete(id);
		}
		return ResultVO.buildSuccessResult();
	}

}
