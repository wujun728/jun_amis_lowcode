package com.ruoyi.project.monitor.logininfor.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.CommonService;
import com.ruoyi.project.monitor.logininfor.domain.Logininfor;

import cn.hutool.core.util.StrUtil;

/**
 * 登录日志服务层处理
 * @author ruoyi
 */
@Service
public class LogininforService extends CommonService {

    /**
     * 查询登录日志（分页查询）
     * @param request
     * @return
     */
    public TableDataInfo selectLogininforList(HttpServletRequest request, boolean pagination) {
		String ipaddr = RequestUtil.getValue(request, "ipaddr");
    	String login_name = RequestUtil.getValue(request, "login_name");
    	String status = RequestUtil.getValue(request, "status");
    	String start_time = RequestUtil.getValue(request, "start_time");
    	String end_time = RequestUtil.getValue(request, "end_time");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer("select info_id,login_name,ipaddr,login_location,browser,os,status,msg,login_time, " +
    			 "(select b.dict_label from sys_dict_data b where b.dict_type = 'sys_normal_disable' and a.status = b.dict_value) status_name from sys_logininfor a where 1 = 1 ");

    	if(StrUtil.isNotBlank(ipaddr)) {
    		sql.append(" and a.ipaddr like concat('%', ?, '%') ");
    		paramList.add(ipaddr);
    	}
    	if(StrUtil.isNotBlank(login_name)) {
    		sql.append(" and a.login_name like concat('%', ?, '%') ");
    		paramList.add(login_name);
    	}
    	if(StrUtil.isNotBlank(status)) {
    		sql.append(" and a.status = ? ");
    		paramList.add(status);
    	}
    	if(StrUtil.isNotBlank(start_time)) {
    		sql.append(" and date_format(login_time,'%y%m%d') >= date_format(?,'%y%m%d') ");
    		paramList.add(start_time);
    	}
    	if(StrUtil.isNotBlank(end_time)) {
    		sql.append(" and date_format(login_time,'%y%m%d') <= date_format(?,'%y%m%d') ");
    		paramList.add(end_time);
    	}

		//拼接排序语句
		this.addOrderBySql(request, sql, "a.login_time desc");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, pagination);
    }

    /**
     * 批量删除登录日志
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    public int deleteLogininforByIds(String ids) {
    	List<String> paramList = new ArrayList<String>();
    	String sql = "delete from sys_logininfor where info_id in ("+SqlUtil.rebuildInSql(ids, paramList)+")";
		return db.execute(sql, paramList.toArray());
    }

    /**
     * 清空系统登录日志
     */
    public void cleanLogininfor() {
        String sql = "truncate table sys_logininfor";
        db.execute(sql, null);
    }

    /**
     * 插入登录日志
     * @param logininfor
     */
    public void insertLogininfor(Logininfor logininfor) {
    	String sql = "insert into sys_logininfor (login_name, status, ipaddr, login_location, browser, os, msg, login_time) " +
    				 "values (?, ?, ?, ?, ?, ?, ?, sysdate())";
        db.execute(sql, new Object[]{logininfor.getLoginName(), logininfor.getStatus(), logininfor.getIpaddr(),
        		logininfor.getLoginLocation(), logininfor.getBrowser(), logininfor.getOs(), logininfor.getMsg()});
    }
}