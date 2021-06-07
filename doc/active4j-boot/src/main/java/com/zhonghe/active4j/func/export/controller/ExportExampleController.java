package com.zhonghe.active4j.func.export.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.core.model.PageInfo;
import com.zhonghe.active4j.core.query.QueryUtils;
import com.zhonghe.active4j.core.util.ResponseUtil;
import com.zhonghe.active4j.func.export.entity.ExportExampleEntity;
import com.zhonghe.active4j.func.export.service.ExportExampleService;
import com.zhonghe.active4j.func.export.wrapper.ExportExampleWrapper;
import com.zhonghe.active4j.func.upload.util.FileUploadUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @title ExportExampleController.java
 * @description 
		导入导出管理
 * @time  2019年12月17日 上午10:14:33
 * @author guyp
 * @version 1.0
 */
@Controller
@RequestMapping("/func/export")
@Slf4j
public class ExportExampleController extends BaseController {
	
	@Autowired
	private ExportExampleService exportExampleService;

	private static final String prefix_page = "func/export/";
	
	/**
	 * 
	 * @description
	 *  	跳转导入导出页面
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2019年12月17日 上午10:15:18
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		return prefix_page + "index_list.html";
	}
	
	/**
	 * 
	 * @description
	 *  	获取表格数据
	 * @params
	 * @return void
	 * @author guyp
	 * @time 2019年12月17日 上午10:52:46
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(ExportExampleEntity exportExampleEntity, PageInfo<ExportExampleEntity> page, HttpServletRequest request, HttpServletResponse response) {
		//拼接查询条件
		QueryWrapper<ExportExampleEntity> queryWrapper = QueryUtils.installQueryWrapper(exportExampleEntity, request.getParameterMap());
		//执行查询
		IPage<ExportExampleEntity> lstResult = exportExampleService.page(page.getPageEntity(), queryWrapper);
		//结果处理，直接写到客户端
		ResponseUtil.write(response, new ExportExampleWrapper(lstResult).wrap());
	}
	
	/**
	 * 
	 * @description
	 *  	导入
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月17日 下午4:20:37
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public AjaxJson upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			log.info("进入导入接口");
			Map<String, Object> attributesMap = new HashMap<String, Object>();
			Map<String, MultipartFile> fileMap = request.getFileMap();
			
			for(Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				// 获取上传文件对象
				MultipartFile mf = entity.getValue();
				//获取文件后缀名
				String extName = FileUploadUtils.getExtension(mf);
				//获得文件输入流
				InputStream inputStream = mf.getInputStream();
				/*根据excel后缀判断
				xlsx可以有1048576行、16384列
				xls只有65536行、256列*/
				if(StringUtils.equals("xlsx", extName)) {
					//保存xlsx的内容
					exportExampleService.saveXlsx(inputStream);
				}else if(StringUtils.equals("xls", extName)) {
					//保存xls的内容
					exportExampleService.saveXls(inputStream);
				}else {
					j.setSuccess(false);
					j.setMsg("您上传的文件包含不支持的格式，请重新上传");
					return j;
				}
				
				attributesMap.put("src", mf.getOriginalFilename());
				j.setAttributes(attributesMap);
				//关闭流
				inputStream.close();
				//单次上传，不需要再次进入循环
				break;
			}
		}catch(Exception e) {
			log.error("导入报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	导出xls文件
	 * @params
	 * @return void
	 * @author guyp
	 * @time 2019年12月18日 上午10:03:09
	 */
	@RequestMapping("/xls")
	public void xls(String name, HttpServletRequest request, HttpServletResponse response) {
		try {
			//导出xls文件
			exportExampleService.exportXls(request, response, name);
		} catch (Exception e) {
			log.error("导出xls报错，错误信息：{}", e.getMessage());
			e.printStackTrace();
		}  
	}
	
	/**
	 * 
	 * @description
	 *  	导出xlsx文件
	 * @params
	 * @return void
	 * @author guyp
	 * @time 2019年12月18日 上午11:27:40
	 */
	@RequestMapping("/xlsx")
	public void xlsx(String name, HttpServletRequest request, HttpServletResponse response) {
		try {
			//导出xlsx文件
			exportExampleService.exportXlsx(request, response, name);
		} catch (Exception e) {
			log.error("导出xls报错，错误信息：{}", e.getMessage());
			e.printStackTrace();
		}  
	}
	
	/**
	 * 
	 * @description
	 *  	导出csv文件
	 * @params
	 * @return void
	 * @author guyp
	 * @time 2019年12月18日 上午10:52:30
	 */
	@RequestMapping("/csv")
	public void csv(String name, HttpServletRequest request, HttpServletResponse response) {
		try {
			//导出csv
			exportExampleService.exportCsv(request, response, name);
		} catch (Exception e) {
			log.error("导出csv报错，错误信息：{}", e.getMessage());
			e.printStackTrace();
		}  
	}
}
