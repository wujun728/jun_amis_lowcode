
package com.jun.biz.manager.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dao.LogisticDao;
import com.jun.biz.manager.dto.logistic.CreateLogisticDTO;
import com.jun.biz.manager.dto.logistic.ListLogisticDTO;
import com.jun.biz.manager.dto.logistic.ModifyLogisticDTO;
import com.jun.biz.manager.mapstruct.LogisticConverter;
import com.jun.biz.manager.model.Logistic;
import com.jun.biz.manager.service.LogisticService;
import com.jun.biz.manager.vo.logistic.ListLogisticVO;

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
public class LogisticServiceImpl implements LogisticService {
	@Resource
	private LogisticConverter logisticConverter;
	@Resource
	private LogisticDao logisticDao;

	@Override
	public ResultVO<ListLogisticVO> list(ListLogisticDTO dto) {
		if (StringUtils.isBlank(dto.getOrderBy())) {
			dto.setOrderBy("id desc");
		}
		Map<String, Object> cond = dto.toMap();
		long count = logisticDao.count(cond);
		List<Logistic> logistics = logisticDao.selectList(cond);
		ListLogisticVO vo = new ListLogisticVO();
		vo.setTotal(count);
		vo.setList(logisticConverter.toVo(logistics));
		return ResultVO.buildSuccessResult(vo);
	}

	@Override
	public ResultVO<Boolean> create(CreateLogisticDTO dto) {
		Logistic entity = logisticConverter.createDtoToEntity(dto);
		entity.setCreateTime(new Date());
		logisticDao.insert(entity);
		return ResultVO.buildSuccessResult();
	}

	@Override
	public ResultVO<Boolean> modify(ModifyLogisticDTO dto) {
		Logistic logistic = logisticDao.selectByPk(dto.getId());
		if (logistic == null) {
			return ResultVO.buildFailResult("角色不存在");
		}


		Logistic entity = logisticConverter.modifyDtoToEntity(dto);
		logisticDao.update(entity);
		return ResultVO.buildSuccessResult();
	}

	@Override
	public ResultVO<Boolean> delete(Set<Long> ids) {
		for (Long id : ids) {
			logisticDao.delete(id);
		}
		return ResultVO.buildSuccessResult();
	}

}
