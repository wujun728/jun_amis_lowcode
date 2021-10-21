
package com.jun.biz.manager.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dao.SessionTokenDao;
import com.jun.biz.manager.dao.UserDao;
import com.jun.biz.manager.dto.user.ChangeStatusDTO;
import com.jun.biz.manager.dto.user.ListUserDTO;
import com.jun.biz.manager.dto.user.ModifyUserDTO;
import com.jun.biz.manager.mapstruct.UserConverter;
import com.jun.biz.manager.model.SessionToken;
import com.jun.biz.manager.model.User;
import com.jun.biz.manager.model.enums.SessionTokenStatus;
import com.jun.biz.manager.service.UserService;
import com.jun.biz.manager.vo.user.ListUserVO;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created on 2020/10/14 20:12
 * <p>
 * Description: [TODO]
 * <p>
 *
 * 
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserConverter userConverter;
    @Resource
    private UserDao userDao;
    @Resource
    private SessionTokenDao sessionTokenDao;
@Resource
    private RedisTemplate<String,SessionToken> redisTemplate;
    @Override
    public ResultVO<ListUserVO> list(ListUserDTO dto) {
        if (StringUtils.isBlank(dto.getOrderBy())) {
            dto.setOrderBy("id desc");
        }
        Map<String, Object> cond = dto.toMap();
        long count = userDao.count(cond);
        List<User> users = userDao.selectList(cond);
        ListUserVO vo = new ListUserVO();
        vo.setTotal(count);
        vo.setList(userConverter.toVo(users));
        return ResultVO.buildSuccessResult(vo);
    }

    @Override
    public ResultVO<Boolean> changeStatus(ChangeStatusDTO dto) {
        User user = userDao.selectByPk(dto.getId());
        if (user == null) {
            return ResultVO.buildFailResult("用户不存在");
        }
        if (!Objects.equals(user.getStatus(), dto.getStatus())) {
            user.setStatus(dto.getStatus());
            userDao.update(user, "status");
        }
        Map<String, Object> cond = new HashMap<>(2);
        cond.put("userId", dto.getId());
        cond.put("status", SessionTokenStatus.VALID.getCode());
        List<SessionToken> sessionTokens = sessionTokenDao.selectList(cond);
        for (SessionToken st : sessionTokens) {
            st.setStatus(SessionTokenStatus.INVALID.getCode());
            sessionTokenDao.update(st, "status");
            redisTemplate.delete("mall:token:" + st.getSessionToken());
        }

        return ResultVO.buildSuccessResult();
    }


    @Override
    public ResultVO<Boolean> modify(ModifyUserDTO dto) {
        User user = userDao.selectByPk(dto.getId());
        if (user == null) {
            return ResultVO.buildFailResult("角色不存在");
        }


        User entity = userConverter.modifyDtoToEntity(dto);
        userDao.update(entity);
        return ResultVO.buildSuccessResult();
    }

    @Override
    public ResultVO<Boolean> delete(Set<Long> ids) {
        for (Long id : ids) {
            userDao.delete(id);
        }
        return ResultVO.buildSuccessResult();
    }

}
