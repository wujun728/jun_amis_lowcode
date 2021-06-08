package com.hope.exception.file;


import com.hope.exception.BaseException;

/**
 * 文件信息异常类
 *
 * @author aodeng
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
