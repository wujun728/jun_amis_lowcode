package com.hope.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hope.model.bean.SysUserBean;

/**
 * @author aodeng
 **/
public interface ISysUserService extends IService<SysUserBean> {

    IPage<SysUserBean> selectUserListPageVo(Page page, SysUserBean dto);
}
