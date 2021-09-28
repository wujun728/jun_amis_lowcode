package com.jun.plugin.ow.service.impl;

import com.jun.plugin.common.support.Convert;
import com.jun.plugin.ow.domain.About;
import com.jun.plugin.ow.mapper.AboutMapper;
import com.jun.plugin.ow.service.IAboutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关于我们 服务层实现
 * 
 * @author admin
 * @date 2018-12-13
 */
@Service
public class AboutServiceImpl implements IAboutService 
{
	@Autowired
	private AboutMapper aboutMapper;

	/**
     * 查询关于我们信息
     * 
     * @param id 关于我们ID
     * @return 关于我们信息
     */
    @Override
	public About selectAboutById(Integer id)
	{
	    return aboutMapper.selectAboutById(id);
	}
	
	/**
     * 查询关于我们列表
     * 
     * @param about 关于我们信息
     * @return 关于我们集合
     */
	@Override
	public List<About> selectAboutList(About about)
	{
	    return aboutMapper.selectAboutList(about);
	}

    /**
     * 查询关于我们列表for 官网
     *
     * @param about 关于我们信息
     * @return 关于我们集合
     */
    @Override
    public List<About> selectAboutListForOw(About about) {
        return aboutMapper.selectAboutList(about);
    }

    /**
     * 新增关于我们
     * 
     * @param about 关于我们信息
     * @return 结果
     */
	@Override
	public int insertAbout(About about)
	{
	    return aboutMapper.insertAbout(about);
	}
	
	/**
     * 修改关于我们
     * 
     * @param about 关于我们信息
     * @return 结果
     */
	@Override
	public int updateAbout(About about)
	{
	    return aboutMapper.updateAbout(about);
	}

	/**
     * 删除关于我们对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAboutByIds(String ids)
	{
		return aboutMapper.deleteAboutByIds(Convert.toStrArray(ids));
	}
    /**
     * 通过ID查详情
     *
     * @param id
     * @return About
     */
    @Override
    public About getAboutByIds(Long id) {
        return aboutMapper.selectByPrimaryKey(id);
    }
}
