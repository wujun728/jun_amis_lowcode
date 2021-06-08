package com.htmall.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htmall.entity.SysDept;
import com.htmall.entity.excel.DeptExcel;
import com.htmall.excellistener.DeptExcelListener;
import com.htmall.mapper.SysDeptMapper;
import com.htmall.service.ISysDeptService;

/**
 *
 * SysDept 表数据服务层接口实现类
 *
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

	@Override
	public void importDept(MultipartFile file, ISysDeptService sysDeptService) {
		try {
			InputStream in = file.getInputStream();
			EasyExcel.read(in, DeptExcel.class, new DeptExcelListener(sysDeptService)).sheet().doRead();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<DeptExcel> listExportDepe(Map<String, Object> queryParam) {
		return this.baseMapper.listExportDepe(queryParam);
	}

}