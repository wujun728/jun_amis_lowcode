
package com.jun.biz.manager.mapstruct;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.jun.biz.manager.dto.order.CreateOrderDTO;
import com.jun.biz.manager.dto.order.ModifyOrderDTO;
import com.jun.biz.manager.model.Order;
import com.jun.biz.manager.vo.order.OrderVO;

import java.util.List;

/**
 * Created on 2020/10/14 18:51
 * <p>
 *
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderConverter {

    List<OrderVO> toVo(List<Order> entityList);

    Order createDtoToEntity(CreateOrderDTO dto);

    Order modifyDtoToEntity(ModifyOrderDTO dto);
}
