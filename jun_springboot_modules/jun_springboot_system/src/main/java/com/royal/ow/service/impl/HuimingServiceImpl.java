package com.royal.ow.service.impl;

import com.royal.common.support.Convert;
import com.royal.ow.domain.Huiming;
import com.royal.ow.mapper.HuimingMapper;
import com.royal.ow.service.IHuimingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 惠民服务 服务层实现
 * 
 * @author royal
 * @date 2018-12-13
 */
@Service
public class HuimingServiceImpl implements IHuimingService 
{
	@Autowired
	private HuimingMapper huimingMapper;

	/**
     * 查询惠民服务信息
     * 
     * @param id 惠民服务ID
     * @return 惠民服务信息
     */
    @Override
	public Huiming selectHuimingById(Integer id)
	{
	    return huimingMapper.selectHuimingById(id);
	}
	
	/**
     * 查询惠民服务列表
     * 
     * @param huiming 惠民服务信息
     * @return 惠民服务集合
     */
	@Override
	public List<Huiming> selectHuimingList(Huiming huiming)
	{
	    return huimingMapper.selectHuimingList(huiming);
	}

    /**
     * 查询惠民服务列表for 官网
     *
     * @param huiming 惠民服务信息
     * @return 惠民服务集合
     */
    @Override
    public List<Huiming> selectHuimingListForOw(Huiming huiming) {
		huiming.setStatus("0");
        return huimingMapper.selectHuimingListForOw(huiming);
    }

    /**
     * 新增惠民服务
     * 
     * @param huiming 惠民服务信息
     * @return 结果
     */
	@Override
	public int insertHuiming(Huiming huiming)
	{
	    return huimingMapper.insertHuiming(huiming);
	}
	
	/**
     * 修改惠民服务
     * 
     * @param huiming 惠民服务信息
     * @return 结果
     */
	@Override
	public int updateHuiming(Huiming huiming)
	{
	    return huimingMapper.updateHuiming(huiming);
	}

	/**
     * 删除惠民服务对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHuimingByIds(String ids)
	{
		return huimingMapper.deleteHuimingByIds(Convert.toStrArray(ids));
	}
    /**
     * 通过ID查详情
     *
     * @param id
     * @return Huiming
     */
    @Override
    public Huiming getHuimingByIds(Long id) {
        return huimingMapper.selectByPrimaryKey(id);
    }
}
