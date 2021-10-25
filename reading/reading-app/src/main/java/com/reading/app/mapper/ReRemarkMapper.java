package com.reading.app.mapper;

import java.util.List;
import com.reading.app.domain.ReRemark;

/**
 * 评论Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-28
 */
public interface ReRemarkMapper 
{
    /**
     * 查询评论
     * 
     * @param id 评论ID
     * @return 评论
     */
    public ReRemark selectReRemarkById(Long id);

    /**
     * 查询评论列表
     * 
     * @param reRemark 评论
     * @return 评论集合
     */
    public List<ReRemark> selectReRemarkList(ReRemark reRemark);

    /**
     * 新增评论
     * 
     * @param reRemark 评论
     * @return 结果
     */
    public int insertReRemark(ReRemark reRemark);

    /**
     * 修改评论
     * 
     * @param reRemark 评论
     * @return 结果
     */
    public int updateReRemark(ReRemark reRemark);

    /**
     * 删除评论
     * 
     * @param id 评论ID
     * @return 结果
     */
    public int deleteReRemarkById(Long id);

    /**
     * 批量删除评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteReRemarkByIds(Long[] ids);
}
