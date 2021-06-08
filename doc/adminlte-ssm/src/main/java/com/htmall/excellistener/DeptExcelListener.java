package com.htmall.excellistener;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.htmall.entity.SysDept;
import com.htmall.entity.excel.DeptExcel;
import com.htmall.service.ISysDeptService;

public class DeptExcelListener extends AnalysisEventListener<DeptExcel> {

	/**
	 * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
	 */
	private static final int BATCH_COUNT = 5;
	List<SysDept> deptList = new ArrayList<SysDept>(BATCH_COUNT);

	private ISysDeptService sysDeptService;

	public DeptExcelListener() {
	}

	/**
	 * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
	 *
	 * @param sysDeptService
	 */
	public DeptExcelListener(ISysDeptService sysDeptService) {
		this.sysDeptService = sysDeptService;
	}

	/**
	 * 这个每一条数据解析都会来调用
	 *
	 * @param deptExcel
	 * @param context
	 */
	@Override
	public void invoke(DeptExcel deptExcel, AnalysisContext context) {
		SysDept sysDept = new SysDept();
		sysDept.setDeptName(deptExcel.getDeptName());
		sysDept.setDeptDesc(deptExcel.getDeptDesc());
		deptList.add(sysDept);
		if (deptList.size() >= BATCH_COUNT) {
			sysDeptService.saveBatch(deptList);
			// 存储完成清理 list
			deptList.clear();
		}

	}

	/**
	 * 所有数据解析完成了 都会来调用
	 *
	 * @param context
	 */
	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		// 这里也要保存数据，确保最后遗留的数据也存储到数据库
		if (deptList.size() > 0) {
			sysDeptService.saveBatch(deptList);
		}
	}

}
