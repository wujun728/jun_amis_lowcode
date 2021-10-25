package com.reading.app.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reading.app.mapper.RePostMapper;
import com.reading.app.domain.RePost;
import com.reading.app.service.IRePostService;

/**
 * 帖子Service业务层处理
 * 
 * @author cj
 * @date 2021-03-24
 */
@Service
public class RePostServiceImpl implements IRePostService 
{
    @Autowired
    private RePostMapper rePostMapper;

    /**
     * 查询帖子
     * 
     * @param id 帖子ID
     * @return 帖子
     */
    @Override
    public RePost selectRePostById(Long id)
    {
        return rePostMapper.selectRePostById(id);
    }

    /**
     * 查询帖子列表
     * 
     * @param rePost 帖子
     * @return 帖子
     */
    @Override
    public List<RePost> selectRePostList(RePost rePost)
    {
        return rePostMapper.selectRePostList(rePost);
    }

    /**
     * 新增帖子
     * 
     * @param rePost 帖子
     * @return 结果
     */
    @Override
    public int insertRePost(RePost rePost)
    {
        rePost.setCreatetime(new Date());
        return rePostMapper.insertRePost(rePost);
    }

    /**
     * 修改帖子
     * 
     * @param rePost 帖子
     * @return 结果
     */
    @Override
    public int updateRePost(RePost rePost)
    {
        return rePostMapper.updateRePost(rePost);
    }

    /**
     * 批量删除帖子
     * 
     * @param ids 需要删除的帖子ID
     * @return 结果
     */
    @Override
    public int deleteRePostByIds(Long[] ids)
    {
        return rePostMapper.deleteRePostByIds(ids);
    }

    /**
     * 删除帖子信息
     * 
     * @param id 帖子ID
     * @return 结果
     */
    @Override
    public int deleteRePostById(Long id)
    {
        return rePostMapper.deleteRePostById(id);
    }
}
