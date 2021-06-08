package com.qingshop.mall.modules.mall.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.modules.mall.entity.MallCoupon;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.entity.MallGoodsSku;
import com.qingshop.mall.modules.mall.entity.MallGoodsSkudetail;
import com.qingshop.mall.modules.mall.mapper.MallGoodsMapper;
import com.qingshop.mall.modules.mall.service.IMallCategoryService;
import com.qingshop.mall.modules.mall.service.IMallCouponService;
import com.qingshop.mall.modules.mall.service.IMallGoodsService;
import com.qingshop.mall.modules.mall.service.IMallGoodsSkuService;
import com.qingshop.mall.modules.mall.service.IMallGoodsSkudetailService;
import com.qingshop.mall.modules.mall.vo.GoodsAllinOneVo;

/**
 * 商品基本信息表 服务实现类
 */
@Service
public class MallGoodsServiceImpl extends ServiceImpl<MallGoodsMapper, MallGoods> implements IMallGoodsService {
	
	@Autowired 
	private IMallCategoryService mallCategoryService;
	
	@Autowired 
	private IMallGoodsSkuService mallGoodsSkuService;
	
	@Autowired 
	private IMallGoodsSkudetailService mallGoodsSkudetailService;
	
	@Autowired
	private IMallCouponService mallCouponService;
	
	/**
	 * 插入商品信息
	 */
	@Override
	public Rest insertMallGoods(GoodsAllinOneVo mallGoodsAll) {
		
		Object error = validate(mallGoodsAll);
        if (error != null) {
            return (Rest)error;
        }
		MallGoods goods = mallGoodsAll.getGoods();
		List<MallGoodsSku> goodsSkus = mallGoodsAll.getGoodSkus();
		List<MallGoodsSkudetail> goodsSkudetail = mallGoodsAll.getGoodSkudetails();
		goods.setGoodsId(DistributedIdWorker.nextId());
		//保存该类商品最低价
		if(!StringUtils.isEmpty(goodsSkudetail)) {
			MallGoodsSkudetail minPriceGood = new MallGoodsSkudetail();
			for (int i = 0; i < goodsSkudetail.size(); i++) {
				if(i == 0) {
					minPriceGood = goodsSkudetail.get(i);
				}else if(goodsSkudetail.get(i).getGoodPrice().compareTo(minPriceGood.getGoodPrice()) == -1){
					minPriceGood = goodsSkudetail.get(i);
				}
			}
			goods.setGoodPrice(minPriceGood.getGoodPrice());
			goods.setLinePrice(minPriceGood.getLinePrice());
		}
		boolean result = this.save(goods);
		if(result) {
			for (MallGoodsSku mallGoodsSku : goodsSkus) {
				mallGoodsSku.setSkuId(DistributedIdWorker.nextId());
				mallGoodsSku.setGoodsId(goods.getGoodsId());
			}
			mallGoodsSkuService.saveBatch(goodsSkus);
			for (MallGoodsSkudetail mallGoodsSkudetail : goodsSkudetail) {
				mallGoodsSkudetail.setSkudetailId(DistributedIdWorker.nextId());
				mallGoodsSkudetail.setGoodsId(goods.getGoodsId());
			}
			mallGoodsSkudetailService.saveBatch(goodsSkudetail);
			return Rest.ok();
		}else {
			return Rest.failure();
		}
	}

	/**
	 *	修改商品信息
	 */
	@Override
	public Rest updateMallGoodById(GoodsAllinOneVo mallGoodsAll) {
		Object error = validate(mallGoodsAll);
        if (error != null) {
            return (Rest)error;
        }
        Date stime = new Date();
        MallGoods goods = mallGoodsAll.getGoods();
		List<MallGoodsSku> goodsSkus = mallGoodsAll.getGoodSkus();
		List<Long> skuIds = new ArrayList<>();
		for (MallGoodsSku mallGoodsSku : goodsSkus) {
			skuIds.add(mallGoodsSku.getSkuId());
		}
		List<MallGoodsSkudetail> goodsSkudetail = mallGoodsAll.getGoodSkudetails();
		List<Long> skuDetailIds = new ArrayList<>();
		//保存该类商品最低价
		if(!StringUtils.isEmpty(goodsSkudetail)) {
			MallGoodsSkudetail minPriceGood = new MallGoodsSkudetail();
			for (int i = 0; i < goodsSkudetail.size(); i++) {
				skuDetailIds.add(goodsSkudetail.get(i).getSkudetailId());
				if(i == 0) {
					minPriceGood = goodsSkudetail.get(i);
				}else if(goodsSkudetail.get(i).getGoodPrice().compareTo(minPriceGood.getGoodPrice()) == -1){
					minPriceGood = goodsSkudetail.get(i);
				}
			}
			goods.setGoodPrice(minPriceGood.getGoodPrice());
			goods.setLinePrice(minPriceGood.getLinePrice());
		}
		MallGoods oldGood = this.getById(goods.getGoodsId());
		if(this.updateById(goods)) {
			mallGoodsSkuService.remove(new QueryWrapper<MallGoodsSku>().eq("goods_id", goods.getGoodsId()));
			mallGoodsSkudetailService.remove(new QueryWrapper<MallGoodsSkudetail>().eq("goods_id", goods.getGoodsId()));
			for (MallGoodsSku sku : goodsSkus) {
				sku.setSkuId(DistributedIdWorker.nextId());
				sku.setGoodsId(goods.getGoodsId());
				sku.setUpdateTime(stime);
			}
			mallGoodsSkuService.saveBatch(goodsSkus);
			for (MallGoodsSkudetail skudetail : goodsSkudetail) {
				if(skudetail.getSkudetailId() == null) {
					skudetail.setSkudetailId(DistributedIdWorker.nextId());
				}
				skudetail.setGoodsId(goods.getGoodsId());
				skudetail.setUpdateTime(stime);
			}
			mallGoodsSkudetailService.saveBatch(goodsSkudetail);
			//修改商品名称则更新优惠券表中的冗余字段
			if(!oldGood.getGoodName().equals(goods.getGoodName())) {
				MallCoupon coupon = new MallCoupon();
				coupon.setRangeRelationName(goods.getGoodName());
				mallCouponService.update(coupon, new QueryWrapper<MallCoupon>().eq("range_relation_id", goods.getGoodsId()));
			}
			return Rest.ok();
		}else {
			return Rest.failure();
		}
	}
	
	@Override
	public Rest deleteMallGoodById(Long id) {
		boolean result = this.removeById(id);
		if(result) {
			mallGoodsSkuService.remove(new QueryWrapper<MallGoodsSku>().eq("goods_id", id));
			mallGoodsSkudetailService.remove(new QueryWrapper<MallGoodsSkudetail>().eq("goods_id", id));
			return Rest.ok();
		}else {
			return Rest.failure();
		}
	}
	
	/**
	 * (修改商品操作后台数据校验)
	 *
	 * @param goodsAllinone
	 * @return
	 */
	private Object validate(GoodsAllinOneVo goodsAllinone) {
		MallGoods goods = goodsAllinone.getGoods();
        String name = goods.getGoodName();
        if (StringUtils.isEmpty(name)) {
            return Rest.failure("商品名称不能为空");
        }
        String goodsSn = goods.getGoodSn();
        if (StringUtils.isEmpty(goodsSn)) {
            return Rest.failure("商品编号不能为空");
        }
        // 分类可以不设置，如果设置则需要验证分类存在
        Long categoryId = goods.getCategoryId();
        if (categoryId != null && categoryId != 0) {
            if (mallCategoryService.getById(categoryId) == null) {
                return Rest.failure("商品分类错误!");
            }
        }

        List<MallGoodsSku> skus = goodsAllinone.getGoodSkus();
        for (MallGoodsSku sku : skus) {
            String spec = sku.getSkukey();
            if (StringUtils.isEmpty(spec)) {
                return Rest.failure("商品规格名错误!");
            }
            String value = sku.getSkuvalue();
            if (StringUtils.isEmpty(value)) {
                return Rest.failure("商品规格值错误!");
            }
        }

        List<MallGoodsSkudetail> skudetails = goodsAllinone.getGoodSkudetails();
        if(StringUtils.isEmpty(skudetails)) {
        	return Rest.failure("请输入商品规格");
        }else {
        	for (MallGoodsSkudetail skudetail : skudetails) {
                BigDecimal goodPrice = skudetail.getGoodPrice();
                if (goodPrice == null || goodPrice.compareTo(BigDecimal.ZERO) <= 0  ) {
                	return Rest.failure("销售价错误!");
                }
                BigDecimal linePrice = skudetail.getLinePrice();
                if (linePrice == null || linePrice.compareTo(BigDecimal.ZERO) <= 0 ) {
                	return Rest.failure("划线价错误!");
                }
                String productSpecifications = skudetail.getSkudetails();
                if (StringUtils.isEmpty(productSpecifications)) {
                	return Rest.failure("商品规格值错误!");
                }
                Integer number = skudetail.getNumber();
                if (number == null || number < 0) {
                    return Rest.failure("库存值错误!");
                }
                BigDecimal goodWeight = skudetail.getGoodWeight();
                if (goodWeight == null ||  goodWeight.compareTo(BigDecimal.ZERO) <= 0 ) {
                	return Rest.failure("重量值错误!");
                }
            }
        }
        return null;
    }
}