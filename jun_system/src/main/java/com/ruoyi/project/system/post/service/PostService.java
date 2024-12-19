package com.ruoyi.project.system.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.CommonService;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 岗位信息 服务层处理
 * @author ruoyi
 */
@Service
public class PostService extends CommonService {

    /**
     * 查询岗位信息（分页查询）
	 * @param request HttpServletRequest对象
	 * @param pagination 是否分页
     * @return
     */
    public TableDataInfo selectPostList(HttpServletRequest request, boolean pagination) {
		String post_code = RequestUtil.getValue(request, "post_code");
    	String post_name = RequestUtil.getValue(request, "post_name");
    	String status = RequestUtil.getValue(request, "status");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer("select post_id, post_code, post_name, post_sort, status, create_by, create_time, remark, " +
    			 "(select b.dict_label from sys_dict_data b where b.dict_type = 'sys_normal_disable' and a.status = b.dict_value) status_name from sys_post a where 1 = 1 ");

    	if(StrUtil.isNotBlank(post_code)) {
    		sql.append(" and a.post_code like concat('%', ?, '%') ");
    		paramList.add(post_code);
    	}
    	if(StrUtil.isNotBlank(post_name)) {
    		sql.append(" and a.post_name like concat('%', ?, '%') ");
    		paramList.add(post_name);
    	}
    	if(StrUtil.isNotBlank(status)) {
    		sql.append(" and a.status = ? ");
    		paramList.add(status);
    	}

		//拼接排序语句
		this.addOrderBySql(request, sql, "a.post_sort");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, pagination);
    }

    /**
     * 查询所有岗位
     * @return
     */
    public List<Map<String, Object>> selectPostAll() {
    	String sql = "select post_id, post_code, post_name, post_sort, status, create_by, create_time, remark " +
    				 "  from sys_post where status = '0' order by post_sort";
    	return db.queryForList(sql, null);
    }

    /**
     * 根据岗位ID查询岗位
     * @param postId 岗位编号
     * @return 参数配置管理
     */
    public Map<String, Object> selectPostById(String postId) {
    	String sql = "select post_id, post_code, post_name, post_sort, status, create_by, create_time, remark " +
			 	 	 "  from sys_post a where post_id = ?";
        return db.queryForMap(sql, new Object[]{postId});
    }

    /**
     * 保存岗位
     * @param request
     * @return 结果
     */
    public int savePost(HttpServletRequest request) {
    	String post_id = RequestUtil.getValue(request, "post_id");
		String post_code = RequestUtil.getValue(request, "post_code");
    	String post_name = RequestUtil.getValue(request, "post_name");
    	String post_sort = RequestUtil.getValue(request, "post_sort");
    	String status = RequestUtil.getValue(request, "status");
    	String remark = RequestUtil.getValue(request, "remark");
    	String operator_id = ShiroUtils.getLoginName();

    	if(!"".equals(post_id)) {
    		String sql = "update sys_post a "+
    				 	 "   set post_code = ?, post_name = ?, post_sort = ?, status = ?, remark = ?," +
    				 	 "		 update_by = ?, update_time = sysdate() " +
    				 	 " where post_id = ?";
            return db.execute(sql, new Object[]{post_code, post_name, post_sort, status, remark, operator_id, post_id});
    	}

    	String sql = "insert into sys_post(post_code, post_name, post_sort, status, create_by, create_time, remark) " +
    				 "values(?, ?, ?, ?, ?, sysdate(), ?)";
        return db.execute(sql, new Object[]{post_code, post_name, post_sort, status, operator_id, remark});
    }

    /**
     * 批量删除岗位信息
     * @param ids 需要删除的数据ID
     */
    public int deletePostByIds(String ids) {
    	String[] postIds = Convert.toStrArray(ids);
        for (String postId : postIds) {
        	Map<String, Object> post = selectPostById(postId);
            if (countUserPostById(postId) > 0) {
                throw new BusinessException(String.format("%1$s已分配,不能删除", MapUtil.getStr(post, "post_name")));
            }
        }

    	List<String> paramList = new ArrayList<String>();
    	String sql = "delete from sys_post where post_id in ("+SqlUtil.rebuildInSql(ids, paramList)+")";
		return db.execute(sql, paramList.toArray());
    }

    /**
     * 通过岗位ID查询岗位使用数量
     * @param postId 岗位ID
     * @return 结果
     */
    public int countUserPostById(String postId) {
    	String sql = "select count(1) from sys_user_post where post_id = ?";
        return db.queryForInt(sql, new Object[]{postId});
    }

    /**
     * 校验岗位名称是否唯一
     * @param request
     * @return 结果
     */
    public int checkPostNameUnique(HttpServletRequest request) {
    	String post_id = RequestUtil.getValue(request, "post_id");
		String post_name = RequestUtil.getValue(request, "post_name");

		List<String> paramList = new ArrayList<String>();
		String sql = "select count(1) from sys_post a where a.post_name = ? ";
		paramList.add(post_name);

		if(StrUtil.isNotBlank(post_id)) {
    		sql += " and a.post_id <> ? ";
    		paramList.add(post_id);
    	}
		return db.queryForInt(sql, paramList.toArray());
    }

    /**
     * 校验岗位编码是否唯一
     * @param request
     * @return 结果
     */
    public int checkPostCodeUnique(HttpServletRequest request) {
    	String post_id = RequestUtil.getValue(request, "post_id");
		String post_code = RequestUtil.getValue(request, "post_code");

		List<String> paramList = new ArrayList<String>();
		String sql = "select count(1) from sys_post a where a.post_code = ? ";
		paramList.add(post_code);

		if(StrUtil.isNotBlank(post_id)) {
    		sql += " and a.post_id <> ? ";
    		paramList.add(post_id);
    	}
		return db.queryForInt(sql, paramList.toArray());
    }
}