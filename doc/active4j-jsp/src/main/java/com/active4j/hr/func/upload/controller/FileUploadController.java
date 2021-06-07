package com.active4j.hr.func.upload.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.common.constant.GlobalConstant;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.core.util.DateUtils;
import com.active4j.hr.core.util.FileUtils;
import com.active4j.hr.core.util.StringUtil;
import com.active4j.hr.func.upload.entity.UploadAttachmentEntity;
import com.active4j.hr.func.upload.service.UploadAttachmentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @title FuncUploadController.java
 * @description 
		文件上传下载
 * @time  2020年2月8日 下午1:12:33
 * @author guyp
 * @version 1.0
 */
@Controller
@RequestMapping("/func/upload")
@Slf4j
public class FileUploadController extends BaseController {
	
	@Autowired
	private UploadAttachmentService uploadAttachmentService;
	
	/**
	 * 
	 * @description
	 *  	查看图片
	 * @params
	 * @return void
	 * @author guyp
	 * @time 2020年2月8日 下午2:09:27
	 */
	@RequestMapping("viewImage") 
	public void viewImage(String n, HttpServletRequest request, HttpServletResponse response) {
		if(StringUtils.isNotEmpty(n)) {
			try {
				QueryWrapper<UploadAttachmentEntity> queryWrapper = new QueryWrapper<UploadAttachmentEntity>();
				queryWrapper.eq("SAVE_NAME", n);
				//查询图片实体
				UploadAttachmentEntity attach = uploadAttachmentService.getOne(queryWrapper);
				if(null != attach) {
					byte[] bs = attach.getContent();
					//将数据写入前端
					if(null != bs) {
						OutputStream out = response.getOutputStream();
						out.write(bs);
						out.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				log.error("查看图片报错，错误信息：{}", e.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * @description
	 *  	按保存方式上传图片
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2020年2月8日 下午2:02:26
	 */
	@RequestMapping("uploadImages")
	@ResponseBody
	public AjaxJson uploadImages(String db, MultipartHttpServletRequest request, HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		try{
			if(StringUtils.isEmpty(db)) {
				j.setSuccess(false);
				j.setMsg("保存图片方式错误，请联系管理员");
				return j;
			}
			
			Map<String, MultipartFile> fileMap = request.getFileMap();
			
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				
				MultipartFile mf = entity.getValue();// 获取上传文件对象
				String fileName = mf.getOriginalFilename();// 获取文件名
				String extend = FileUtils.getExtend(fileName);// 获取文件扩展名
				
				if(StringUtils.equals(db, GlobalConstant.FILE_UPLOADER_SAVE_FILE)) {
					//本地保存文件系统
					String strYYYYMMDD = DateUtils.getYYYYMMDDStr();
					
					String realPath = request.getSession().getServletContext().getRealPath("/") + "/upload/" + strYYYYMMDD + "/";// 文件的硬盘真实路径
					String path = "upload/" + strYYYYMMDD + "/";
					File file = new File(realPath);
					if (!file.exists()) {
						file.mkdirs();// 创建根目录
					}
					
					String noextfilename = DateUtils.getDataString(DateUtils.SDF_YYYYMMDDHHMMSS) + StringUtil.random(8);//自定义文件名称
					String myfilename= noextfilename+"."+extend;//自定义文件名称
					
					String savePath = realPath + myfilename;// 文件保存全路径
					path = path + myfilename;
					File savefile = new File(savePath);
					
					// 文件拷贝到指定硬盘目录
					FileCopyUtils.copy(mf.getBytes(), savefile);
					
					//页面返回值
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("filePath", path);
					map.put("saveName", noextfilename);
					j.setAttributes(map);
					
				}else if(StringUtils.equals(db, GlobalConstant.FILE_UPLOADER_SAVE_DB)) {
					//保存进数据库
					UploadAttachmentEntity attach = new UploadAttachmentEntity();
					attach.setContent(mf.getBytes());
					attach.setName(fileName);
					attach.setExtendName(extend);
					
					String noextfilename = DateUtils.getDataString(DateUtils.SDF_YYYYMMDDHHMMSS) + StringUtil.random(10);//自定义文件名称
					String myfilename= noextfilename+"."+extend;//自定义文件名称
					
					//保存实体赋值
					attach.setSaveName(myfilename);
					attach.setPath(GlobalConstant.CONFIG_FILE_SAVE_DB_URL + myfilename);
					attach.setUploaderDate(DateUtils.getDate());
					attach.setUploaderName(ShiroUtils.getSessionUserName());
					attach.setType(GlobalConstant.FILE_UPLOADER_TYPE_IMAGE);
					uploadAttachmentService.save(attach);
					
					//页面返回值
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("filePath", GlobalConstant.CONFIG_FILE_SAVE_DB_URL + myfilename);
					map.put("saveName", noextfilename);
					j.setAttributes(map);
				}
				
				//暂时只支持单个图片上传
				break;
			}
		}catch(Exception e){
			log.error("图片上传报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("图片上传失败");
		}
		
		return j;
	}
}
