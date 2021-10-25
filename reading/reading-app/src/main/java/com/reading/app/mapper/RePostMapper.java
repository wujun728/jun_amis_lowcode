package com.reading.app.mapper;

import java.util.List;
import com.reading.app.domain.RePost;

/**
 * 帖子Mapper接口
 * 
 * @author cj
 * @date 2021-03-24
 */
public interface RePostMapper 
{
    /**
     * 查询帖子
     * 
     * @param id 帖子ID
     * @return 帖子
     */
    public RePost selectRePostById(Long id);

    /**
     * 查询帖子列表
     * 
     * @param rePost 帖子
     * @return 帖子集合
     */
    public List<RePost> selectRePostList(RePost rePost);

    /**
     * 新增帖子
     * 
     * @param rePost 帖子
     * @return 结果
     */
    public int insertRePost(RePost rePost);

    /**
     * 修改帖子
     * 
     * @param rePost 帖子
     * @return 结果
     */
    public int updateRePost(RePost rePost);

    /**
     * 删除帖子
     * 
     * @param id 帖子ID
     * @return 结果
     */
    public int deleteRePostById(Long id);

    /**
     * 批量删除帖子
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRePostByIds(Long[] ids);
}
