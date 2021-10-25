package com.reading.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.reading.app.mapper.MyFileMapper;
import com.reading.app.domain.MyFile;
import com.reading.app.service.IMyFileService;

public class MyFileServiceImpl implements IMyFileService {

    @Autowired
    MyFileMapper myFileMapper;

    @Override
    public int insertFile(MyFile myFile) {
        return myFileMapper.insertFile(myFile);
    }

    @Override
    public MyFile getFile(MyFile myFile) {
        return myFileMapper.getFile(myFile);
    }
}
