package com.qingshop.mall.modules.mall.vo;

import java.util.List;

import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.entity.MallGoodsSku;
import com.qingshop.mall.modules.mall.entity.MallGoodsSkudetail;

public class GoodsAllinOneVo {

	private MallGoods goods;
	
	private List<MallGoodsSku> goodSkus;
    
	private List<MallGoodsSkudetail> goodSkudetails;

	public MallGoods getGoods() {
		return goods;
	}

	public void setGoods(MallGoods goods) {
		this.goods = goods;
	}

	public List<MallGoodsSku> getGoodSkus() {
		return goodSkus;
	}

	public void setGoodSkus(List<MallGoodsSku> goodSkus) {
		this.goodSkus = goodSkus;
	}

	public List<MallGoodsSkudetail> getGoodSkudetails() {
		return goodSkudetails;
	}

	public void setGoodSkudetails(List<MallGoodsSkudetail> goodSkudetails) {
		this.goodSkudetails = goodSkudetails;
	}
    
}
