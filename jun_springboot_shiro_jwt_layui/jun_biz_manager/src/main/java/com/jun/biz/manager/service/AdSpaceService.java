package com.jun.biz.manager.service;

import java.util.Set;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.ad.space.CreateAdSpaceDTO;
import com.jun.biz.manager.dto.ad.space.ListAdSpaceDTO;
import com.jun.biz.manager.dto.ad.space.ModifyAdSpaceDTO;
import com.jun.biz.manager.vo.ad.space.AdSpaceVO;
import com.jun.biz.manager.vo.ad.space.ListAdSpaceVO;


/**
 * 
 */
public interface AdSpaceService {


    ResultVO<Boolean> create(CreateAdSpaceDTO dto);

    ResultVO<Boolean> modify(ModifyAdSpaceDTO dto);

    ResultVO<Boolean> delete(Set<Long> ids);

    ResultVO<ListAdSpaceVO> list(ListAdSpaceDTO dto);

    ResultVO<AdSpaceVO> detail(Long id);

    /**
     * 发布广告位
     *
     * @param id
     * @return
     */
    ResultVO<Boolean> publish(Long id);
}
