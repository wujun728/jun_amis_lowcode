package com.royal.ow.mapper;

import com.royal.common.base.BaseMapper;
import com.royal.ow.domain.Slide;

import java.util.List;

/**
 * 幻灯片 数据层
 * 
 * @author royal
 * @date 2018-12-13
 */
public interface SlideMapper extends BaseMapper<Slide>
{
	/**
     * 查询幻灯片信息
     * 
     * @param id 幻灯片ID
     * @return 幻灯片信息
     */
	public Slide selectSlideById(Integer id);
	
	/**
     * 查询幻灯片列表
     * 
     * @param slide 幻灯片信息
     * @return 幻灯片集合
     */
	public List<Slide> selectSlideList(Slide slide);
	
	/**
     * 新增幻灯片
     * 
     * @param slide 幻灯片信息
     * @return 结果
     */
	public int insertSlide(Slide slide);
	
	/**
     * 修改幻灯片
     * 
     * @param slide 幻灯片信息
     * @return 结果
     */
	public int updateSlide(Slide slide);
	
	/**
     * 删除幻灯片
     * 
     * @param id 幻灯片ID
     * @return 结果
     */
	public int deleteSlideById(Integer id);
	
	/**
     * 批量删除幻灯片
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSlideByIds(String[] ids);
	
}