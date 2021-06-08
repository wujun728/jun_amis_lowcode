package com.hope.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.mapper.SysUserMapper;
import com.hope.model.bean.SysUserBean;
import com.hope.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:aodeng
 * @create:2018-10-16 15:21
 **/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserBean> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public IPage<SysUserBean> selectUserListPageVo(Page page, SysUserBean dto) {
        return sysUserMapper.selectUserListPageVo(page, dto);
    }
}
