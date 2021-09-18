package com.jun.biz.manager.service;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.auth.LoginDTO;
import com.jun.biz.manager.vo.auth.AuthVO;

/**
 * Created on 2020/10/23 11:20
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
public interface AuthService {
    /**
     * 登录
     *
     * @param dto
     * @return
     */
    ResultVO<AuthVO> login(LoginDTO dto);
}
