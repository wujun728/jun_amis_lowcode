package com.jun.plugin.ow.service.impl;

import com.jun.plugin.common.support.Convert;
import com.jun.plugin.ow.domain.Equipment;
import com.jun.plugin.ow.mapper.EquipmentMapper;
import com.jun.plugin.ow.service.IEquipmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备介绍 服务层实现
 * 
 * @author admin
 * @date 2018-12-13
 */
@Service
public class EquipmentServiceImpl implements IEquipmentService 
{
	@Autowired
	private EquipmentMapper equipmentMapper;

	/**
     * 查询设备介绍信息
     * 
     * @param id 设备介绍ID
     * @return 设备介绍信息
     */
    @Override
	public Equipment selectEquipmentById(Integer id)
	{
	    return equipmentMapper.selectEquipmentById(id);
	}
	
	/**
     * 查询设备介绍列表
     * 
     * @param equipment 设备介绍信息
     * @return 设备介绍集合
     */
	@Override
	public List<Equipment> selectEquipmentList(Equipment equipment)
	{
	    return equipmentMapper.selectEquipmentList(equipment);
	}

    /**
     * 查询设备介绍列表for 官网
     *
     * @param equipment 设备介绍信息
     * @return 设备介绍集合
     */
    @Override
    public List<Equipment> selectEquipmentListForOw(Equipment equipment) {
		equipment.setStatus("0");
        return equipmentMapper.selectEquipmentListForOw(equipment);
    }

    /**
     * 新增设备介绍
     * 
     * @param equipment 设备介绍信息
     * @return 结果
     */
	@Override
	public int insertEquipment(Equipment equipment)
	{
	    return equipmentMapper.insertEquipment(equipment);
	}
	
	/**
     * 修改设备介绍
     * 
     * @param equipment 设备介绍信息
     * @return 结果
     */
	@Override
	public int updateEquipment(Equipment equipment)
	{
	    return equipmentMapper.updateEquipment(equipment);
	}

	/**
     * 删除设备介绍对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteEquipmentByIds(String ids)
	{
		return equipmentMapper.deleteEquipmentByIds(Convert.toStrArray(ids));
	}
    /**
     * 通过ID查详情
     *
     * @param id
     * @return Equipment
     */
    @Override
    public Equipment getEquipmentByIds(Long id) {
        return equipmentMapper.selectByPrimaryKey(id);
    }
}
