package com.ruoyi.project.demo.service;

import com.ruoyi.project.common.CommonService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 自定义Thymeleaf标签示例
 * @author ruoyi
 */
@Service
public class DemoDialectService extends CommonService {

    /**
     * 查询所有岗位
     * @return
     */
    public List<Map<String, Object>> selectAllPost() {
        String sql = "select post_id, post_code, post_name, post_sort, status, create_by, create_time, remark " +
                "  from sys_post where status = '0' order by post_sort";
        return db.queryForList(sql, null);
    }
}