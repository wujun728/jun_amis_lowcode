package com.reading.app.mapper;

import org.apache.ibatis.annotations.Param;
import com.reading.app.domain.Integral;

public interface IntegralMapper {

    int insertIntegral(Integral integral);

    Integral selectByAid(@Param("aid") int aid);

    int update(Integral integral);

}
