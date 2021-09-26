package com.royal.ow.mapper;

import com.royal.common.base.BaseMapper;
import com.royal.ow.domain.Equipment;

import java.util.List;

/**
 * 设备介绍 数据层
 * 
 * @author royal
 * @date 2018-12-13
 */
public interface EquipmentMapper extends BaseMapper<Equipment>
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
     * 查询设备介绍列表 For 官网
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
     * 删除设备介绍
     * 
     * @param id 设备介绍ID
     * @return 结果
     */
	public int deleteEquipmentById(Integer id);
	
	/**
     * 批量删除设备介绍
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteEquipmentByIds(String[] ids);
	
}