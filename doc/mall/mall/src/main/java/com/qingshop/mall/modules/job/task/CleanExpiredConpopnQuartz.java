package com.qingshop.mall.modules.job.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.modules.mall.entity.MallCouponUser;
import com.qingshop.mall.modules.mall.service.IMallCouponUserService;

@Component("cleanExpiredConpopn")
public class CleanExpiredConpopnQuartz {
	
	@Autowired
	private IMallCouponUserService mallCouponUserService;
	
	public void cleanExpiredConpopn() {
		QueryWrapper<MallCouponUser> ew = new QueryWrapper<>();
		ew.lt("end_time", new Date()).eq("status", 0);
		List<MallCouponUser> overtimeCouponList = mallCouponUserService.list(ew);
		if(!StringUtils.isEmpty(overtimeCouponList)) {
			for (MallCouponUser mallCouponUser : overtimeCouponList) {
				mallCouponUser.setStatus(2);
			}
			if(mallCouponUserService.updateBatchById(overtimeCouponList)) {
				System.out.println("过期优惠券清理成功");
			}else {
				System.out.println("过期优惠券清理失败");
			}
		}
	}

}
