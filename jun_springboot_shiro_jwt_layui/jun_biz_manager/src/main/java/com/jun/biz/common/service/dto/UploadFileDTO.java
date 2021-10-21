package com.jun.biz.common.service.dto;

import lombok.Data;

import java.io.InputStream;

/**
 * Created on 2020/10/22 16:29
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Data
public class UploadFileDTO {
    private String filename;
    private InputStream inputStream;
}
