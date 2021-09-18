package com.jun.biz.common.service;

import java.io.IOException;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.common.service.dto.SaveAdHtmlDTO;
import com.jun.biz.common.service.dto.TempToAdDTO;
import com.jun.biz.common.service.dto.TempToProductDTO;
import com.jun.biz.common.service.dto.UploadFileDTO;
import com.jun.biz.common.service.vo.FileVO;

/**
 * Created on 2020/10/22 16:26
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
public interface FileService {
    /**
     * 上传临时文件
     *
     * @param dto
     * @return
     */
    ResultVO<FileVO> uploadTemp(UploadFileDTO dto) throws IOException;

    ResultVO<FileVO> tempToAd(TempToAdDTO dto);

    ResultVO<FileVO> tempToProduct(TempToProductDTO dto);

    ResultVO<FileVO> saveAdHtml(SaveAdHtmlDTO dto);
}
