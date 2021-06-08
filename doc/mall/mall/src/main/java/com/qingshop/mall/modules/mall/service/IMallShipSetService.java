package com.qingshop.mall.modules.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.modules.mall.entity.MallShipSet;
import com.qingshop.mall.modules.mall.vo.ShipSetVo;

/**
 * 配送设置表 服务类
 */
public interface IMallShipSetService extends IService<MallShipSet> {

	Rest insertShipSet(ShipSetVo shipSet);

}
