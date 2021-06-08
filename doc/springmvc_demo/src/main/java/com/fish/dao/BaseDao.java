package com.fish.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/17
 * Time: 9:14
 */
public class BaseDao {
    @Autowired
    protected JdbcTemplate jdbcTemplate;
}
