package com.lanyu.admin.mapper;

import com.lanyu.common.model.User;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public interface UserMapper {

    @Options(useGeneratedKeys=true, keyProperty="user_id", keyColumn="user_id")
    int insert(User user);

    User selectByPrimaryKey(Integer userId);

    User selectByPhone(String username);

    int update(User record);

    int deleteBatch(@Param(value="uidstr")String uidstr);
    /**
     *通过用户id或者角色code删除用户角色关联表
     */
    int deleteRoles(HashMap<String,Object> param);

    List<Map<String,Object>> selectAllUser(HashMap<String, Object> param);
}