package com.jun.biz.manager.dao;

import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.ProductDetail;
import com.jun.biz.manager.model.annotation.MapperMapping;

@MapperMapping(table = "p_product_detail")
public interface ProductDetailDao extends BaseDao<ProductDetail, Long> {
    ProductDetail selectByProductId(Long productId);
}
