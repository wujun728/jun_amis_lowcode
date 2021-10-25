package com.reading.app.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reading.app.mapper.ReLikeMapper;
import com.reading.app.domain.ReLike;
import com.reading.app.service.IReLikeService;

/**
 * 点赞Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-29
 */
@Service
public class ReLikeServiceImpl implements IReLikeService 
{
    @Autowired
    private ReLikeMapper reLikeMapper;

    /**
     * 查询点赞
     * 
     * @param id 点赞ID
     * @return 点赞
     */
    @Override
    public ReLike selectReLikeById(Long id)
    {
        return reLikeMapper.selectReLikeById(id);
    }

    /**
     * 查询点赞列表
     * 
     * @param reLike 点赞
     * @return 点赞
     */
    @Override
    public List<ReLike> selectReLikeList(ReLike reLike)
    {
        return reLikeMapper.selectReLikeList(reLike);
    }

    /**
     * 新增点赞
     * 
     * @param reLike 点赞
     * @return 结果
     */
    @Override
    public int insertReLike(ReLike reLike)
    {
        List<ReLike> list =  reLikeMapper.selectReLikeList(reLike);
        if (list.size()>0){
            return 0;
        }
        reLike.setState(1L);
        reLike.setTime(new Date());
        return reLikeMapper.insertReLike(reLike);
    }

    /**
     * 修改点赞
     * 
     * @param reLike 点赞
     * @return 结果
     */
    @Override
    public int updateReLike(ReLike reLike)
    {
        return reLikeMapper.updateReLike(reLike);
    }

    /**
     * 批量删除点赞
     * 
     * @param ids 需要删除的点赞ID
     * @return 结果
     */
    @Override
    public int deleteReLikeByIds(Long[] ids)
    {
        return reLikeMapper.deleteReLikeByIds(ids);
    }

    /**
     * 删除点赞信息
     * 
     * @param id 点赞ID
     * @return 结果
     */
    @Override
    public int deleteReLikeById(Long id)
    {
        return reLikeMapper.deleteReLikeById(id);
    }
}
