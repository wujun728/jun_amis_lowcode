package com.reading.app.service.impl;

import java.util.Date;
import java.util.List;
import com.reading.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reading.app.mapper.ReRemarkMapper;
import com.reading.app.domain.ReRemark;
import com.reading.app.service.IReRemarkService;

/**
 * 评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-28
 */
@Service
public class ReRemarkServiceImpl implements IReRemarkService 
{
    @Autowired
    private ReRemarkMapper reRemarkMapper;

    /**
     * 查询评论
     * 
     * @param id 评论ID
     * @return 评论
     */
    @Override
    public ReRemark selectReRemarkById(Long id)
    {
        return reRemarkMapper.selectReRemarkById(id);
    }

    /**
     * 查询评论列表
     * 
     * @param reRemark 评论
     * @return 评论
     */
    @Override
    public List<ReRemark> selectReRemarkList(ReRemark reRemark)
    {
        return reRemarkMapper.selectReRemarkList(reRemark);
    }

    /**
     * 新增评论
     * 
     * @param reRemark 评论
     * @return 结果
     */
    @Override
    public int insertReRemark(ReRemark reRemark)
    {
        reRemark.setCreateTime(new Date());
        return reRemarkMapper.insertReRemark(reRemark);
    }

    /**
     * 修改评论
     * 
     * @param reRemark 评论
     * @return 结果
     */
    @Override
    public int updateReRemark(ReRemark reRemark)
    {
        reRemark.setUpdateTime(DateUtils.getNowDate());
        return reRemarkMapper.updateReRemark(reRemark);
    }

    /**
     * 批量删除评论
     * 
     * @param ids 需要删除的评论ID
     * @return 结果
     */
    @Override
    public int deleteReRemarkByIds(Long[] ids)
    {
        return reRemarkMapper.deleteReRemarkByIds(ids);
    }

    /**
     * 删除评论信息
     * 
     * @param id 评论ID
     * @return 结果
     */
    @Override
    public int deleteReRemarkById(Long id)
    {
        return reRemarkMapper.deleteReRemarkById(id);
    }
}
