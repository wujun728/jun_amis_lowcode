package com.jun.plugin.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.business.domain.Goods;
import com.jun.plugin.business.service.GoodsService;
import com.jun.plugin.business.vo.GoodsVo;
import com.jun.plugin.system.common.Constant;
import com.jun.plugin.system.common.ResultObj;

import java.util.Arrays;

/**
 * ClassName: GoodsController Description: layui date: 2020/4/16 15:52
 *
 * 
 * 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("api/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	/**
	 * 查询所有商品数据
	 *
	 * @param goodsVo
	 * @return
	 */
	@GetMapping("loadAllGoods")
	public Object loadAllGoods(GoodsVo goodsVo) {
		return this.goodsService.queryAllGoods(goodsVo);
	}

	/**
	 * 查询所有商品数据 不分页
	 *
	 * @return
	 */
	@GetMapping("getAllAvailableGoods")
	public Object getAllAvailableGoods() {
		return this.goodsService.getAllAvailableGoods();
	}

	/**
	 * 添加商品
	 *
	 * @param goods
	 * @return
	 */
	@PostMapping("addGoods")
	public Object addGoods(Goods goods) {
		try {
			goods.setAvailable(Constant.AVAILABLE_TRUE);
			if (goods.getGoodsimg() == null) {
				goods.setGoodsimg("");
			}
			this.goodsService.saveGoods(goods);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 根据供应商 id 获取 商品信息
	 *
	 * @param providerid
	 * @return
	 */
	@GetMapping("getGoodsByProviderId")
	public Object getGoodsByProviderId(Integer providerid) {
		return this.goodsService.getGoodsByProviderId(providerid);
	}

	/**
	 * 根据商品ID 获取商品信息
	 *
	 * @param goodsid
	 * @return
	 */
	@GetMapping("getGoodsByGoodId")
	public Object getGoodsByGoodId(Integer goodsid) {
		return this.goodsService.getGoodsByGoodId(goodsid);
	}

	/**
	 * 修改商品
	 *
	 * @param goods
	 * @return
	 */
	@PostMapping("updateGoods")
	public Object updateGoods(Goods goods) {
		try {
			this.goodsService.updateGoods(goods);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 删除商品
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteGoods")
	public Object deleteGoods(Integer id) {
		try {
			this.goodsService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 批量删除商品
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping("batchDeleteGoods")
	public Object batchDeleteGoods(Integer[] ids) {
		try {
			this.goodsService.removeByIds(Arrays.asList(ids));
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

}