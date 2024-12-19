package com.ruoyi.project.system.notice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.CommonService;

import cn.hutool.core.util.StrUtil;

/**
 * 公告信息 服务层处理
 * @author ruoyi
 */
@Service
public class NoticeService extends CommonService {

    /**
     * 分页查询公告信息集合
     * @param request
     * @return 公告信息集合
     */
    public TableDataInfo selectNoticeList(HttpServletRequest request) {
		String notice_title = RequestUtil.getValue(request, "notice_title");
    	String notice_type = RequestUtil.getValue(request, "notice_type");
    	String create_by = RequestUtil.getValue(request, "create_by");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer("select notice_id, notice_title, notice_type, notice_content, status, create_by, create_time, update_by, update_time, remark, " +
    			 "(select b.dict_label from sys_dict_data b where b.dict_type = 'sys_notice_type' and a.notice_type = b.dict_value) notice_type_name, "+
    			 "(select c.dict_label from sys_dict_data c where c.dict_type = 'sys_notice_status' and a.status = c.dict_value) status_name "+
    			 " from sys_notice a where 1 = 1 ");

    	if(StrUtil.isNotBlank(notice_title)) {
    		sql.append(" and a.notice_title like concat('%', ?, '%') ");
    		paramList.add(notice_title);
    	}
    	if(StrUtil.isNotBlank(create_by)) {
    		sql.append(" and a.create_by like concat('%', ?, '%') ");
    		paramList.add(create_by);
    	}
    	if(StrUtil.isNotBlank(notice_type)) {
    		sql.append(" and a.notice_type = ? ");
    		paramList.add(notice_type);
    	}

		//拼接排序语句
		this.addOrderBySql(request, sql, "a.create_time desc");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, true);
    }

    /**
     * 根据用户ID查询公告
     * @param notice_id 公告信息ID
     * @return
     */
    public Map<String, Object> selectNoticeById(String notice_id) {
    	String sql = "select notice_id, notice_title, notice_type, notice_content, status, create_by, create_time, update_by, update_time, remark " +
			 	 	 "  from sys_notice a where notice_id = ?";
        return db.queryForMap(sql, new Object[]{notice_id});
    }

    /**
     * 保存公告
     * @param request
     * @return 结果
     */
    public int saveNotice(HttpServletRequest request) {
    	String notice_id = RequestUtil.getValue(request, "notice_id");
		String notice_title = RequestUtil.getValue(request, "notice_title");
    	String notice_type = RequestUtil.getValue(request, "notice_type");
    	String notice_content = RequestUtil.getValue(request, "notice_content");
    	String status = RequestUtil.getValue(request, "status");
    	String operator_id = ShiroUtils.getLoginName();

    	if(!"".equals(notice_id)) {
    		String sql = "update sys_notice a "+
    				 	 "   set notice_title = ?, notice_type = ?, notice_content = ?, status = ?, " +
    				 	 "		 update_by = ?, update_time = sysdate() " +
    				 	 " where notice_id = ?";
            return db.execute(sql, new Object[]{notice_title, notice_type, notice_content, status, operator_id, notice_id});
    	}

    	String sql = "insert into sys_notice(notice_title, notice_type, notice_content, status, create_by, create_time) " +
    				 "values(?, ?, ?, ?, ?, sysdate())";
        return db.execute(sql, new Object[]{notice_title, notice_type, notice_content, status, operator_id});
    }

    /**
     * 批量删除公告信息
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    public int deleteNoticeByIds(String ids) {
    	List<String> paramList = new ArrayList<String>();
    	String sql = "delete from sys_notice where notice_id in ("+SqlUtil.rebuildInSql(ids, paramList)+")";
		return db.execute(sql, paramList.toArray());
    }
}