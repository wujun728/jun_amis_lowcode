package com.jun.biz.manager.service;

import java.util.Set;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.ad.item.AdItemDTO;


public interface AdItemService {


    ResultVO<Boolean> create(AdItemDTO dto);

    ResultVO<Boolean> modify(AdItemDTO dto);

    ResultVO<Boolean> delete(Set<Long> ids);


}
