package com.jun.plugin.ow.service;

import java.util.List;

import com.jun.plugin.ow.domain.Equipment;

/**
 * 设备介绍 服务层
 * 
 * @author admin
 * @date 2018-12-13
 */
public interface IEquipmentService 
{
	/**
     * 查询设备介绍信息
     * 
     * @param id 设备介绍ID
     * @return 设备介绍信息
     */
	public Equipment selectEquipmentById(Integer id);
	
	/**
     * 查询设备介绍列表
     * 
     * @param equipment 设备介绍信息
     * @return 设备介绍集合
     */
	public List<Equipment> selectEquipmentList(Equipment equipment);

    /**
     * 查询设备介绍列表for官网
     *
     * @param equipment 设备介绍信息
     * @return 设备介绍集合
     */
    public List<Equipment> selectEquipmentListForOw(Equipment equipment);
	
	/**
     * 新增设备介绍
     * 
     * @param equipment 设备介绍信息
     * @return 结果
     */
	public int insertEquipment(Equipment equipment);
	
	/**
     * 修改设备介绍
     * 
     * @param equipment 设备介绍信息
     * @return 结果
     */
	public int updateEquipment(Equipment equipment);
		
	/**
     * 删除设备介绍信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteEquipmentByIds(String ids);

    /**
     * 通过ID查看详情
     * @param id
     * @return
     */
    public Equipment getEquipmentByIds(Long id);
	
}
