package com.reading.app.mapper;

import java.util.List;
import com.reading.app.domain.ReAttention;

/**
 * 关注Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-29
 */
public interface ReAttentionMapper 
{
    /**
     * 查询关注
     * 
     * @param id 关注ID
     * @return 关注
     */
    public ReAttention selectReAttentionById(Long id);

    /**
     * 查询关注列表
     * 
     * @param reAttention 关注
     * @return 关注集合
     */
    public List<ReAttention> selectReAttentionList(ReAttention reAttention);

    /**
     * 新增关注
     * 
     * @param reAttention 关注
     * @return 结果
     */
    public int insertReAttention(ReAttention reAttention);

    /**
     * 修改关注
     * 
     * @param reAttention 关注
     * @return 结果
     */
    public int updateReAttention(ReAttention reAttention);

    /**
     * 删除关注
     * 
     * @param id 关注ID
     * @return 结果
     */
    public int deleteReAttentionById(Long id);

    /**
     * 批量删除关注
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteReAttentionByIds(Long[] ids);
}
