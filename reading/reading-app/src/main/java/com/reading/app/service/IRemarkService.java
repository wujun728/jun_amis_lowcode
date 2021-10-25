package com.reading.app.service;

import com.reading.app.domain.Remark;

import java.util.List;

public interface IRemarkService {

    int remarkPost(Remark remark);

    int deleteRemark(int rid);

    List<Remark> getRemarkList(int id);
}
