package com.ruoyi.project.system.dict.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.collection.CollectionUtil;
import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.utils.DictUtils;
import com.ruoyi.project.system.param.domain.Config;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.db.BatchSql;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import cn.hutool.core.convert.Convert;
import com.ruoyi.framework.web.domain.Ztree;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.CommonService;
import com.ruoyi.project.system.dict.domain.DictType;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 字典 业务层处理
 * @author ruoyi
 */
@Service
public class DictTypeService extends CommonService {

	private String baseSelectSql = "select dict_id, dict_name, dict_type, status, create_by, create_time, remark from sys_dict_type ";

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        String sql = baseSelectSql+" order by dict_name";
        List<DictType> dictTypeList = db.queryForList(sql, null, DictType.class);
        for (DictType dictType : dictTypeList) {
            DictUtils.setDictCache(dictType.getDictType(), this.selectDictItemByType(dictType.getDictType()));
        }
    }

    /**
     * 查询字典配置（分页查询）
     * @param request
     * @return
     */
    public TableDataInfo selectDictTypeList(HttpServletRequest request, boolean pagination) {
    	String dict_name = RequestUtil.getValue(request, "dict_name");
    	String dict_type = RequestUtil.getValue(request, "dict_type");
    	String status = RequestUtil.getValue(request, "status");
    	String start_time = RequestUtil.getValue(request, "start_time");
    	String end_time = RequestUtil.getValue(request, "end_time");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer();
    	sql.append(baseSelectSql+" where 1 = 1 ");

    	if(StrUtil.isNotBlank(dict_name)) {
    		sql.append(" and dict_name like concat('%', ?, '%') ");
    		paramList.add(dict_name);
    	}
    	if(StrUtil.isNotBlank(dict_type)) {
    		sql.append(" and dict_type like concat('%', ?, '%') ");
    		paramList.add(dict_type);
    	}
    	if(StrUtil.isNotBlank(status)) {
    		sql.append(" and status = ? ");
    		paramList.add(status);
    	}
    	if(StrUtil.isNotBlank(start_time)) {
    		sql.append(" and date_format(create_time,'%y%m%d') >= date_format(?,'%y%m%d') ");
    		paramList.add(start_time);
    	}
    	if(StrUtil.isNotBlank(end_time)) {
    		sql.append(" and date_format(create_time,'%y%m%d') <= date_format(?,'%y%m%d') ");
    		paramList.add(end_time);
    	}

		//拼接排序语句
		this.addOrderBySql(request, sql, "create_time desc");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, pagination);
    }

	/**
	 * 根据所有字典类型
	 * @return 字典类型集合信息
	 */
	public List<Map<String, Object>> selectDictTypeAll() {
		String sql = baseSelectSql+" order by dict_name";
    	return db.queryForList(sql, null);
	}

	/**
	 * 根据字典类型ID查询信息
	 * @param dictId 字典类型ID
	 * @return 字典类型
	 */
	public DictType selectDictTypeById(String dict_id) {
		String sql = baseSelectSql+" where dict_id = ?";
		return db.queryForObject(sql, new Object[]{dict_id}, DictType.class);
	}

	/**
	 * 根据字典类型查询信息
	 * @param dictType 字典类型
	 * @return 字典类型
	 */
	public DictType selectDictTypeByType(String dict_type) {
		String sql = baseSelectSql+" where dict_type = ?";
		return db.queryForObject(sql, new Object[]{dict_type}, DictType.class);
	}

	/**
	 * 批量删除字典类型
	 * @param ids 需要删除的数据
	 * @return 结果
	 */
	public int deleteDictTypeByIds(String ids) {
		String[] dictIds = Convert.toStrArray(ids);

        //根据参数个数创建相应数量的占位符
        String placeholders = SqlUtil.rebuildInSql(dictIds.length);

        //查询出待删除的字典类型
        String sql = "select dict_type from sys_dict_type where dict_id in ("+placeholders+")";
        List<String> dictList = db.queryForList(sql, dictIds, "dict_type");

    	BatchSql batchSql = new BatchSql();
    	sql = "delete from sys_dict_data a where exists(select 1 from sys_dict_type b " +
    		  " where a.dict_type = b.dict_type and b.dict_id in ("+placeholders+"))";
    	batchSql.addBatch(sql, dictIds);

    	sql = "delete from sys_dict_type where dict_id in ("+placeholders+")";
    	batchSql.addBatch(sql, dictIds);

    	int res = db.doInTransaction(batchSql);
        if (res > 0) {
            for(String dictType : dictList) {
                DictUtils.removeDictCache(dictType);
            }
        }

		return res;
	}

    /**
     * 清空缓存数据
     */
    public void clearCache() {
        DictUtils.clearDictCache();
    }

	/**
	 * 新增保存字典类型信息
	 * @param dictType 字典类型信息
	 * @return 结果
	 */
	public int insertDictType(HttpServletRequest request) {
		String dict_name = RequestUtil.getValue(request, "dict_name");
    	String dict_type = RequestUtil.getValue(request, "dict_type");
    	String status = RequestUtil.getValue(request, "status");
    	String remark = RequestUtil.getValue(request, "remark");
    	String operator_id = ShiroUtils.getLoginName();

    	BatchSql batchSql = new BatchSql();
    	String sql = "insert into sys_dict_type(dict_name, dict_type, status, remark, create_by, create_time) " +
    				 "values(?, ?, ?, ?, ?, sysdate())";
		batchSql.addBatch(sql, new Object[]{dict_name, dict_type, status, remark, operator_id});

		//保存字典项
		this.saveDictItems(request, batchSql);

		int res = db.doInTransaction(batchSql);
		if(res == 1) {
		    DictUtils.setDictCache(dict_type, this.selectDictItemByType(dict_type));
        }
		return res;
	}

	/**
	 * 修改保存字典类型信息
	 * @param dictType 字典类型信息
	 * @return 结果
	 */
	public int updateDictType(HttpServletRequest request) {
		String dict_id = RequestUtil.getValue(request, "dict_id");
		String dict_name = RequestUtil.getValue(request, "dict_name");
    	String dict_type = RequestUtil.getValue(request, "dict_type");
    	String old_dict_type = RequestUtil.getValue(request, "old_dict_type");
    	String status = RequestUtil.getValue(request, "status");
    	String remark = RequestUtil.getValue(request, "remark");
    	String operator_id = ShiroUtils.getLoginName();

    	BatchSql batchSql = new BatchSql();
		String sql = "update sys_dict_type a "+
				 	 "   set dict_name = ?, dict_type = ?, status = ?, remark = ?," +
				 	 "		 update_by = ?, update_time = sysdate() " +
				 	 " where dict_id = ?";
		batchSql.addBatch(sql, new Object[]{dict_name, dict_type, status, remark, operator_id, dict_id});

		sql = "delete from sys_dict_data where dict_type = ?";
		batchSql.addBatch(sql, new Object[]{old_dict_type});

		//保存字典项
		this.saveDictItems(request, batchSql);

        int res = db.doInTransaction(batchSql);
        if(res == 1) {
            DictUtils.setDictCache(dict_type, this.selectDictItemByType(dict_type));
        }
        return res;
	}

    /**
     * 保存字典项
     * @param request
     * @return 结果
     */
    public void saveDictItems(HttpServletRequest request, BatchSql batchSql) {
    	String dictType = RequestUtil.getValue(request, "dict_type");
    	String[] dictLabels = RequestUtil.getValues(request, "dict_label");
    	String[] dictValues = RequestUtil.getValues(request, "dict_value");
    	String[] cssClasses = RequestUtil.getValues(request, "css_class");
    	String[] dictSorts = RequestUtil.getValues(request, "dict_sort");
    	String[] statuses = RequestUtil.getValues(request, "item_status");
    	String[] listClasses = RequestUtil.getValues(request, "list_class");
    	String[] isDefaults = RequestUtil.getValues(request, "is_default");
    	String[] remarks = RequestUtil.getValues(request, "item_remark");
    	String createBy = ShiroUtils.getLoginName();

    	String sql = "insert into sys_dict_data(dict_type, dict_label, dict_value, dict_sort, css_class, list_class, is_default, " +
    				 "	status, remark, create_by, create_time) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate())";
    	for(int i=0;i<dictLabels.length;i++) {
    		batchSql.addBatch(sql, new Object[]{dictType, dictLabels[i], dictValues[i], dictSorts[i], cssClasses[i],
    				listClasses[i], isDefaults[i], statuses[i], remarks[i], createBy});
    	}
    }

	/**
	 * 校验字典组编号是否唯一
	 * @param request
	 * @return 结果
	 */
	public int checkDictTypeUnique(HttpServletRequest request) {
		String dict_id = RequestUtil.getValue(request, "dict_id");
    	String dict_type = RequestUtil.getValue(request, "dict_type");

		List<String> paramList = new ArrayList<String>();
		String sql = "select count(1) from sys_dict_type where dict_type = ? ";
		paramList.add(dict_type);

		if(StrUtil.isNotBlank(dict_id)) {
    		sql += " and dict_id <> ? ";
    		paramList.add(dict_id);
    	}
		return db.queryForInt(sql, paramList.toArray());
	}

	/**
	 * 查询字典类型树
	 * @param dictType 字典类型
	 * @return 所有字典类型
	 */
	public List<Ztree> selectDictTree() {
		List<Ztree> ztrees = new ArrayList<Ztree>();
		List<Map<String, Object>> dictList = selectDictTypeAll();
		for (Map<String, Object> dict : dictList) {
			if("0".equals(MapUtil.getStr(dict, "status"))) {
				Ztree ztree = new Ztree();
				ztree.setId(MapUtil.getLong(dict, "dict_id"));
				ztree.setName(MapUtil.getStr(dict, "dict_name"));
				ztree.setTitle(MapUtil.getStr(dict, "dict_type"));
				ztrees.add(ztree);
			}
		}
		return ztrees;
	}

	public String transDictName(DictType dictType) {
		StringBuffer sb = new StringBuffer();
		sb.append("(" + dictType.getDictName() + ")");
		sb.append("&nbsp;&nbsp;&nbsp;" + dictType.getDictType());
		return sb.toString();
	}

    /**
     * 根据字典类型查询字典数据
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    public List<Map<String, Object>> selectDictItemList(String dict_type) {
    	String sql = "select dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, "+
    			     "       list_class, is_default, status, create_by, create_time, remark "+
    			     "  from sys_dict_data where dict_type = ? order by dict_sort";
        return db.queryForList(sql, new Object[]{dict_type});
    }

    /**
     * 根据字典类型查询字典数据
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    public List<DictData> selectDictItemByType(String dictType) {
        String sql = "select dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, "+
                "       list_class, is_default, status, create_by, create_time, remark "+
                "  from sys_dict_data where dict_type = ? order by dict_sort";
        return db.queryForList(sql, new Object[]{dictType}, DictData.class);
    }

    /**
     * 根据字典类型查询字典数据
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    public List<DictData> selectDictDataByType(String dictType) {
        List<DictData> dictDatas = DictUtils.getDictCache(dictType);
        if (CollectionUtil.isNotEmpty(dictDatas)) {
            return dictDatas;
        }

        dictDatas = this.selectDictItemByType(dictType);
        if (CollectionUtil.isNotEmpty(dictDatas)) {
            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }
}