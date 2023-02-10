package com.jun.plugin.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.business.domain.Goods;
import com.jun.plugin.business.vo.GoodsVo;
import com.jun.plugin.system.common.DataGridView;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/26
 *
 * 
 * 
 * @since JDK 1.8
 **/

public interface GoodsService extends IService<Goods> {

	/**
	 * 查询所有商品信息
	 *
	 * @param goodsVo
	 * @return
	 */
	DataGridView queryAllGoods(GoodsVo goodsVo);

	/**
	 * 保存商品
	 *
	 * @param goods
	 * @return 缓存用
	 */
	Goods saveGoods(Goods goods);

	/**
	 * 更新商品
	 *
	 * @param goods
	 * @return 缓存用
	 */
	Goods updateGoods(Goods goods);

	/**
	 * 查询所有商品数据 不分页
	 *
	 * @return
	 */
	DataGridView getAllAvailableGoods();

	/**
	 * 根据供货商查询 商品
	 *
	 * @param providerid
	 * @return
	 */
	DataGridView getGoodsByProviderId(Integer providerid);

	/**
	 * 根据商品ID 获取商品信息
	 * 
	 * @param goodsid
	 * @return
	 */
	DataGridView getGoodsByGoodId(Integer goodsid);
}
