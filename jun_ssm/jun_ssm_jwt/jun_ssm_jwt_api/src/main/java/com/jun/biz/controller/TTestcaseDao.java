package com.jun.biz.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* 用例信息
*
* Created by wujun on '2021-03-24 01:01:30'.
*/
@Component
public interface TTestcaseDao {

    /**
    * 新增
    */
    public int insert(@Param("tTestcase") TTestcase tTestcase);

    /**
    * 删除
    */
    public int delete(@Param("id") int id);

    /**
    * 更新
    */
    public int update(@Param("tTestcase") TTestcase tTestcase);

    /**
    * Load查询
    */
    public TTestcase load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<TTestcase> pageList(@Param("offset") int offset,
                                                 @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}

