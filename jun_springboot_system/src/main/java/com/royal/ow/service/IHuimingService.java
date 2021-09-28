package com.royal.ow.service;

import com.royal.ow.domain.Huiming;
import java.util.List;

/**
 * 惠民服务 服务层
 * 
 * @author royal
 * @date 2018-12-13
 */
public interface IHuimingService 
{
	/**
     * 查询惠民服务信息
     * 
     * @param id 惠民服务ID
     * @return 惠民服务信息
     */
	public Huiming selectHuimingById(Integer id);
	
	/**
     * 查询惠民服务列表
     * 
     * @param huiming 惠民服务信息
     * @return 惠民服务集合
     */
	public List<Huiming> selectHuimingList(Huiming huiming);

    /**
     * 查询惠民服务列表for官网
     *
     * @param huiming 惠民服务信息
     * @return 惠民服务集合
     */
    public List<Huiming> selectHuimingListForOw(Huiming huiming);
	
	/**
     * 新增惠民服务
     * 
     * @param huiming 惠民服务信息
     * @return 结果
     */
	public int insertHuiming(Huiming huiming);
	
	/**
     * 修改惠民服务
     * 
     * @param huiming 惠民服务信息
     * @return 结果
     */
	public int updateHuiming(Huiming huiming);
		
	/**
     * 删除惠民服务信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHuimingByIds(String ids);

    /**
     * 通过ID查看详情
     * @param id
     * @return
     */
    public Huiming getHuimingByIds(Long id);
	
}
