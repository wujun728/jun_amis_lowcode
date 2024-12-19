package com.ruoyi.common.utils.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.web.domain.AjaxResult;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelWriter;

/**
 * Excel相关处理
 * @author daixf
 */
public class ExcelUtilx {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtilx.class);

    /**
     * 对list数据源将其里面的数据导入到excel表单
     * @return 结果
     */
    public static AjaxResult exportExcel(Map<String, String> headers, List<?> dataList, String fileName) {
    	if(!StrUtil.endWithIgnoreCase(fileName, ".xls") && !StrUtil.endWithIgnoreCase(fileName, ".xlsx")) {
    		fileName += ".xlsx";
    	}

        //通过工具类创建writer
        ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getWriter(true);
        writer.setOnlyAlias(true);
        writer.setHeaderAlias(headers);//表头

        OutputStream out = null;
        try {
			out = new FileOutputStream(FileUtils.getAbsoluteFile(fileName));
			//一次性写出内容，使用默认样式，强制输出标题
			writer.write(dataList, true);
			//out为OutputStream，需要写出到的目标流
			writer.flush(out);
			//关闭writer，释放内存
            writer.close();
		} catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            throw new BusinessException("导出Excel失败，请联系网站管理员！");
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return AjaxResult.success(fileName);
    }
}