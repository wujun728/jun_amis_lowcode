package com.ruoyi.project.system.param.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.StrUtils;
import com.ruoyi.project.system.param.domain.Config;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.CommonService;

import cn.hutool.core.util.StrUtil;

/**
 * 参数配置 服务层实现
 * @author daixf
 * @date 2019-08-24
 */
@Service
public class ParamService extends CommonService {

    /**
     * 获取cache name
     * @return 缓存名
     */
    private String getCacheName() {
        return Constants.SYS_CONFIG_CACHE;
    }

    /**
     * 设置cache key
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return Constants.SYS_CONFIG_KEY + configKey;
    }

    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init() {
        String sql = "select config_id, config_name, config_key, config_value, config_type, create_by, create_time, " +
                " update_by, update_time, remark from sys_config ";

        List<Config> configsList = db.queryForList(sql, null, Config.class);
        for (Config config : configsList) {
            CacheUtils.put(getCacheName(), getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
    }

    /**
     * 清空缓存数据
     */
    public void clearCache() {
        CacheUtils.removeAll(getCacheName());
    }

    /**
     * 查询参数配置（分页查询）
	 * @param request HttpServletRequest对象
	 * @param pagination 是否分页
     * @return 参数配置管理
     */
    public TableDataInfo selectParamList(HttpServletRequest request, boolean pagination) {
		String config_name = RequestUtil.getValue(request, "config_name");
    	String config_key = RequestUtil.getValue(request, "config_key");
    	String config_type = RequestUtil.getValue(request, "config_type");
    	String start_time = RequestUtil.getValue(request, "start_time");
    	String end_time = RequestUtil.getValue(request, "end_time");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer("select config_id, config_name, config_key, config_value, config_type, " +
				 " create_by, create_time, update_by, update_time, remark, " +
				 " (select b.dict_label from sys_dict_data b where b.dict_type = 'sys_yes_no' and a.config_type = b.dict_value) config_type_name from sys_config a where 1 = 1 ");

    	if(StrUtil.isNotBlank(config_name)) {
    		sql.append(" and a.config_name like concat('%', ?, '%') ");
    		paramList.add(config_name);
    	}
    	if(StrUtil.isNotBlank(config_key)) {
    		sql.append(" and a.config_key like concat('%', ?, '%') ");
    		paramList.add(config_key);
    	}
    	if(StrUtil.isNotBlank(config_type)) {
    		sql.append(" and a.config_type = ? ");
    		paramList.add(config_type);
    	}
    	if(StrUtil.isNotBlank(start_time)) {
    		sql.append(" and date_format(a.create_time,'%y%m%d') >= date_format(?,'%y%m%d') ");
    		paramList.add(start_time);
    	}
    	if(StrUtil.isNotBlank(end_time)) {
    		sql.append(" and date_format(a.create_time,'%y%m%d') <= date_format(?,'%y%m%d') ");
    		paramList.add(end_time);
    	}

		//拼接排序语句
		this.addOrderBySql(request, sql, "a.create_time desc");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, pagination);
    }

    /**
     * 查询单个参数配置信息
     * @param config_id 参数配置管理ID
     * @return 参数配置管理
     */
    public Map<String, Object> selectParamById(String config_id) {
    	String sql = "select config_id, config_name, config_key, config_value, config_type, " +
			 	 	 "		 create_by, create_time, update_by, update_time, remark " +
			 	 	 "  from sys_config a where config_id = ?";
        return db.queryForMap(sql, new Object[]{config_id});
    }

    /**
     * 保存参数配置
     * @param request
     * @return 结果
     */
    public int saveParam(HttpServletRequest request) {
    	String config_id = RequestUtil.getValue(request, "config_id");
		String config_name = RequestUtil.getValue(request, "config_name");
    	String config_key = RequestUtil.getValue(request, "config_key");
    	String config_value = RequestUtil.getValue(request, "config_value");
    	String config_type = RequestUtil.getValue(request, "config_type");
    	String remark = RequestUtil.getValue(request, "remark");
    	String operator_id = ShiroUtils.getLoginName();

    	int result = 0;
    	if(!"".equals(config_id)) {
    		String sql = "update sys_config a "+
    				 	 "   set config_name = ?, config_key = ?, config_value = ?, config_type = ?, remark = ?," +
    				 	 "		 update_by = ?, update_time = sysdate() " +
    				 	 " where config_id = ?";
            result = db.execute(sql, new Object[]{config_name, config_key, config_value, config_type, remark, operator_id, config_id});
    	}
    	else {
            String sql = "insert into sys_config(config_name, config_key, config_value, config_type, create_by, create_time, remark) " +
                         "values(?, ?, ?, ?, ?, sysdate(), ?)";
            result = db.execute(sql, new Object[]{config_name, config_key, config_value, config_type, operator_id, remark});
        }

        if (result > 0) {
            CacheUtils.put(getCacheName(), getCacheKey(config_key), config_value);
        }
        return result;
    }

    /**
     * 校验参数键名是否唯一
     * @param request
     * @return
     */
    public int checkConfigKeyUnique(HttpServletRequest request) {
    	String config_id = RequestUtil.getValue(request, "config_id");
		String config_key = RequestUtil.getValue(request, "config_key");

		List<String> paramList = new ArrayList<String>();
		String sql = "select count(1) from sys_config a where a.config_key = ? ";
		paramList.add(config_key);

		if(StrUtil.isNotBlank(config_id)) {
    		sql += " and a.config_id <> ? ";
    		paramList.add(config_id);
    	}
		return db.queryForInt(sql, paramList.toArray());
    }

    /**
     * 删除参数配置管理信息
     * @param config_ids 参数IDs
     * @return 结果
     */
    public int deleteParamById(String config_ids) {
        String[] configIds = Convert.toStrArray(config_ids);

        //根据参数个数创建相应数量的占位符
        String placeholders = SqlUtil.rebuildInSql(configIds.length);

        //查询出待删除的参数
        String sql = "select config_id, config_name, config_key, config_value, config_type, create_by, create_time, " +
                " update_by, update_time, remark from sys_config where config_id in ("+placeholders+")";
        List<Config> configsList = db.queryForList(sql, configIds, Config.class);
        for (Config config : configsList) {
            if (StrUtil.equals(UserConstants.YES, config.getConfigType())) {
                throw new BusinessException(String.format("内置参数【%1$s】不能删除 ", config.getConfigKey()));
            }
        }

		//删除参数配置
		sql = "delete from sys_config where config_id in ("+placeholders+")";
		int res = db.execute(sql, configIds);
		if(res == 1) {
            for (Config config : configsList) {
                CacheUtils.remove(getCacheName(), getCacheKey(config.getConfigKey()));
            }
        }

		return res;
    }

    /**
     * 根据参数键名查询参数键值
     * @param config_key 参数键名
     * @return 参数键值
     */
    public String selectConfigByKey(String config_key) {
        String configValue = Convert.toStr(CacheUtils.get(getCacheName(), getCacheKey(config_key)));
        if (StrUtils.isNotEmpty(configValue)) {
            return configValue;
        }

        String sql = "select * from sys_config a where config_key = ?";
        Config config = db.queryForObject(sql, new Object[]{config_key}, Config.class);

        if (ObjectUtil.isNotNull(config)) {
            CacheUtils.put(getCacheName(), getCacheKey(config_key), config.getConfigValue());
            return config.getConfigValue();
        }
        return StrUtils.EMPTY;
    }
}