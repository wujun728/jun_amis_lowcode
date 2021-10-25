package com.reading.app.mapper;

import java.util.List;
import com.reading.app.domain.ReLike;

/**
 * 点赞Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-29
 */
public interface ReLikeMapper 
{
    /**
     * 查询点赞
     * 
     * @param id 点赞ID
     * @return 点赞
     */
    public ReLike selectReLikeById(Long id);

    /**
     * 查询点赞列表
     * 
     * @param reLike 点赞
     * @return 点赞集合
     */
    public List<ReLike> selectReLikeList(ReLike reLike);

    /**
     * 新增点赞
     * 
     * @param reLike 点赞
     * @return 结果
     */
    public int insertReLike(ReLike reLike);

    /**
     * 修改点赞
     * 
     * @param reLike 点赞
     * @return 结果
     */
    public int updateReLike(ReLike reLike);

    /**
     * 删除点赞
     * 
     * @param id 点赞ID
     * @return 结果
     */
    public int deleteReLikeById(Long id);

    /**
     * 批量删除点赞
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteReLikeByIds(Long[] ids);
}
