package com.reading.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reading.app.mapper.ReIntegralMapper;
import com.reading.app.domain.ReIntegral;
import com.reading.app.service.IReIntegralService;

/**
 * 积分Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
@Service
public class ReIntegralServiceImpl implements IReIntegralService 
{
    @Autowired
    private ReIntegralMapper reIntegralMapper;

    /**
     * 查询积分
     * 
     * @param id 积分ID
     * @return 积分
     */
    @Override
    public ReIntegral selectReIntegralById(Long id)
    {
        return reIntegralMapper.selectReIntegralById(id);
    }

    /**
     * 查询积分列表
     * 
     * @param reIntegral 积分
     * @return 积分
     */
    @Override
    public List<ReIntegral> selectReIntegralList(ReIntegral reIntegral)
    {
        return reIntegralMapper.selectReIntegralList(reIntegral);
    }

    /**
     * 新增积分
     * 
     * @param reIntegral 积分
     * @return 结果
     */
    @Override
    public int insertReIntegral(ReIntegral reIntegral)
    {
        return reIntegralMapper.insertReIntegral(reIntegral);
    }

    /**
     * 修改积分
     * 
     * @param reIntegral 积分
     * @return 结果
     */
    @Override
    public int updateReIntegral(ReIntegral reIntegral)
    {
        return reIntegralMapper.updateReIntegral(reIntegral);
    }

    /**
     * 批量删除积分
     * 
     * @param ids 需要删除的积分ID
     * @return 结果
     */
    @Override
    public int deleteReIntegralByIds(Long[] ids)
    {
        return reIntegralMapper.deleteReIntegralByIds(ids);
    }

    /**
     * 删除积分信息
     * 
     * @param id 积分ID
     * @return 结果
     */
    @Override
    public int deleteReIntegralById(Long id)
    {
        return reIntegralMapper.deleteReIntegralById(id);
    }
}
