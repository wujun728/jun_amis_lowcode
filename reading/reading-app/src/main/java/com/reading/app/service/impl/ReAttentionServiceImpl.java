package com.reading.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reading.app.mapper.ReAttentionMapper;
import com.reading.app.domain.ReAttention;
import com.reading.app.service.IReAttentionService;

/**
 * 关注Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-29
 */
@Service
public class ReAttentionServiceImpl implements IReAttentionService 
{
    @Autowired
    private ReAttentionMapper reAttentionMapper;

    /**
     * 查询关注
     * 
     * @param id 关注ID
     * @return 关注
     */
    @Override
    public ReAttention selectReAttentionById(Long id)
    {
        return reAttentionMapper.selectReAttentionById(id);
    }

    /**
     * 查询关注列表
     * 
     * @param reAttention 关注
     * @return 关注
     */
    @Override
    public List<ReAttention> selectReAttentionList(ReAttention reAttention)
    {
        return reAttentionMapper.selectReAttentionList(reAttention);
    }

    /**
     * 新增关注
     * 
     * @param reAttention 关注
     * @return 结果
     */
    @Override
    public int insertReAttention(ReAttention reAttention)
    {
        return reAttentionMapper.insertReAttention(reAttention);
    }

    /**
     * 修改关注
     * 
     * @param reAttention 关注
     * @return 结果
     */
    @Override
    public int updateReAttention(ReAttention reAttention)
    {
        return reAttentionMapper.updateReAttention(reAttention);
    }

    /**
     * 批量删除关注
     * 
     * @param ids 需要删除的关注ID
     * @return 结果
     */
    @Override
    public int deleteReAttentionByIds(Long[] ids)
    {
        return reAttentionMapper.deleteReAttentionByIds(ids);
    }

    /**
     * 删除关注信息
     * 
     * @param id 关注ID
     * @return 结果
     */
    @Override
    public int deleteReAttentionById(Long id)
    {
        return reAttentionMapper.deleteReAttentionById(id);
    }
}
