package cn.kunter.generator.datasource;

import cn.kunter.generator.datasource.enums.SourceType;
import cn.kunter.generator.entity.Table;
import cn.kunter.generator.exception.GeneratorException;
import cn.kunter.generator.util.ExcelUtils;
import cn.kunter.generator.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.apache.poi.ss.usermodel.CellType;

import java.util.List;

/**
 * Excel数据源
 * @author nature
 * @version 1.0 2021/7/20
 */
@Slf4j
public class ExcelDataSource implements DataSource {

    @Override
    public List<Table> getTables() throws GeneratorException {

        val filePath = "/Users/nature/Documents/IdeaProjects/gdp-springboot/docs/表结构一览.xlsm";
        val workbook = ExcelUtils.getWorkbook(filePath);

        // 遍历Sheet
        for (int i = 2; i < workbook.getNumberOfSheets(); i++) {
            val sheet = workbook.getSheetAt(i);

            // 表名称（物理名称）
            val tableName = sheet.getRow(1).getCell(7).getStringCellValue();
            // 表备注（表名称）
            val tableRemarks = sheet.getRow(0).getCell(7).getStringCellValue();
            log.info("getTables tableName: {}, tableRemarks: {}", tableName, tableRemarks);

            Table table = Table.builder().tableName(tableName).remarks(tableRemarks).build();

            // 遍历Row
            for (int j = 5; j < sheet.getPhysicalNumberOfRows(); j++) {
                val row = sheet.getRow(j);

                // 编号（序号）
                val serialCell = row.getCell(0);
                val serialCellType = serialCell.getCellType();
                String serial;
                // 判断为公式或者数值类型，采用getNumericCellValue获取值，并转换成String
                if (CellType.FORMULA == serialCellType || CellType.NUMERIC == serialCellType) {
                    serial = String.valueOf(Double.valueOf(serialCell.getNumericCellValue()).intValue());
                }
                // 其他类型，使用getStringCellValue获取值
                else {
                    serial = serialCell.getStringCellValue();
                }

                // 列名
                val columnName = row.getCell(2).getStringCellValue();
                // 物理名
                val sqlName = row.getCell(9).getStringCellValue();
                // 类型
                val sqlType = row.getCell(16).getStringCellValue().toUpperCase();

                // 长度
                val lengthCell = row.getCell(21);
                val lengthCellType = lengthCell.getCellType();
                String length;
                // 判断为公式或者数值类型，采用getNumericCellValue获取值，并转换成String
                if (CellType.FORMULA == lengthCellType || CellType.NUMERIC == lengthCellType) {
                    length = String.valueOf(Double.valueOf(lengthCell.getNumericCellValue()).intValue());
                }
                // 其他类型，使用getStringCellValue获取值
                else {
                    length = lengthCell.getStringCellValue();
                }

                // 不为空
                val notNullValue = row.getCell(24).getStringCellValue();
                var notNull = false;
                if (StringUtils.isNotBlank(notNullValue)) {
                    notNull = true;
                }

                // 主键
                val primaryKeyValue = row.getCell(26).getStringCellValue();
                var primaryKey = false;
                if (StringUtils.isNotBlank(primaryKeyValue)) {
                    primaryKey = true;
                }
                // 主键顺序
                val primaryKeyOrderCell = row.getCell(28);
                val primaryKeyOrderCellType = primaryKeyOrderCell.getCellType();
                String primaryKeyOrder;
                // 判断为公式或者数值类型，采用getNumericCellValue获取值，并转换成String
                if (CellType.FORMULA == primaryKeyOrderCellType || CellType.NUMERIC == primaryKeyOrderCellType) {
                    primaryKeyOrder = String
                            .valueOf(Double.valueOf(primaryKeyOrderCell.getNumericCellValue()).intValue());
                }
                // 其他类型，使用getStringCellValue获取值
                else {
                    primaryKeyOrder = primaryKeyOrderCell.getStringCellValue();
                }

                // 备注
                val remarks = row.getCell(31).getStringCellValue();

            }
        }

        return null;
    }

    @Override
    public SourceType getSourceType() {
        return SourceType.EXCEL;
    }

}
