package com.reading.app.mapper;

import org.apache.ibatis.annotations.Param;
import com.reading.app.domain.Remark;

import java.util.List;

public interface RemarkMapper {

    int insert(Remark remark);

    int delete(@Param("rid") int rid);

    Remark getRemark(Remark remark);

    List<Remark> getListByAid(@Param("rid") int rid);

}
