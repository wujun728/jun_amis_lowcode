package com.reading.app.mapper;

import java.util.List;
import com.reading.app.domain.ReIntegral;

/**
 * 积分Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
public interface ReIntegralMapper 
{
    /**
     * 查询积分
     * 
     * @param id 积分ID
     * @return 积分
     */
    public ReIntegral selectReIntegralById(Long id);

    /**
     * 查询积分列表
     * 
     * @param reIntegral 积分
     * @return 积分集合
     */
    public List<ReIntegral> selectReIntegralList(ReIntegral reIntegral);

    /**
     * 新增积分
     * 
     * @param reIntegral 积分
     * @return 结果
     */
    public int insertReIntegral(ReIntegral reIntegral);

    /**
     * 修改积分
     * 
     * @param reIntegral 积分
     * @return 结果
     */
    public int updateReIntegral(ReIntegral reIntegral);

    /**
     * 删除积分
     * 
     * @param id 积分ID
     * @return 结果
     */
    public int deleteReIntegralById(Long id);

    /**
     * 批量删除积分
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteReIntegralByIds(Long[] ids);
}
