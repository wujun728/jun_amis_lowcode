package com.jun.biz.controller;

import java.util.Map;

import com.jun.common.ReturnT;

/**
* 用例信息
*
* Created by wujun on '2021-03-24 01:01:30'.
*/
public interface TTestcaseService {

    /**
    * 新增
    */
    public ReturnT<String> insert(TTestcase tTestcase);

    /**
    * 删除
    */
    public ReturnT<String> delete(int id);

    /**
    * 更新
    */
    public ReturnT<String> update(TTestcase tTestcase);

    /**
    * Load查询
    */
    public TTestcase load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
