package com.qingshop.mall.modules.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.modules.mall.entity.MallGoodsSkudetail;

/**
 * 商品货品表 服务类
 */
public interface IMallGoodsSkudetailService extends IService<MallGoodsSkudetail> {

	public int addStock(Long skudetailId, Integer num);

	public int reduceStock(Long skudetailId, Integer num);
}
