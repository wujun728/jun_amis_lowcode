package com.zhonghe.active4j.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.annotation.Log;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.core.model.LogType;
import com.zhonghe.active4j.core.query.QueryUtils;
import com.zhonghe.active4j.core.util.MyBeanUtils;
import com.zhonghe.active4j.core.util.ResponseUtil;
import com.zhonghe.active4j.system.entity.SysDicEntity;
import com.zhonghe.active4j.system.entity.SysDicValueEntity;
import com.zhonghe.active4j.system.model.TreeSysDicModel;
import com.zhonghe.active4j.system.service.SysDicService;
import com.zhonghe.active4j.system.service.SysDicValueService;
import com.zhonghe.active4j.system.service.SystemService;
import com.zhonghe.active4j.system.wrapper.DicWrapper;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @title SysDicController.java
 * @description 
		  数据字典管理
 * @time  2019年12月3日 上午10:12:31
 * @author chenxl
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/dic")
@Slf4j
public class SysDicController extends BaseController {

	@Autowired
	private SysDicService sysDicService;
	
	@Autowired
	private SysDicValueService sysDicValueService;
	
	@Autowired
	private SystemService systemService;
	
	/**
	 * 跳转到列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys/dic/list")
	public String list(Model model) {
		return "sys/dic/dic_list.html";
	}
	
	
	/**
	 *    获取数据，树形结构显示
	 * @param sysDicEntity
	 * @param request
	 * @return
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(SysDicEntity sysDicEntity, HttpServletRequest request, HttpServletResponse response) {
		
		//拼接查询条件
		QueryWrapper<SysDicEntity> queryWrapper = QueryUtils.installQueryWrapper(sysDicEntity, request.getParameterMap());
		
		//执行查询
		List<SysDicEntity> lstResult = sysDicService.list(queryWrapper);
		
		List<TreeSysDicModel> lstModes = getDicTree(lstResult);

		ResponseUtil.write(response, new DicWrapper(lstModes).wrap());
		
	}
	
	/**
	 * 跳转到页面
	 * @param sysDicEntity
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(String id, String parentId, Model model) {
		
		if(StringUtils.isEmpty(id)) {
			return "sys/dic/dic.html";
		}else {
			if(StringUtils.equals("-1", parentId)) {
				//字典的实体
				SysDicEntity dic = sysDicService.getById(id);
				
				model.addAttribute("dic", dic);
				
				return "sys/dic/dic.html";
			}else {
				//值的实体
				SysDicValueEntity dicValue = sysDicValueService.getById(id);
				model.addAttribute("dicValue", dicValue);
				
				SysDicEntity dic = sysDicService.getById(dicValue.getParentId());
				model.addAttribute("name", dic.getName());
				model.addAttribute("parentId", dic.getId());
			
				return "sys/dic/dicval.html";
			}
			
		}
		
	}
	
	/**
	 * 跳转到新增数据字典值页面
	 * @param sysDicValueEntity
	 * @param model
	 * @return
	 */
	@RequestMapping("/addval")
	public String addVal(SysDicValueEntity sysDicValueEntity, String parentId, Model model) {
		
		SysDicEntity dic = sysDicService.getById(parentId);
		model.addAttribute("name", dic.getName());
		model.addAttribute("parentId", parentId);
		
		return "sys/dic/dicval.html";
	}
	
	
	/**
	 * 保存方法
	 * @param sysDicEntity
	 * @return
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:dic:save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存数据字典信息", memo = "新增或编辑保存了数据字典信息")
	public AjaxJson save(@Validated SysDicEntity sysDicEntity)  {
		AjaxJson j = new AjaxJson();
		
		try{
			if(StringUtils.isEmpty(sysDicEntity.getId())) {
				//新增保存
				sysDicService.save(sysDicEntity);
				//刷新一下数据字典
				systemService.initDictionary();
			}else {
				//编辑保存
				SysDicEntity tmp = sysDicService.getById(sysDicEntity.getId());
				MyBeanUtils.copyBeanNotNull2Bean(sysDicEntity, tmp);
				sysDicService.saveOrUpdate(tmp);
				//刷新一下数据字典
				systemService.initDictionary();
			}
		}catch(Exception e) {
			log.error("保存数据字典信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 保存字典值
	 * @param sysDicValueEntity
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/saveval")
	@RequiresPermissions("sys:dic:saveval")
	@ResponseBody
	@Log(type = LogType.save, name = "保存字典值", memo = "新增或编辑保存了字典值")
	public AjaxJson saveVal(@Validated SysDicValueEntity sysDicValueEntity, String parentId)  {
		AjaxJson j = new AjaxJson();
		
		try{
			//后端校验
			if(StringUtils.isEmpty(parentId)) {
				j.setSuccess(false);
				j.setMsg("请选择字典");
				return j;
			}
			
			sysDicValueEntity.setParentId(parentId);
			
			if(StringUtils.isEmpty(sysDicValueEntity.getId())) {
				//新增保存
				sysDicValueService.save(sysDicValueEntity);
				//刷新一下数据字典
				systemService.initDictionary();
			}else {
				//编辑保存
				SysDicValueEntity tmp = sysDicValueService.getById(sysDicValueEntity.getId());
				MyBeanUtils.copyBeanNotNull2Bean(sysDicValueEntity, tmp);
				sysDicValueService.saveOrUpdate(tmp);
				//刷新一下数据字典
				systemService.initDictionary();
			}
		}catch(Exception e) {
			log.error("保存字典值报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		return j;
	}
	
	
	/**
	 *  删除操作
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:dic:delete")
	@ResponseBody
	@Log(type = LogType.del, name = "删除数据字典信息", memo = "删除了数据字典信息")
	public AjaxJson delete(String id, String parentId) {
		AjaxJson j = new AjaxJson();
		try {
			
			if(StringUtils.equals("-1", parentId)) {
				//判断是否存在子值
				QueryWrapper<SysDicValueEntity> queryWrapper = new QueryWrapper<SysDicValueEntity>();
				queryWrapper.eq("PARENT_ID", id);
				List<SysDicValueEntity> lstDicValues = sysDicValueService.list(queryWrapper);
				if(lstDicValues.size() > 0) {
					j.setSuccess(false);
					j.setMsg("该字典下存在子值，请先删除字典值");
					return j;
				}
				
				//字典的删除
				sysDicService.removeById(id);
			}else {
				//值的删除
				sysDicValueService.removeById(id);
			}
			
			//刷新一下数据字典
			systemService.initDictionary();
		}catch(Exception e) {
			log.error("删除数据字典信息出错，错误信息:" + e.getMessage() );
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		
		return j;
	}
	
	
	
	/**
	 * 获取数据字典的树形结构显示
	 * @param lstDics
	 * @return
	 */
	public List<TreeSysDicModel> getDicTree(List<SysDicEntity> lstDics) {
		
		List<TreeSysDicModel> lstTree = new ArrayList<TreeSysDicModel>();
		
		if(null != lstDics && lstDics.size() > 0) {
			for(SysDicEntity dic : lstDics) {
				
				//父表的数据
				TreeSysDicModel treeModel = new TreeSysDicModel();
				treeModel.setId(dic.getId());
				treeModel.setParentId("-1");
				treeModel.setMemo(dic.getMemo());
				treeModel.setName(dic.getName());
				treeModel.setValue(dic.getCode());
				lstTree.add(treeModel);
				
				//子表的数据
				QueryWrapper<SysDicValueEntity> queryWrapper = new QueryWrapper<SysDicValueEntity>();
				queryWrapper.eq("PARENT_ID", dic.getId());
				List<SysDicValueEntity> lstValues = sysDicValueService.list(queryWrapper);
				if(null != lstValues && lstValues.size() > 0) {
					for(SysDicValueEntity val : lstValues) {
						treeModel = new TreeSysDicModel();
						treeModel.setId(val.getId());
						treeModel.setParentId(dic.getId());
						treeModel.setMemo("");
						treeModel.setName(val.getLabel());
						treeModel.setValue(val.getValue());
						lstTree.add(treeModel);
					}
				}
			}
		}
		
		return lstTree;
	}
}
