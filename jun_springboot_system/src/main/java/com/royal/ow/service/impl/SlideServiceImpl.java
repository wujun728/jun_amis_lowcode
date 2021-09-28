package com.royal.ow.service.impl;

import com.royal.common.support.Convert;
import com.royal.ow.domain.Slide;
import com.royal.ow.mapper.SlideMapper;
import com.royal.ow.service.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 幻灯片 服务层实现
 * 
 * @author royal
 * @date 2018-12-13
 */
@Service
public class SlideServiceImpl implements ISlideService 
{
	@Autowired
	private SlideMapper slideMapper;

	/**
     * 查询幻灯片信息
     * 
     * @param id 幻灯片ID
     * @return 幻灯片信息
     */
    @Override
	public Slide selectSlideById(Integer id)
	{
	    return slideMapper.selectSlideById(id);
	}
	
	/**
     * 查询幻灯片列表
     * 
     * @param slide 幻灯片信息
     * @return 幻灯片集合
     */
	@Override
	public List<Slide> selectSlideList(Slide slide)
	{
	    return slideMapper.selectSlideList(slide);
	}

    /**
     * 查询幻灯片列表for 官网
     *
     * @param slide 幻灯片信息
     * @return 幻灯片集合
     */
    @Override
    public List<Slide> selectSlideListForOw(Slide slide) {
        return slideMapper.selectSlideList(slide);
    }

    /**
     * 新增幻灯片
     * 
     * @param slide 幻灯片信息
     * @return 结果
     */
	@Override
	public int insertSlide(Slide slide)
	{
	    return slideMapper.insertSlide(slide);
	}
	
	/**
     * 修改幻灯片
     * 
     * @param slide 幻灯片信息
     * @return 结果
     */
	@Override
	public int updateSlide(Slide slide)
	{
	    return slideMapper.updateSlide(slide);
	}

	/**
     * 删除幻灯片对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSlideByIds(String ids)
	{
		return slideMapper.deleteSlideByIds(Convert.toStrArray(ids));
	}
    /**
     * 通过ID查详情
     *
     * @param id
     * @return Slide
     */
    @Override
    public Slide getSlideByIds(Long id) {
        return slideMapper.selectByPrimaryKey(id);
    }
}
