package cn.kunter.generator.util;

import cn.kunter.generator.exception.GeneratorException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Excel工具类
 * @author nature
 * @version 1.0 2021/7/20
 */
@Slf4j
public class ExcelUtils {

    /**
     * 工具类，私有构造
     */
    private ExcelUtils() {
        super();
    }

    /**
     * 加载Excel格式的数据字典，兼容xls和xlsx以及xlsm格式文件
     * @param filePath 文件全路径
     * @return Workbook
     * @throws GeneratorException
     */
    public static Workbook getWorkbook(String filePath) throws GeneratorException {

        if (StringUtils.isBlank(filePath)) {
            log.error("文件为空");
            throw new GeneratorException("文件为空");
        }

        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            log.error("文件为空");
            throw new GeneratorException("文件为空");
        }

        try (val inputStream = Files.newInputStream(path)) {
            return WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            log.error("文件读取错误", e);
            throw new GeneratorException("文件读取错误", e);
        }
    }

}
