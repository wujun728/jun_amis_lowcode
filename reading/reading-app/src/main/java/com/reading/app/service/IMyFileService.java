package com.reading.app.service;

import com.reading.app.domain.MyFile;

import java.io.File;

public interface IMyFileService {

    int insertFile(MyFile myFile);

    MyFile getFile(MyFile myFile);
}
