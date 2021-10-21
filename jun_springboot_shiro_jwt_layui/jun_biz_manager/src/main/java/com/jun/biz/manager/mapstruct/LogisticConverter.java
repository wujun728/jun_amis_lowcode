
package com.jun.biz.manager.mapstruct;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.jun.biz.manager.dto.logistic.CreateLogisticDTO;
import com.jun.biz.manager.dto.logistic.ModifyLogisticDTO;
import com.jun.biz.manager.model.Logistic;
import com.jun.biz.manager.vo.logistic.LogisticVO;

import java.util.List;

/**
 * Created on 2020/10/14 18:51
 * <p>
 *
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogisticConverter {

    List<LogisticVO> toVo(List<Logistic> entityList);

    Logistic createDtoToEntity(CreateLogisticDTO dto);

    Logistic modifyDtoToEntity(ModifyLogisticDTO dto);
}
