package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.mybatisplus.core.ServicePlusImpl;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.service.OperLogService;
import com.ruoyi.common.core.domain.dto.OperLogDTO;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.mapper.SysOperLogMapper;
import com.ruoyi.system.service.ISysOperLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 操作日志 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysOperLogServiceImpl extends ServicePlusImpl<SysOperLogMapper, SysOperLog, SysOperLog> implements ISysOperLogService, OperLogService {

    /**
     * 操作日志记录
     *
     * @param operLogDTO 操作日志信息
     */
    @Async
    @Override
    public void recordOper(final OperLogDTO operLogDTO) {
        SysOperLog operLog = BeanUtil.toBean(operLogDTO, SysOperLog.class);
        // 远程查询操作地点
        operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
        insertOperlog(operLog);
    }

    @Override
    public TableDataInfo<SysOperLog> selectPageOperLogList(SysOperLog operLog) {
        Map<String, Object> params = operLog.getParams();
        LambdaQueryWrapper<SysOperLog> lqw = new LambdaQueryWrapper<SysOperLog>()
                .like(StringUtils.isNotBlank(operLog.getTitle()), SysOperLog::getTitle, operLog.getTitle())
                .eq(operLog.getBusinessType() != null && operLog.getBusinessType() > 0,
                        SysOperLog::getBusinessType, operLog.getBusinessType())
                .func(f -> {
                    if (ArrayUtil.isNotEmpty(operLog.getBusinessTypes())) {
                        f.in(SysOperLog::getBusinessType, Arrays.asList(operLog.getBusinessTypes()));
                    }
                })
                .eq(operLog.getStatus() != null,
                        SysOperLog::getStatus, operLog.getStatus())
                .like(StringUtils.isNotBlank(operLog.getOperName()), SysOperLog::getOperName, operLog.getOperName())
                .apply(StringUtils.isNotEmpty(params.get("beginTime")),
                        "date_format(oper_time,'%y%m%d') >= date_format({0},'%y%m%d')",
                        params.get("beginTime"))
                .apply(StringUtils.isNotEmpty(params.get("endTime")),
                        "date_format(oper_time,'%y%m%d') <= date_format({0},'%y%m%d')",
                        params.get("endTime"));
        return PageUtils.buildDataInfo(page(PageUtils.buildPage("oper_id","desc"), lqw));
    }

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog) {
        operLog.setOperTime(new Date());
        save(operLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        Map<String, Object> params = operLog.getParams();
        return list(new LambdaQueryWrapper<SysOperLog>()
                .like(StringUtils.isNotBlank(operLog.getTitle()),SysOperLog::getTitle,operLog.getTitle())
                .eq(operLog.getBusinessType() != null && operLog.getBusinessType() > 0,
                        SysOperLog::getBusinessType,operLog.getBusinessType())
                .func(f -> {
                    if (ArrayUtil.isNotEmpty(operLog.getBusinessTypes())){
                        f.in(SysOperLog::getBusinessType, Arrays.asList(operLog.getBusinessTypes()));
                    }
                })
                .eq(operLog.getStatus() != null && operLog.getStatus() > 0,
                        SysOperLog::getStatus,operLog.getStatus())
                .like(StringUtils.isNotBlank(operLog.getOperName()),SysOperLog::getOperName,operLog.getOperName())
                .apply(StringUtils.isNotEmpty(params.get("beginTime")),
                        "date_format(oper_time,'%y%m%d') >= date_format({0},'%y%m%d')",
                        params.get("beginTime"))
                .apply(StringUtils.isNotEmpty(params.get("endTime")),
                        "date_format(oper_time,'%y%m%d') <= date_format({0},'%y%m%d')",
                        params.get("endTime"))
                .orderByDesc(SysOperLog::getOperId));
    }

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperLogByIds(Long[] operIds) {
        return baseMapper.deleteBatchIds(Arrays.asList(operIds));
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId) {
        return getById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {
        remove(new LambdaQueryWrapper<>());
    }
}
