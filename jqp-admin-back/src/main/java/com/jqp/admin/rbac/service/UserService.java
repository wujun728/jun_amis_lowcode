package com.jqp.admin.rbac.service;

import com.jqp.admin.rbac.data.Enterprise;
import com.jqp.admin.rbac.data.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Enterprise> getUserEnterpriseList(User user);

    User get(Long id);
}


