package com.hope.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.model.bean.SysUserBean;
import org.apache.ibatis.annotations.Param;

/**
 * @author aodeng
 **/
public interface SysUserMapper extends BaseMapper<SysUserBean> {

    IPage<SysUserBean> selectUserListPageVo(Page page, @Param("dto") SysUserBean dto);
}

