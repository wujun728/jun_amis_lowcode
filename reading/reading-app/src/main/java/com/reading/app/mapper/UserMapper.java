package com.reading.app.mapper;

import org.apache.ibatis.annotations.Param;
import com.reading.app.domain.User;

public interface UserMapper {

    int insertUser(User user);

    User selectUserzbyAid(@Param("aid") int aid);

    int updateUser(User user);
}
