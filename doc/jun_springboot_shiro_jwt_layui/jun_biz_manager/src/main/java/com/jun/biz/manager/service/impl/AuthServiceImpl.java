package com.jun.biz.manager.service.impl;

import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jun.biz.common.base.enums.VoCodeEnum;
import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.common.utils.HttpUtil;
import com.jun.biz.manager.dao.AdminDao;
import com.jun.biz.manager.dao.AdminLoginRecordDao;
import com.jun.biz.manager.dto.auth.LoginDTO;
import com.jun.biz.manager.model.Admin;
import com.jun.biz.manager.model.AdminLoginRecord;
import com.jun.biz.manager.model.enums.AdminStatus;
import com.jun.biz.manager.service.AuthService;
import com.jun.biz.manager.vo.auth.AuthVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * Created on 2020/10/23 11:23
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AdminDao adminDao;
    @Resource
    private AdminLoginRecordDao adminLoginRecordDao;
    @Override
    public ResultVO<AuthVO> login(LoginDTO dto) {
        Admin admin = adminDao.selectByUsername(dto.getUsername());
        if (admin == null || !Objects.equals(admin.getPassword(), DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()))) {
            return ResultVO.buildResult(VoCodeEnum.PASSWORD_ERROR);
        }
        if (AdminStatus.FROZEN.getCode().equals(admin.getStatus())) {
            return ResultVO.buildResult(VoCodeEnum.USER_STATUS_ERROR);
        }
        admin.setLastLoginTime(new Date());
        adminDao.update(admin, "lastLoginTime");
        AuthVO vo = new AuthVO();
        vo.setAdminId(admin.getId());
        vo.setRealName(admin.getRealName());
        vo.setUsername(admin.getUsername());

        AdminLoginRecord loginRecord = obtainRequest();
        loginRecord.setAdminId(admin.getId());
        loginRecord.setAdminUserName(admin.getUsername());
        adminLoginRecordDao.insert(loginRecord);
        return ResultVO.buildSuccessResult(vo);
    }

    private AdminLoginRecord obtainRequest() {
        HttpServletRequest request =((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String agent = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        AdminLoginRecord loginRecord  = new AdminLoginRecord();
        loginRecord.setCreateTime(new Date());
        loginRecord.setIp(HttpUtil.getRemoteAddr(request));
        loginRecord.setBrowser(userAgent.getBrowser().getName());
        loginRecord.setDevice(userAgent.getOperatingSystem().getDeviceType().getName());
        loginRecord.setOs(userAgent.getOperatingSystem().getName());
        return loginRecord;
    }
}
