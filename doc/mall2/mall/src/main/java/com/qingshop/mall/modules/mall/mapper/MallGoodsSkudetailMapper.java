package com.qingshop.mall.modules.mall.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingshop.mall.modules.mall.entity.MallGoodsSkudetail;

/**
 * <p>
 * 商品货品表 Mapper 接口
 * </p>
 */
public interface MallGoodsSkudetailMapper extends BaseMapper<MallGoodsSkudetail> {

	int addStock(@Param("skudetailId") Long skudetailId, @Param("num") Integer num);

	int reduceStock(@Param("skudetailId") Long skudetailId, @Param("num") Integer num);

}
