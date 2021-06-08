package com.hope.service.impl;

import com.hope.mapper.GetIDMapper;
import com.hope.model.bean.GetID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * getId 数据库主键id使用自定义规则获取 即uuid
 *
 * @author aodeng
 */
@Service
public class GetIDService {

    @Autowired
    private GetIDMapper getIDMapper;

    /**
     * 调用存储过程获取id
     *
     * @author aodeng
     */
    public String getId(String tableName) {
        GetID getID = new GetID();
        getID.setName(tableName);
        getIDMapper.getID(getID);
        return getID.getId();
    }
}