package com.jun.biz.common.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.common.config.properties.FilePathProperties;
import com.jun.biz.common.service.FileService;
import com.jun.biz.common.service.dto.SaveAdHtmlDTO;
import com.jun.biz.common.service.dto.TempToAdDTO;
import com.jun.biz.common.service.dto.TempToProductDTO;
import com.jun.biz.common.service.dto.UploadFileDTO;
import com.jun.biz.common.service.vo.FileVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Created on 2020/10/22 16:30
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Slf4j
public class FileServiceImpl implements FileService {


    private final FilePathProperties filePathProperties;

    public FileServiceImpl(FilePathProperties filePathProperties) {
        this.filePathProperties = filePathProperties;
    }

    @Override
    public ResultVO<FileVO> uploadTemp(UploadFileDTO dto)  {
        String path = filePathProperties.getTempPath() + "/" + DateFormatUtils.format(new Date(), "yyyy-MM-dd") + "/" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(dto.getFilename());
        File targetFile = new File(filePathProperties.getBasePath() + path);
        if (!targetFile.getParentFile().exists()) {
            boolean mkdirs = targetFile.getParentFile().mkdirs();
            log.info("创建父目录{}{}", targetFile.getAbsolutePath(), mkdirs ? "成功" : "失败");
        }
        try(
                FileInputStream fileInputStream = (FileInputStream) dto.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(targetFile)
        ) {
            fileInputStream.getChannel().transferTo(0, fileInputStream.available(),fileOutputStream.getChannel());
        } catch (IOException e) {
            log.error("文件拷贝异常！",e);
            return ResultVO.buildFailResult("文件拷贝异常！");
        }
        FileVO vo = new FileVO();
        vo.setPath(path);
        vo.setUrl(filePathProperties.getDomain() + path);
        return ResultVO.buildSuccessResult(vo);
    }

    @Override
    public ResultVO<FileVO> tempToAd(TempToAdDTO dto)  {
        File tempFile = new File(filePathProperties.getBasePath() + dto.getTempFilePath());
        if (!tempFile.exists()) {
            return ResultVO.buildFailResult("未找到源文件");
        }
        String name = FilenameUtils.getName(dto.getTempFilePath());
        String path = filePathProperties.getAdPath() + "/image/" + dto.getAdSpaceId() + "/" + DateFormatUtils.format(new Date(), "yyyy-MM-dd") + "/" + name;
        return storeFile(path,tempFile);

    }

    @Override
    public ResultVO<FileVO> tempToProduct(TempToProductDTO dto)  {
        File tempFile = new File(filePathProperties.getBasePath() + dto.getTempFilePath());
        if (!tempFile.exists()) {
            return ResultVO.buildFailResult("未找到源文件");
        }
        String name = FilenameUtils.getName(dto.getTempFilePath());
        String path = filePathProperties.getProduct().getPath() + "/" + dto.getProductId() + "/image/" + DateFormatUtils.format(new Date(), "yyyy-MM-dd") + "/" + name;
        return storeFile(path,tempFile);
    }

    private ResultVO<FileVO> storeFile(String targetPath,File srcFile)  {
        File targetFile = new File(filePathProperties.getBasePath() + targetPath);
        if (!targetFile.getParentFile().exists()) {
            boolean mkdirs = targetFile.getParentFile().mkdirs();
            log.info("创建父目录{}{}", targetFile.getAbsolutePath(), mkdirs ? "成功" : "失败");
        }
        try(
                final FileInputStream fileInputStream = new FileInputStream(srcFile);
                final FileOutputStream fileOutputStream = new FileOutputStream(targetFile)
        ){
            fileInputStream.getChannel().transferTo(0, fileInputStream.available(),fileOutputStream.getChannel());
        } catch (IOException e) {
            log.error("文件拷贝异常！",e);
            return ResultVO.buildFailResult("文件拷贝异常！");
        }
        FileVO vo = new FileVO();
        vo.setPath(targetPath);
        vo.setUrl(filePathProperties.getDomain() + targetPath);
        return ResultVO.buildSuccessResult(vo);
    }

    @Override
    public ResultVO<FileVO> saveAdHtml(SaveAdHtmlDTO dto) {
        String path = filePathProperties.getAdPath() + "/html/" + dto.getNo() + ".html";
        File targetFile = new File(filePathProperties.getBasePath() + path);
        if (!targetFile.getParentFile().exists()) {
            boolean mkdirs = targetFile.getParentFile().mkdirs();
            log.info("创建父目录{}{}", targetFile.getAbsolutePath(), mkdirs ? "成功" : "失败");
        }
        try(final FileChannel fileChannel = new FileOutputStream(targetFile).getChannel()) {
            fileChannel.write(ByteBuffer.wrap(dto.getHtml().getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            log.error("写文件异常！",e);
            return ResultVO.buildFailResult("写文件异常");
        }
        FileVO vo = new FileVO();
        vo.setPath(path);
        vo.setUrl(filePathProperties.getDomain() + path);
        return ResultVO.buildSuccessResult(vo);
    }
}
