package com.reading.app.service;

import java.util.List;
import com.reading.app.domain.ReCollection;

/**
 * 收藏列Service接口
 * 
 * @author reading
 * @date 2021-03-29
 */
public interface IReCollectionService 
{
    /**
     * 查询收藏列
     * 
     * @param id 收藏列ID
     * @return 收藏列
     */
    public ReCollection selectReCollectionById(Long id);

    /**
     * 查询收藏列列表
     * 
     * @param reCollection 收藏列
     * @return 收藏列集合
     */
    public List<ReCollection> selectReCollectionList(ReCollection reCollection);

    /**
     * 新增收藏列
     * 
     * @param reCollection 收藏列
     * @return 结果
     */
    public int insertReCollection(ReCollection reCollection);

    /**
     * 修改收藏列
     * 
     * @param reCollection 收藏列
     * @return 结果
     */
    public int updateReCollection(ReCollection reCollection);

    /**
     * 批量删除收藏列
     * 
     * @param ids 需要删除的收藏列ID
     * @return 结果
     */
    public int deleteReCollectionByIds(Long[] ids);

    /**
     * 删除收藏列信息
     * 
     * @param id 收藏列ID
     * @return 结果
     */
    public int deleteReCollectionById(Long id);
}
