package com.ruoyi.project.monitor.operlog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.CommonService;
import com.ruoyi.project.monitor.operlog.domain.OperLog;

import cn.hutool.core.util.StrUtil;

/**
 * 操作日志服务层处理
 * @author ruoyi
 */
@Service
public class OperLogService extends CommonService {

    /**
     * 查询操作日志（分页查询）
     * @param request HttpServletRequest对象
	 * @param pagination 是否分页
     * @return
     */
    public TableDataInfo selectOperLogList(HttpServletRequest request, boolean pagination) {
		String title = RequestUtil.getValue(request, "title");
		String business_type = RequestUtil.getValue(request, "business_type");
    	String oper_name = RequestUtil.getValue(request, "oper_name");
    	String status = RequestUtil.getValue(request, "status");
    	String start_time = RequestUtil.getValue(request, "start_time");
    	String end_time = RequestUtil.getValue(request, "end_time");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer("select oper_id, title, business_type, method, request_method, operator_type, oper_name, "+
    			 " dept_name, oper_url, oper_ip, oper_location, oper_param, json_result, status, error_msg, oper_time, " +
    			 " (select b.dict_label from sys_dict_data b where b.dict_type = 'sys_oper_type' and a.business_type = b.dict_value) business_type_name, "+
    			 " (select c.dict_label from sys_dict_data c where c.dict_type = 'sys_common_status' and a.status = c.dict_value) status_name "+
    			 "from sys_oper_log a where 1 = 1 ");

    	if(StrUtil.isNotBlank(title)) {
    		sql.append(" and a.title like concat('%', ?, '%') ");
    		paramList.add(title);
    	}
    	if(StrUtil.isNotBlank(business_type)) {
    		sql.append(" and a.business_type = ? ");
    		paramList.add(business_type);
    	}
    	if(StrUtil.isNotBlank(oper_name)) {
    		sql.append(" and a.oper_name like concat('%', ?, '%') ");
    		paramList.add(oper_name);
    	}
    	if(StrUtil.isNotBlank(status)) {
    		sql.append(" and a.status = ? ");
    		paramList.add(status);
    	}
    	if(StrUtil.isNotBlank(start_time)) {
    		sql.append(" and date_format(oper_time,'%y%m%d') >= date_format(?,'%y%m%d') ");
    		paramList.add(start_time);
    	}
    	if(StrUtil.isNotBlank(end_time)) {
    		sql.append(" and date_format(oper_time,'%y%m%d') <= date_format(?,'%y%m%d') ");
    		paramList.add(end_time);
    	}

		//拼接排序语句
		this.addOrderBySql(request, sql, "a.oper_time desc");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, pagination);
    }

    /**
     * 查询单个操作日志信息
     * @param oper_id 操作日志ID
     * @return
     */
    public Map<String, Object> selectOperLogById(String oper_id) {
    	String sql = "select oper_id, title, business_type, method, request_method, operator_type, oper_name, dept_name, oper_url, oper_ip, oper_location, oper_param, json_result, status, error_msg, oper_time, " +
   			 " (select b.dict_label from sys_dict_data b where b.dict_type = 'sys_oper_type' and a.business_type = b.dict_value) business_type_name, "+
   			 " (select c.dict_label from sys_dict_data c where c.dict_type = 'sys_common_status' and a.status = c.dict_value) status_name "+
			 "  from sys_oper_log a where oper_id = ?";
        return db.queryForMap(sql, new Object[]{oper_id});
    }

    /**
     * 批量删除操作日志
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    public int deleteOperLogByIds(String ids) {
    	List<String> paramList = new ArrayList<String>();
    	String sql = "delete from sys_oper_log where oper_id in ("+SqlUtil.rebuildInSql(ids, paramList)+")";
		return db.execute(sql, paramList.toArray());
    }

    /**
     * 清空系统操作日志
     */
    public void cleanOperLog() {
        String sql = "truncate table sys_oper_log";
        db.execute(sql, null);
    }

    /**
     * 插入操作日志
     * @param logininfor
     */
    public void insertOperlog(OperLog operLog) {
    	String sql = "insert into sys_oper_log(title, business_type, method, request_method, operator_type, oper_name, dept_name, oper_url, oper_ip, oper_location, " +
    				 " oper_param, json_result, status, error_msg, oper_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate())";
        db.execute(sql, new Object[]{operLog.getTitle(), operLog.getBusinessType(), operLog.getMethod(), operLog.getRequestMethod(),
        		operLog.getOperatorType(), operLog.getOperName(), operLog.getDeptName(), operLog.getOperUrl(), operLog.getOperIp(),
        		operLog.getOperLocation(), operLog.getOperParam(), operLog.getJsonResult(), operLog.getStatus(), operLog.getErrorMsg()});
    }
}