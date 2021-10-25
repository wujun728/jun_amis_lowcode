package com.reading.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reading.app.mapper.RemarkMapper;
import com.reading.app.domain.Remark;
import com.reading.app.service.IRemarkService;

import java.util.List;

@Service
public class RemarkServiceImpl implements IRemarkService {

    @Autowired
    RemarkMapper remarkMapper;

    @Override
    public int remarkPost(Remark remark) {
        return remarkMapper.insert(remark);
    }

    @Override
    public int deleteRemark(int rid) {
        return remarkMapper.delete(rid);
    }

    @Override
    public List<Remark> getRemarkList(int pid) {
        return remarkMapper.getListByAid(pid);
    }
}
