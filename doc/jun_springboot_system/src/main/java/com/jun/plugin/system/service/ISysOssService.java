package com.jun.plugin.system.service;

import java.util.List;

import com.jun.plugin.system.domain.SysOss;

/**
 * 文件上传
 */
public interface ISysOssService
{
    /**
     * 列表查询方法
     * @param sysOss
     * @return
     * @author zmr
     */
    List<SysOss> getList(SysOss sysOss);

    /**
     * @param ossEntity
     * @author zmr
     */
    int save(SysOss ossEntity);

    /**
     * @param ossId
     * @return
     * @author zmr
     */
    SysOss findById(Long ossId);

    /**
     * @param sysOss
     * @return
     * @author zmr
     */
    int update(SysOss sysOss);

    /**
     * @param ids
     * @return
     * @author zmr
     */
    int deleteByIds(String ids);
}
