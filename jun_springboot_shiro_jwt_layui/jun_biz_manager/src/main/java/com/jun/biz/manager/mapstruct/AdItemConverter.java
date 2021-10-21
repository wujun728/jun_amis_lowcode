package com.jun.biz.manager.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.jun.biz.manager.dto.ad.item.AdItemDTO;
import com.jun.biz.manager.model.AdItem;
import com.jun.biz.manager.vo.ad.item.AdItemVO;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdItemConverter {

    List<AdItemVO> toVo(List<AdItem> entityList);

    AdItem toEntity(AdItemDTO dto);

}
