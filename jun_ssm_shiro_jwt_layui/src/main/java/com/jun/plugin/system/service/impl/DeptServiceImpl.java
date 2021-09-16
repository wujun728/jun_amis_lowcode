package com.jun.plugin.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.decorators.LoggingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.business.domain.Goods;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.domain.Dept;
import com.jun.plugin.system.mapper.DeptMapper;
import com.jun.plugin.system.service.DeptService;
import com.jun.plugin.system.vo.DeptVo;

import javax.sound.sampled.Line;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/17
 *
 * 
 * 
 * @since JDK 1.8
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
	/**
	 * 日志提示信息
	 */
	private Log log = LogFactory.getLog(DeptServiceImpl.class);

	@Autowired
	private DeptMapper deptMapper;

	/**
	 * 查询所有部门信息 带高级查询
	 *
	 * @param deptVo
	 * @return
	 */
	@Override
	public DataGridView queryAllDept(DeptVo deptVo) {
		QueryWrapper<Dept> qw = new QueryWrapper<>();
		qw.like(StringUtils.isNotBlank(deptVo.getTitle()), "title", deptVo.getTitle());
		qw.orderByAsc("ordernum");
		List<Dept> depts = deptMapper.selectList(qw);
		// 前端treeTable 必要参数 count
		return new DataGridView(Long.valueOf(depts.size()), depts);
	}

	/**
	 * 查询最大排序码
	 *
	 * @return
	 */
	@Override
	public Integer queryDeptMaxOrderNum() {
		return this.deptMapper.queryDeptMaxOrderNum();
	}

	/**
	 * 根据id查询当前部门 子部门的 个数
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Integer getDeptChildrenCountById(Integer id) {
		return this.deptMapper.queryDeptChildrenCountById(id);
	}

	/*
	 * ******************************************载入缓存开始*****************************
	 * *********************
	 */

	/**
	 * 保存部门信息 获取缓存信息
	 *
	 * @param dept
	 * @return
	 */
	@CachePut(cacheNames = "com.jun.plugin.system.service.impl.DeptServiceImpl", key = "#result.id")
	@Override
	public Dept saveDept(Dept dept) {
		// mq insert会返回一个 添加后的id
		this.deptMapper.insert(dept);
		log.info(dept.getTitle() + "，改信息已存入缓存");
		return dept;
	}

	/**
	 * 修改部门 获取缓存信息
	 *
	 * @param dept
	 * @return
	 */
	@CachePut(cacheNames = "com.jun.plugin.system.service.impl.DeptServiceImpl", key = "#result.id")
	@Override
	public Dept updateDept(Dept dept) {
//			// 先进行更新 把更新完的存放redis
//			this.deptMapper.updateById(dept);
//			log.info("更新前没有查询" + dept.getTitle() + "，已修改缓存当中的信息");
//			dept = this.deptMapper.selectById(dept.getId());
//			log.info("进行查询在存储redis" + dept.getTitle() + "，已修改缓存当中的信息");
		// 为了 解决缓存问题
		// 思路： 当前端某个功能 提交数据 但是数据不全 导致存储到缓存 获取到的数据 缺失
		Dept selectById = this.deptMapper.selectById(dept.getId());
		// 把不为空的属性 覆盖到 selectById 合并成一个 完整的数据集
		BeanUtil.copyProperties(dept, selectById, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
		this.deptMapper.updateById(selectById);
		return selectById;
	}

	/**
	 * 重写 mq 方法 获取缓存信息
	 *
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames = "com.jun.plugin.system.service.impl.DeptServiceImpl", key = "#id")
	@Override
	public Dept getById(Serializable id) {
		log.info(id + "，已从缓存当中获取");
		return super.getById(id);
	}

	/**
	 * 重写 mq 删除方法
	 *
	 * @param id
	 * @return
	 */
	@CacheEvict(cacheNames = "com.jun.plugin.system.service.impl.DeptServiceImpl", key = "#id")
	@Override
	public boolean removeById(Serializable id) {
		log.info(id + ":已将该信息从缓存当中删除");
		return super.removeById(id);
	}

	/*
	 * ******************************************载入缓存结束*****************************
	 * *********************
	 */

	/*
	 * ******************************************清理全部缓存开始***************************
	 * ***********************
	 */

	@Override
	@CacheEvict(value = "com.jun.plugin.system.service.impl.DeptServiceImpl", allEntries = true)
	public void clearAllRedisDB() {
		log.info("指定清理部门Redis缓存槽中的所有对象....");
	}
	/*
	 * ******************************************清理全部缓存结束***************************
	 * ***********************
	 */

}
