package com.lanyu.admin.service;

import com.lanyu.common.model.Module;
import com.lanyu.common.model.Role;
import com.lanyu.common.model.vo.ModuleTree;
import com.lanyu.common.model.vo.ReszTreeData;

import java.util.HashMap;
import java.util.List;

/**
 * 资源权限
 * Created by Administrator on 2017/8/16.
 */
public interface ModuleService {

    /**
     * 查询资源列表
     * @param param
     * @return
     */
    List<Module> selectModuleList(HashMap<String, Object> param);

    /**
     * 树形资源列表
     * @param param
     * @return
     */
    List<ModuleTree> zTreeModuleList();

    /**
     * 新增资源
     * @param module
     * @return
     */
    int insert(Module module);

    /**
     * 修改资源
     * @param module
     */
    void updateByKeySelective(Module module);

    /**
     * 删除资源
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
