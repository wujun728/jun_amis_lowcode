
package com.jun.biz.manager.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.common.service.FileService;
import com.jun.biz.common.service.dto.SaveAdHtmlDTO;
import com.jun.biz.common.utils.FreemarkerUtil;
import com.jun.biz.manager.dao.AdItemDao;
import com.jun.biz.manager.dao.AdSpaceDao;
import com.jun.biz.manager.dto.ad.space.CreateAdSpaceDTO;
import com.jun.biz.manager.dto.ad.space.ListAdSpaceDTO;
import com.jun.biz.manager.dto.ad.space.ModifyAdSpaceDTO;
import com.jun.biz.manager.mapstruct.AdItemConverter;
import com.jun.biz.manager.mapstruct.AdSpaceConverter;
import com.jun.biz.manager.model.AdItem;
import com.jun.biz.manager.model.AdSpace;
import com.jun.biz.manager.model.enums.AdItemStatus;
import com.jun.biz.manager.service.AdSpaceService;
import com.jun.biz.manager.vo.ad.item.AdItemVO;
import com.jun.biz.manager.vo.ad.space.AdSpaceVO;
import com.jun.biz.manager.vo.ad.space.ListAdSpaceVO;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created on 2020/10/14 20:12
 * <p>
 * Description: [TODO]
 * <p>
 *
 * 
 */
@Service
public class AdSpaceServiceImpl implements AdSpaceService {
    @Value("${mall.file.domain}")
    private String domain;
    @Resource
    private AdSpaceConverter adSpaceConverter;
    @Resource
    private AdItemConverter adItemConverter;
    @Resource
    private AdSpaceDao adSpaceDao;
    @Resource
    private AdItemDao adItemDao;
    @Resource
    private FileService fileService;

    @Override
    public ResultVO<ListAdSpaceVO> list(ListAdSpaceDTO dto) {
        if (StringUtils.isBlank(dto.getOrderBy())) {
            dto.setOrderBy("id desc");
        }
        Map<String, Object> cond = dto.toMap();
        long count = adSpaceDao.count(cond);
        List<AdSpace> adSpaces = adSpaceDao.selectList(cond);
        ListAdSpaceVO vo = new ListAdSpaceVO();
        vo.setTotal(count);
        vo.setList(adSpaceConverter.toVo(adSpaces));
        return ResultVO.buildSuccessResult(vo);
    }

    @Override
    public ResultVO<AdSpaceVO> detail(Long id) {
        AdSpace adSpace = adSpaceDao.selectByPk(id);
        if (adSpace == null) {
            return ResultVO.buildFailResult("广告位不存在");
        }
        AdSpaceVO adSpaceVO = adSpaceConverter.toVo(adSpace);
        Map<String, Object> cond = new HashMap<>(2);
        cond.put("adSpaceId", id);
        cond.put("orderBy", "weight desc");
        List<AdItem> adItems = adItemDao.selectList(cond);
        adSpaceVO.setAdItemVos(adItemConverter.toVo(adItems));
        return ResultVO.buildSuccessResult(adSpaceVO);
    }

    @Override
    public ResultVO<Boolean> publish(Long id) {
        ResultVO<AdSpaceVO> detailVo = detail(id);
        if (!detailVo.isSuccess()) {
            return ResultVO.buildFailResult(detailVo.getMsg());
        }
        AdSpaceVO adSpaceVo = detailVo.getData();
        adSpaceVo.setDomain(domain);
        String html = FreemarkerUtil.genHtml(adSpaceVo.getNo(), adSpaceVo.getTemplate(), adSpaceVo);
        SaveAdHtmlDTO dto = new SaveAdHtmlDTO();
        dto.setNo(adSpaceVo.getNo());
        dto.setHtml(html);
        fileService.saveAdHtml(dto);
        for (AdItemVO adItemVO : adSpaceVo.getAdItemVos()) {
            AdItem adItem = new AdItem();
            adItem.setId(adItemVO.getId());
            adItem.setStatus(AdItemStatus.PUBLISHED.getCode());
            adItemDao.update(adItem, "status");
        }
        return ResultVO.buildSuccessResult();
    }

    @Override
    public ResultVO<Boolean> create(CreateAdSpaceDTO dto) {
        AdSpace entity = adSpaceConverter.createDtoToEntity(dto);
        entity.setCreateTime(new Date());
        adSpaceDao.insert(entity);
        return ResultVO.buildSuccessResult();
    }

    @Override
    public ResultVO<Boolean> modify(ModifyAdSpaceDTO dto) {
        AdSpace adSpace = adSpaceDao.selectByPk(dto.getId());
        if (adSpace == null) {
            return ResultVO.buildFailResult("广告位不存在");
        }
        AdSpace entity = adSpaceConverter.modifyDtoToEntity(dto);
        adSpaceDao.update(entity);
        return ResultVO.buildSuccessResult();
    }

    @Override
    public ResultVO<Boolean> delete(Set<Long> ids) {
        for (Long id : ids) {
            AdSpace adSpace = adSpaceDao.selectByPk(id);
            if (adSpace == null) {
                return ResultVO.buildFailResult("广告位不存在！");
            }
            SaveAdHtmlDTO dto = new SaveAdHtmlDTO();
            dto.setNo(adSpace.getNo());
            dto.setHtml("");
            fileService.saveAdHtml(dto);
            adItemDao.deleteBySpaceId(id);
            adSpaceDao.delete(id);

        }
        return ResultVO.buildSuccessResult();
    }

}
