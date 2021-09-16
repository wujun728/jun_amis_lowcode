package com.jun.plugin.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.system.common.Constant;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.common.ResultObj;
import com.jun.plugin.system.domain.Dept;
import com.jun.plugin.system.service.DeptService;
import com.jun.plugin.system.vo.DeptVo;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: DeptController create: 2020-04-17 19:07
 *
 * @author: Wujun @since： JDK1.8
 **/
@RestController
@RequestMapping("api/dept")
public class DeptController {
	@Autowired
	private DeptService deptService;

	/**
	 * 加载部门数据
	 *
	 * @param deptVo
	 * 
	 * @return
	 */
	@GetMapping("loadAllDept")
	public Object loadAllDept(DeptVo deptVo) {
		return this.deptService.queryAllDept(deptVo);
	}

	/**
	 * 查询部门最大的排序码
	 */
	@GetMapping("queryDeptMaxOrderNum")
	public Object queryDeptMaxOrderNum() {
		Integer maxValue = this.deptService.queryDeptMaxOrderNum();
		return new DataGridView(maxValue + 1);
	}

	/**
	 * 添加部门
	 *
	 * @param dept
	 * 
	 * @return
	 */
	@PostMapping("addDept")
	@RequiresPermissions("dept:add")
	public ResultObj addDept(Dept dept) {
		try {
			dept.setSpread(Constant.SPREAD_FALSE);
			dept.setAvailable(Constant.AVAILABLE_TRUE);
			this.deptService.saveDept(dept);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改
	 *
	 * @param dept
	 * 
	 * @return
	 */
	@PostMapping("updateDept")
	@RequiresPermissions("dept:update")
	public ResultObj updateDept(Dept dept) {
		try {
			this.deptService.updateDept(dept);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 根据id查询对应的部门信息
	 *
	 * @param id
	 * 
	 * @return
	 */
	@GetMapping("getDeptById")
	public Object getDeptById(Integer id) {
		return new DataGridView(this.deptService.getById(id));
	}

	/**
	 * 删除
	 *
	 * @param id
	 * 
	 * @return
	 */
	@PostMapping("deleteDept")
	public ResultObj deleteDept(Integer id) {
		try {
			this.deptService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 根据id查询当前部门 子部门的 个数
	 *
	 * @param id
	 * 
	 * @return
	 */
	@GetMapping("getDeptChildrenCountById")
	public Object getDeptChildrenCountById(Integer id) {
		return new DataGridView(this.deptService.getDeptChildrenCountById(id));
	}

	@GetMapping("clearAllRedisDB")
	public Map<String, Object> clearAllRedisDB() {
		Map<String, Object> map = new HashMap<>();
		try {
			this.deptService.clearAllRedisDB();
			map.put("code", 1);
			map.put("msg", "缓存清理成功");
			return map;
		} catch (Exception e) {
			map.put("code", -1);
			map.put("msg", "缓存清理失败");
			return map;
		}

	}

}
