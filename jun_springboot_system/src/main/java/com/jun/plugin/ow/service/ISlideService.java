package com.jun.plugin.ow.service;

import java.util.List;

import com.jun.plugin.ow.domain.Slide;

/**
 * 幻灯片 服务层
 * 
 * @author admin
 * @date 2018-12-13
 */
public interface ISlideService 
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
     * 查询幻灯片列表for官网
     *
     * @param slide 幻灯片信息
     * @return 幻灯片集合
     */
    public List<Slide> selectSlideListForOw(Slide slide);
	
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
     * 删除幻灯片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSlideByIds(String ids);

    /**
     * 通过ID查看详情
     * @param id
     * @return
     */
    public Slide getSlideByIds(Long id);
	
}
