package com.lanyu.admin.service.impl;

import com.lanyu.admin.mapper.RoleMapper;
import com.lanyu.admin.mapper.UserMapper;
import com.lanyu.admin.service.UserService;
import com.lanyu.common.model.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service(value = "userService")
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    /**
     * 新添加用户，密码加密，默认角色为persion
     */
    @Override
    public int insert(User user){
        String inputpassword = user.getPassword();
        //管理系统添加的用户使用默认密码123456
        if(inputpassword==null){
            inputpassword="123456";
        }
        String salt = user.getUser_name();
        //参数列表：加密方式，需要加密的值，加密盐，加密次数
        SimpleHash sh = new SimpleHash("md5", inputpassword, salt, 3);
        String password =sh.toHex();
        user.setPassword(password);
        userMapper.insert(user);
        int user_id = user.getUser_id();
        List<HashMap> rolmods = new ArrayList<HashMap>();
        HashMap role = new HashMap();
        role.put("uid",user_id);
        role.put("rcode","persion");
        rolmods.add(role);
        roleMapper.addUserRoles(rolmods);
        return 1;
    }

    @Override
    public int update(User record) {
        return userMapper.update(record);
    }

    @Override
    public int batchResetpw(String uidstr,String unamestr)  {
        String[] uids = uidstr.split(",");
        String[] unames = unamestr.split(",");
        List<HashMap> param = new ArrayList<HashMap>();
        for(int i=0;i< uids.length;i++){
            User user = new User();
            String username = unames[i];
            SimpleHash sh = new SimpleHash("md5", "123456", username, 3);
            user.setPassword(sh.toString());
            user.setUser_id(Integer.parseInt(uids[i]));
            userMapper.update(user);
        }
        return 0;
    }

    @Override
    public List<Map<String,Object>> findAllUser(HashMap queryparam) {
        List<Map<String,Object>> aa =  userMapper.selectAllUser(queryparam);
        return aa;
    }

    @Override
    public User selectByPhone(String username) {
        return userMapper.selectByPhone(username);
    }

    @Override
    public int deleteRoles(HashMap<String, Object> param) {
        return userMapper.deleteRoles(param);
    }

    /**
     * 删除用户，并且删除级联表
     */
    @Override
    public int deleteUser(String uidstr) {
        return userMapper.deleteBatch(uidstr);
    }
}
