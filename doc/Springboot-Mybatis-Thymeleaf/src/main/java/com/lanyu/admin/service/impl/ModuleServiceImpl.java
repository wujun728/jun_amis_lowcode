package com.lanyu.admin.service.impl;

import com.lanyu.admin.mapper.ModuleMapper;
import com.lanyu.admin.service.ModuleService;
import com.lanyu.common.model.Module;
import com.lanyu.common.model.vo.ModuleTree;
import com.lanyu.common.model.vo.ReszTreeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service(value = "moduleService")
@Transactional(rollbackFor=Exception.class)
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper dao;
    @Override
    public List<Module> selectModuleList(HashMap<String, Object> param) {
        return dao.selectModuleList(param);
    }

    @Override
    public List<ModuleTree> zTreeModuleList() {
        return dao.zTreeModuleList();
    }

    @Override
    public int insert(Module module) {
        return dao.insert(module);
    }

    @Override
    public void updateByKeySelective(Module module) {
         dao.updateByKeySelective(module);
    }

    @Override
    public int deleteByKey(String mcode) {
        return dao.deleteByKey(mcode);
    }

    @Override
    public List<HashMap<String, Object>> selectRoleModule(String rcode) {
        System.out.print(rcode+"---------------------------------");
        return dao.selectRoleModule(rcode);
    }
}
