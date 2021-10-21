
package com.jun.biz.manager.mapstruct;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.jun.biz.manager.dto.ad.space.CreateAdSpaceDTO;
import com.jun.biz.manager.dto.ad.space.ModifyAdSpaceDTO;
import com.jun.biz.manager.model.AdSpace;
import com.jun.biz.manager.vo.ad.space.AdSpaceVO;

import java.util.List;

/**
 * Created on 2020/10/14 18:51
 * <p>
 *
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdSpaceConverter {

    List<AdSpaceVO> toVo(List<AdSpace> entityList);

    AdSpaceVO toVo(AdSpace adSpace);

    AdSpace createDtoToEntity(CreateAdSpaceDTO dto);

    AdSpace modifyDtoToEntity(ModifyAdSpaceDTO dto);
}
