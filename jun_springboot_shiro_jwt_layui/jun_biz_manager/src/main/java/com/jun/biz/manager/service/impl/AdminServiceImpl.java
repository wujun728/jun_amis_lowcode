
package com.jun.biz.manager.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.common.runtime.AdminSessionHolder;
import com.jun.biz.manager.dao.*;
import com.jun.biz.manager.dto.admin.*;
import com.jun.biz.manager.mapstruct.AdminConverter;
import com.jun.biz.manager.mapstruct.AdminLoginRecordConverter;
import com.jun.biz.manager.model.*;
import com.jun.biz.manager.model.enums.AdminStatus;
import com.jun.biz.manager.service.AdminService;
import com.jun.biz.manager.service.PermissionService;
import com.jun.biz.manager.vo.admin.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 2020/10/14 20:12
 * <p>
 * Description: [TODO]
 * <p>
 *
 * 
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Value("${mall.file.domain}")
    private String frontDomain;
    @Resource
    private AdminLoginRecordConverter adminLoginRecordConverter;
    @Resource
    private AdminLoginRecordDao adminLoginRecordDao;
    @Resource
    private AdminConverter adminConverter;
    @Resource
    private AdminDao adminDao;
    @Resource
    private AdminRoleDao adminRoleDao;
    @Resource
    private PermissionService permissionService;
    @Resource
    private UserDao userDao;
@Resource
    private ProductDao productDao;
    @Resource
    private OrderDao orderDao;
    @Resource
    private SessionTokenDao sessionTokenDao;


    @Override
    public ResultVO<AdminInitVO> init() {
        ResultVO<List<PermissionVO>> setResultVO = permissionService.menuTree();
        AdminInitVO adminInitVO = new AdminInitVO();
        adminInitVO.setMenuInfo(setResultVO.getData());
        adminInitVO.setRealName(AdminSessionHolder.getCurrentAdmin().getRealName());
        Set<String> perCodes = AdminSessionHolder.getPermission().stream().map(Permission::getCode).collect(Collectors.toSet());
        adminInitVO.setPerCodes(perCodes);
        adminInitVO.setFrontDomain(frontDomain);
        return ResultVO.buildSuccessResult(adminInitVO);
    }

    @Override
    public ResultVO<AdminVO> myInfo() {
        Long adminId = AdminSessionHolder.getCurrentAdmin().getAdminId();
        Admin admin = adminDao.selectByPk(adminId);
        return ResultVO.buildSuccessResult(adminConverter.toVo(admin));
    }

    @Override
    public ResultVO<Boolean> modifyMyInfo(AdminDTO dto) {
        dto.setId(AdminSessionHolder.getCurrentAdmin().getAdminId());
        Admin admin = adminConverter.toEntity(dto);
        adminDao.update(admin, "realName", "phone", "email");
        return ResultVO.buildSuccessResult();
    }

    @Override
    public ResultVO<Boolean> modifyMyPassword(ModifyMyPasswordDTO dto) {
        if (!Objects.equals(dto.getPassword(), dto.getConfirmPassword())) {
            return ResultVO.buildFailResult("两次密码不一致！");
        }
        Long adminId = AdminSessionHolder.getCurrentAdmin().getAdminId();
        Admin admin = adminDao.selectByPk(adminId);
        if (!Objects.equals(admin.getPassword(), DigestUtils.md5DigestAsHex(dto.getOldPassword().getBytes()))) {
            return ResultVO.buildFailResult("原密码错误！");
        }
        admin.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        adminDao.update(admin, "password");
        return ResultVO.buildSuccessResult();
    }


    @Override
    public ResultVO<Boolean> changeStatus(ChangeStatusDTO dto) {
        Admin admin = adminDao.selectByPk(dto.getId());
        if (admin == null) {
            return ResultVO.buildFailResult("管理员不存在");
        }
        if (!Objects.equals(admin.getStatus(), dto.getStatus())) {
            admin.setStatus(dto.getStatus());
            adminDao.update(admin, "status");
        }
        return ResultVO.buildSuccessResult();
    }

    @Override
    public ResultVO<ListAdminLoginRecordVO> listLoginRecored(ListAdminLoginRecordDTO dto) {
        if (StringUtils.isBlank(dto.getOrderBy())) {
            dto.setOrderBy("id desc");
        }
        Map<String, Object> cond = dto.toMap();
        long count = adminLoginRecordDao.count(cond);
        List<AdminLoginRecord> adminLoginRecords = adminLoginRecordDao.selectList(cond);
        ListAdminLoginRecordVO vo = new ListAdminLoginRecordVO();
        vo.setTotal(count);
        vo.setList(adminLoginRecordConverter.toVo(adminLoginRecords));
        return ResultVO.buildSuccessResult(vo);
    }

    @Override
    public ResultVO<WelcomeVO> welcome() {
        WelcomeVO vo = new WelcomeVO();
        vo.setUserNum(userDao.count(null));
        vo.setProductNum(productDao.count(null));
        vo.setOrderNum(orderDao.count(null));
        vo.setOrderAmount(orderDao.sumAmount(null));
        Date minDate = Date.from(LocalDateTime.now().minusMonths(1).atZone(ZoneId.systemDefault()).toInstant());
        vo.setLoginCount(sessionTokenDao.countToken(minDate,null));
        vo.setOrderCount(orderDao.countOrder(minDate,null));
        return ResultVO.buildSuccessResult(vo);
    }


    @Override
    public ResultVO<ListAdminVO> list(ListAdminDTO dto) {
        ListAdminVO vo = new ListAdminVO();
        Map<String, Object> cond = dto.toMap();
        long count = adminDao.count(cond);
        vo.setTotal(count);
        if(count == 0 ){
            vo.setList(Collections.emptyList());
            return ResultVO.buildSuccessResult(vo);
        }

        List<Admin> admins = adminDao.selectList(cond);
        List<Long> adminIds = admins.stream().map(Admin::getId).collect(Collectors.toList());
        List<AdminRole> roles = adminRoleDao.selectRoleByAdmin(adminIds);

        Map<Object, List<AdminRole>> adminRoleMap = roles.stream().collect(Collectors.toMap(AdminRole::getAdminId,
                adminRole -> {
                    List<AdminRole> roleList = new ArrayList<>();
                    roleList.add(adminRole);
                    return roleList;
                },
                (List<AdminRole> value1, List<AdminRole> value2) -> {
                    value1.addAll(value2);
                    return value1;
                }));
        List<AdminVO> adminVos = adminConverter.toVo(admins);
        for (AdminVO adminVO : adminVos) {
            List<AdminRole> roleList = adminRoleMap.get(adminVO.getId());
            adminVO.setRoles(roleList);
        }
        vo.setList(adminVos);
        return ResultVO.buildSuccessResult(vo);
    }

    @Override
    public ResultVO<Boolean> create(AdminDTO dto) {
        Map<String, Object> cond = new HashMap<>(2);
        cond.put("username", dto.getUsername());
        long count = adminDao.count(cond);
        if (count > 0) {
            return ResultVO.buildFailResult("用户名已存在！");
        }
        Admin entity = adminConverter.toEntity(dto);
        entity.setCreateTime(new Date());
        entity.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        entity.setStatus(AdminStatus.NORMAL.getCode());
        entity.setCreateAdmin(AdminSessionHolder.getCurrentAdmin().getUsername());
        adminDao.insert(entity);
        for (String roleId : dto.getRoleIds().split(",")) {
            AdminRole adminRole = new AdminRole();
            adminRole.setRoleId(Long.valueOf(roleId));
            adminRole.setAdminId(entity.getId());
            adminRoleDao.insert(adminRole);
        }
        return ResultVO.buildSuccessResult();
    }

    @Override
    public ResultVO<Boolean> modify(AdminDTO dto) {
        Admin admin = adminDao.selectByPk(dto.getId());
        if (admin == null) {
            return ResultVO.buildFailResult("管理员不存在");
        }
        Admin entity = adminConverter.toEntity(dto);
        if (StringUtils.isNotBlank(dto.getPassword())) {
            entity.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        } else {
            entity.setPassword(null);
        }
        adminDao.update(entity, "realName", "password", "email", "phone");
        adminRoleDao.deleteByAdmin(dto.getId());
        for (String roleId : dto.getRoleIds().split(",")) {
            AdminRole adminRole = new AdminRole();
            adminRole.setRoleId(Long.valueOf(roleId));
            adminRole.setAdminId(dto.getId());
            adminRoleDao.insert(adminRole);
        }
        return ResultVO.buildSuccessResult();
    }

    @Override
    public ResultVO<Boolean> delete(Set<Long> ids) {
        for (Long id : ids) {
            adminDao.delete(id);
        }
        return ResultVO.buildSuccessResult();
    }

}
