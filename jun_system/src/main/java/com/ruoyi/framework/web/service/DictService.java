package com.ruoyi.framework.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ruoyi.project.common.CommonService;
import com.ruoyi.project.system.dict.domain.DictData;

/**
 * RuoYi首创 html调用 thymeleaf 实现字典读取
 * @author ruoyi
 */
@Service("dict")
public class DictService extends CommonService {

	/**
	 * 根据字典类型查询字典数据信息
	 * @param dictType 字典类型
	 * @return
	 */
	public List<DictData> getType(String dictType) {
    	String sql = "select dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, "+
			     	 "       list_class, is_default, status, create_by, create_time, remark "+
			     	 "  from sys_dict_data where dict_type = ? and status = '0' order by dict_sort";
		return db.queryForList(sql, new Object[]{dictType}, DictData.class);
	}

	/**
	 * 根据字典类型和字典键值查询字典数据信息
	 * @param dictType 字典类型
	 * @param dictValue 字典键值
	 * @return 字典标签
	 */
	public String getLabel(String dictType, String dictValue) {
		String sql = "select dict_label from sys_dict_data where dict_type = ? and dict_value = ?";
		return db.queryForString(sql, new Object[]{dictType, dictValue});
	}
}