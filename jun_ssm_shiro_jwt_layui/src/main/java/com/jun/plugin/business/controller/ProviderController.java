package com.jun.plugin.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.business.domain.Provider;
import com.jun.plugin.business.service.ProviderService;
import com.jun.plugin.business.vo.ProviderVo;
import com.jun.plugin.system.common.Constant;
import com.jun.plugin.system.common.ResultObj;

import java.util.Arrays;

/**
 * ClassName: ProviderController Description: layui date: 2020/4/16 15:52
 *
 * 
 * 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("api/provider")
public class ProviderController {

	@Autowired
	private ProviderService providerService;

	/**
	 * 供应商信息数据
	 *
	 * @param providerVo
	 * @return
	 */
	@GetMapping("loadAllProvider")
	public Object loadAllProvider(ProviderVo providerVo) {
		System.out.println("进来啦");
		return this.providerService.queryAllProvider(providerVo);
	}

	/**
	 * 查询所有供应商 不分页
	 * 
	 * @return
	 */
	@GetMapping("getAllAvailableProvider")
	public Object getAllAvailableProvider() {
		return this.providerService.getAllAvailableProvider();
	}

	/**
	 * 添加供应商信息
	 *
	 * @param provider
	 * @return
	 */
	@PostMapping("addProvider")
	public Object addProvider(Provider provider) {
		try {
			provider.setAvailable(Constant.AVAILABLE_TRUE);
			this.providerService.saveProvider(provider);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改供应商信息
	 *
	 * @param provider
	 * @return
	 */
	@PostMapping("updateProvider")
	public Object updateProvider(Provider provider) {
		try {
			this.providerService.updateProvider(provider);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 删除供应商信息
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("deleteProvider")
	public Object deleteProvider(Integer id) {
		try {
			this.providerService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 批量删除供应商信息
	 *
	 * @param ids
	 * @return
	 */
	@PostMapping("batchDeleteProvider")
	public Object batchDeleteProvider(Integer[] ids) {
		try {
			this.providerService.removeByIds(Arrays.asList(ids));
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

}