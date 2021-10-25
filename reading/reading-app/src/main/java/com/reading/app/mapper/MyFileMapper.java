package com.reading.app.mapper;

import com.reading.app.domain.MyFile;

public interface MyFileMapper {

    int insertFile(MyFile myFile);

    MyFile getFile(MyFile myFile);
}
