package com.jqp.admin.common.service.impl;

import cn.hutool.core.date.DateUtil;
import com.jqp.admin.common.data.SerialNumber;
import com.jqp.admin.common.service.SerialNumberService;
import com.jqp.admin.db.service.JdbcService;
import com.jqp.admin.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service("serialNumberService")
public class SerialNumberServiceImpl implements SerialNumberService {
    @Resource
    private JdbcService jdbcService;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public synchronized String nextSerial(String code) {
        SerialNumber serialNumber = jdbcService.findOne(SerialNumber.class, "code", code);
        if(serialNumber == null){
            throw new RuntimeException("序号["+code+"]不存在");
        }
        String str = "";
        if(StringUtils.isNotBlank(serialNumber.getPrefix())){
            str += serialNumber.getPrefix();
        }
        if(StringUtils.isNotBlank(serialNumber.getDateFormat())){
            String date = DateUtil.format(new Date(),serialNumber.getDateFormat());
            if(!date.equals(serialNumber.getCurDate())){
                serialNumber.setCurSerial(0);
                serialNumber.setCurDate(date);
            }
            str += date;
        }
        if(serialNumber.getCurSerial() == null){
            serialNumber.setCurSerial(0);
        }
        serialNumber.setCurSerial(serialNumber.getCurSerial()+1);
        str += StringUtil.getAddCode(serialNumber.getCurSerial()+"","0", serialNumber.getSerialLenth());
        jdbcService.update(serialNumber);
        return str;
    }
}
