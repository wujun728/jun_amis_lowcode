package com.mall.admin.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2018/11/2415:14
 */
public abstract class AdminBaseService {

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;
}
