package com.jun.biz.manager.service;

import java.util.Set;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.product.CreateProductDTO;
import com.jun.biz.manager.dto.product.ListProductDTO;
import com.jun.biz.manager.dto.product.ModifyProductDTO;
import com.jun.biz.manager.vo.product.ListProductVO;
import com.jun.biz.manager.vo.product.ProductVO;


public interface ProductService {


	ResultVO<Boolean> create(CreateProductDTO dto);

	ResultVO<Boolean> modify(ModifyProductDTO dto);

	ResultVO<Boolean> delete(Set<Long> ids);

	ResultVO<ListProductVO> list(ListProductDTO dto);

	ResultVO<ProductVO> detail(Long id);

	/**
	 * 上架
	 *
	 * @param id
	 * @return
	 */
	ResultVO<Boolean> publish(Set<Long> id);

	/**
	 * 下架
	 *
	 * @param id
	 * @return
	 */
	ResultVO<Boolean> suspend(Set<Long> id);


}
