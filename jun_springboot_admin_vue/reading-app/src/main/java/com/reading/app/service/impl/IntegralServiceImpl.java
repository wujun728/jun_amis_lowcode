package com.reading.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reading.app.mapper.IntegralMapper;
import com.reading.app.domain.Integral;
import com.reading.app.service.IIntegralService;

@Service
public class IntegralServiceImpl implements IIntegralService {

    @Autowired
    IntegralMapper integralMapper;

    @Override
    public int sing(Integral integral) {
        Integral integralRe = integralMapper.selectByAid(integral.getAid());
        if (integralRe != null) {
            //有
            integralRe.setIntegral(integralRe.getIntegral() + 5);
            return integralMapper.update(integralRe);
        } else {
            integral.setIntegral(5);
            return integralMapper.insertIntegral(integral);
        }
    }
}
