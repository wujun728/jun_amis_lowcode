package com.jun.biz.manager.service;

import java.util.Set;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.admin.*;
import com.jun.biz.manager.vo.admin.AdminInitVO;
import com.jun.biz.manager.vo.admin.AdminVO;
import com.jun.biz.manager.vo.admin.ListAdminLoginRecordVO;
import com.jun.biz.manager.vo.admin.ListAdminVO;


public interface AdminService {

    /**
     * 初始化首页
     *
     * @return
     */
    ResultVO<AdminInitVO> init();

    ResultVO<AdminVO> myInfo();

    ResultVO<Boolean> modifyMyInfo(AdminDTO dto);

    ResultVO<Boolean> modifyMyPassword(ModifyMyPasswordDTO dto);

    ResultVO<Boolean> create(AdminDTO dto);

    ResultVO<Boolean> modify(AdminDTO dto);

    ResultVO<Boolean> delete(Set<Long> ids);

    ResultVO<ListAdminVO> list(ListAdminDTO dto);

    ResultVO<Boolean> changeStatus(ChangeStatusDTO dto);

    ResultVO<ListAdminLoginRecordVO> listLoginRecored(ListAdminLoginRecordDTO dto) ;

    ResultVO<WelcomeVO> welcome();
}
