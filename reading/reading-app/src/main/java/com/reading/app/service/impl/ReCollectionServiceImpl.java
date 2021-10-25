package com.reading.app.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reading.app.mapper.ReCollectionMapper;
import com.reading.app.domain.ReCollection;
import com.reading.app.service.IReCollectionService;

/**
 * 收藏列Service业务层处理
 * 
 * @author reading
 * @date 2021-03-29
 */
@Service
public class ReCollectionServiceImpl implements IReCollectionService 
{
    @Autowired
    private ReCollectionMapper reCollectionMapper;

    /**
     * 查询收藏列
     * 
     * @param id 收藏列ID
     * @return 收藏列
     */
    @Override
    public ReCollection selectReCollectionById(Long id)
    {
        return reCollectionMapper.selectReCollectionById(id);
    }

    /**
     * 查询收藏列列表
     * 
     * @param reCollection 收藏列
     * @return 收藏列
     */
    @Override
    public List<ReCollection> selectReCollectionList(ReCollection reCollection)
    {
        return reCollectionMapper.selectReCollectionList(reCollection);
    }

    /**
     * 新增收藏列
     * 
     * @param reCollection 收藏列
     * @return 结果
     */
    @Override
    public int insertReCollection(ReCollection reCollection)
    {
        List<ReCollection> list = selectReCollectionList(reCollection);
        if (list.size()>0){
            return 0;
        }
        reCollection.setState(1L);
        reCollection.setCollectionTime(new Date());
        return reCollectionMapper.insertReCollection(reCollection);
    }

    /**
     * 修改收藏列
     * 
     * @param reCollection 收藏列
     * @return 结果
     */
    @Override
    public int updateReCollection(ReCollection reCollection)
    {
        return reCollectionMapper.updateReCollection(reCollection);
    }

    /**
     * 批量删除收藏列
     * 
     * @param ids 需要删除的收藏列ID
     * @return 结果
     */
    @Override
    public int deleteReCollectionByIds(Long[] ids)
    {
        return reCollectionMapper.deleteReCollectionByIds(ids);
    }

    /**
     * 删除收藏列信息
     * 
     * @param id 收藏列ID
     * @return 结果
     */
    @Override
    public int deleteReCollectionById(Long id)
    {
        return reCollectionMapper.deleteReCollectionById(id);
    }
}
