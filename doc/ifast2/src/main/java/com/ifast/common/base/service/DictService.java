package com.ifast.common.base.service;

import java.util.List;

import com.ifast.common.base.CoreService;
import com.ifast.common.base.domain.DictDO;
import com.ifast.sys.base.domain.UserDO;

/**
 * <pre>
 * 数据字典
 * </pre>
 * 
 * <small> 2018年1月3日 | Aron</small>
 */
public interface DictService extends CoreService<DictDO> {
    
    List<DictDO> listType();

    String getName(String type, String value);

    /**
     * 获取爱好列表
     * 
     * @return
     * @param userDO
     */
    List<DictDO> getHobbyList(UserDO userDO);

    /**
     * 获取性别列表
     * 
     * @return
     */
    List<DictDO> getSexList();
}
