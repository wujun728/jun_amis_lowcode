package com.lanyu.admin.service;

import com.lanyu.common.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/16.
 */
public interface UserService {

    int insert(User user);

    int update(User record);

    int batchResetpw(String uidstr,String unamestr);

    List<Map<String,Object>> findAllUser(HashMap queryparm);

    User selectByPhone(String username);

    int deleteRoles(HashMap<String, Object> param);

    int deleteUser(String uidstr);
}
