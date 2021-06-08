package com.qingshop.mall.modules.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.vo.GoodsAllinOneVo;

/**
 * 商品基本信息表 服务类
 */
public interface IMallGoodsService extends IService<MallGoods> {

	Rest insertMallGoods(GoodsAllinOneVo mallGoodsAll);

	Rest updateMallGoodById(GoodsAllinOneVo goodsAllinone);

	Rest deleteMallGoodById(Long id);

}
