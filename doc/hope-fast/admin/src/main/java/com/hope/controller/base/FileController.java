package com.hope.controller.base;

import cn.hutool.core.util.RandomUtil;
import com.hope.utils.AjaxResult;
import com.hope.utils.StringUtils;
import com.hope.utils.file.FileUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传控制类
 *
 * @author aodeng
 */
@Controller
@RequestMapping("file")
public class FileController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
    * 文件上传
    * @author aodeng
    */
    @PostMapping("/uploadFile")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fileName = RandomUtil.randomNumbers(10) + "_" + file.getOriginalFilename();
            String avatar = FileUploadUtils.upload("/equipment", file, fileName);
            if (StringUtils.isNotEmpty(avatar)) {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", avatar);
                logger.info(avatar);
                return ajax;
            }
        }
        return AjaxResult.error("上传图片异常");
    }
}
