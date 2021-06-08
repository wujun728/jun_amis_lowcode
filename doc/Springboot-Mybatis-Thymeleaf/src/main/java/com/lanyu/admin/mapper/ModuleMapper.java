package com.lanyu.admin.mapper;


import com.lanyu.common.model.Module;
import com.lanyu.common.model.vo.ModuleTree;
import com.lanyu.common.model.vo.ReszTreeData;

import java.util.HashMap;
import java.util.List;

/**
 * 权限资源数据接口
 */
public interface ModuleMapper {
    /**
     * 普通查询
     * @param param
     * @return
     */
    List<Module> selectModuleList(HashMap<String, Object> param);

    /**
     * 树形表格的资源查询
     * @return
     */
    List<ModuleTree> zTreeModuleList();

    /**
     * 添加
     * @param module
     * @return
     */
    int insert(Module module);

    /**
     * 修改
     * @param module
     */
    void updateByKeySelective(Module module);

    /**
     * 删除
     * @param mcode
     * @return
     */
    int deleteByKey(String mcode);

    /**
     * 获取角色资源
     * @param rcode
     * @return
     */
    List<HashMap<String, Object>> selectRoleModule(String rcode);
}