package com.jun.biz.manager.service;

import java.util.Set;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.logistic.CreateLogisticDTO;
import com.jun.biz.manager.dto.logistic.ListLogisticDTO;
import com.jun.biz.manager.dto.logistic.ModifyLogisticDTO;
import com.jun.biz.manager.vo.logistic.ListLogisticVO;


public interface LogisticService {


	ResultVO<Boolean> create(CreateLogisticDTO dto);

	ResultVO<Boolean> modify(ModifyLogisticDTO dto);

	ResultVO<Boolean> delete(Set<Long> ids);

	ResultVO<ListLogisticVO> list(ListLogisticDTO dto);


}
