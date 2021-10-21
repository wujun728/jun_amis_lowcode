
package com.jun.biz.manager.service.impl;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.common.runtime.AdminSessionHolder;
import com.jun.biz.common.service.FileService;
import com.jun.biz.common.service.dto.TempToAdDTO;
import com.jun.biz.common.service.vo.FileVO;
import com.jun.biz.manager.dao.AdItemDao;
import com.jun.biz.manager.dao.AdSpaceDao;
import com.jun.biz.manager.dao.ProductDao;
import com.jun.biz.manager.dto.ad.item.AdItemDTO;
import com.jun.biz.manager.mapstruct.AdItemConverter;
import com.jun.biz.manager.model.AdItem;
import com.jun.biz.manager.model.AdSpace;
import com.jun.biz.manager.model.Product;
import com.jun.biz.manager.model.enums.AdItemStatus;
import com.jun.biz.manager.model.enums.AdSpaceType;
import com.jun.biz.manager.model.enums.ProductStatus;
import com.jun.biz.manager.model.json.LinkAdItem;
import com.jun.biz.manager.model.json.PicAdItem;
import com.jun.biz.manager.model.json.PicTextAdItem;
import com.jun.biz.manager.model.json.ProductAdItem;
import com.jun.biz.manager.service.AdItemService;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2020/10/14 20:12
 * <p>
 * Description: [TODO]
 * <p>
 *
 * 
 */
@Slf4j
@Service
public class AdItemServiceImpl implements AdItemService {
    @Value("${mall.file.adPath}")
    private String adFilePath;
    @Resource
    private AdItemConverter adItemConverter;
    @Resource
    private AdItemDao adItemDao;
    @Resource
    private AdSpaceDao adSpaceDao;
    @Resource
    private ProductDao productDao;
    @Resource
    private FileService fileService;

    @Override
    public ResultVO<Boolean> create(AdItemDTO dto) {
        AdSpace adSpace = adSpaceDao.selectByPk(dto.getAdSpaceId());
        if (adSpace == null) {
            return ResultVO.buildFailResult("广告位不存在");
        }
        AdItem entity = adItemConverter.toEntity(dto);
        entity.setContent(handelContent(adSpace, dto));
        entity.setCreateTime(new Date());
        entity.setCreateAdmin(AdminSessionHolder.getCurrentAdmin().getUsername());
        entity.setStatus(AdItemStatus.UNPUBLISHED.getCode());
        adItemDao.insert(entity);
        return ResultVO.buildSuccessResult();
    }

    private String handelContent(AdSpace adSpace, AdItemDTO dto) {
        Object contentObject;
        AdSpaceType type = AdSpaceType.getByCode(adSpace.getType());
        switch (type) {
            case PIC:
            case PIC_TEXT: {
                TempToAdDTO tempToAdDTO = new TempToAdDTO();
                tempToAdDTO.setAdSpaceId(dto.getAdSpaceId());
                tempToAdDTO.setTempFilePath(dto.getPicPath());
                tempToAdDTO.setWidth(adSpace.getWidth());
                tempToAdDTO.setHeight(adSpace.getHeight());
                String picPath = dto.getPicPath();
                if (!dto.getPicPath().startsWith(adFilePath)) {
                    ResultVO<FileVO> vo = fileService.tempToAd(tempToAdDTO);
                    if (!vo.isSuccess()) {
                        log.error(vo.getMsg());
                        return null;
                    }
                    picPath = vo.getData().getPath();
                }

                if (Objects.equals(type, AdSpaceType.PIC)) {
                    contentObject = new PicAdItem(dto.getLink(), picPath);
                } else {
                    contentObject = new PicTextAdItem(dto.getLink(), picPath, dto.getText());
                }
                break;
            }
            case LINK: {
                contentObject = new LinkAdItem(dto.getText(), dto.getLink());
                break;
            }
            case PRODUCT: {
                Product product = productDao.selectByPk(dto.getObjectId());
                if (product == null || !ProductStatus.SELLING.getCode().equals(product.getStatus())) {
                    log.error("所选的商品异常！");
                    return null;
                }
                contentObject = new ProductAdItem(product);
                break;
            }
            default: {
                log.error("未知的广告位类型！");
                return null;
            }
        }
        return JSONObject.toJSONString(contentObject);
    }

    @Override
    public ResultVO<Boolean> modify(AdItemDTO dto) {
        AdItem adItem = adItemDao.selectByPk(dto.getId());
        if (adItem == null) {
            return ResultVO.buildFailResult("广告不存在");
        }
        AdSpace adSpace = adSpaceDao.selectByPk(adItem.getAdSpaceId());
        if (adSpace == null) {
            return ResultVO.buildFailResult("广告位不存在");
        }
        AdItem entity = adItemConverter.toEntity(dto);
        entity.setContent(handelContent(adSpace, dto));
        adItemDao.update(entity);
        return ResultVO.buildSuccessResult();
    }

    @Override
    public ResultVO<Boolean> delete(Set<Long> ids) {
        for (Long id : ids) {
            adItemDao.delete(id);
        }
        return ResultVO.buildSuccessResult();
    }

}
