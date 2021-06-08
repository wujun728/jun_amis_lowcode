package com.htmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;
import com.htmall.entity.SysDept;
import com.htmall.entity.excel.DeptExcel;

/**
 *
 * SysDept 表数据服务层接口
 *
 */
public interface ISysDeptService extends IService<SysDept> {

	void importDept(MultipartFile file, ISysDeptService sysDeptService);

	List<DeptExcel> listExportDepe(Map<String, Object> queryParam);

}