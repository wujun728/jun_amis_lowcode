package cn.kunter.generator.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

@Slf4j
class ExcelUtilsTests {

    @Test
    void test() throws Exception {

        String filePath = "/Users/nature/Documents/IdeaProjects/gdp-springboot/docs/表结构一览.xlsm";
        Workbook workbook = ExcelUtils.getWorkbook(filePath);

        // 遍历Sheet
        for (int i = 2; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);

            // 表名称（物理名称）
            String tableName = sheet.getRow(1).getCell(7).getStringCellValue();
            // 表备注（表名称）
            String tableRemarks = sheet.getRow(0).getCell(7).getStringCellValue();
            log.info("tableName: {}, tableRemarks: {}", tableName, tableRemarks);

            // 遍历Row
            for (int j = 5; j < sheet.getPhysicalNumberOfRows(); j++) {
                Row row = sheet.getRow(j);

                // 编号（序号）
                Cell serialCell = row.getCell(0);
                CellType serialCellType = serialCell.getCellType();
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
                String columnName = row.getCell(2).getStringCellValue();

                // 物理名
                String sqlName = row.getCell(9).getStringCellValue();

                // 类型
                String sqlType = row.getCell(16).getStringCellValue().toUpperCase();

                // 长度
                Cell lengthCell = row.getCell(21);
                CellType lengthCellType = lengthCell.getCellType();
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
                String notNullValue = row.getCell(24).getStringCellValue();
                Boolean notNull = false;
                if (StringUtils.isNotBlank(notNullValue)) {
                    notNull = true;
                }

                // 主键
                String primaryKeyValue = row.getCell(26).getStringCellValue();
                Boolean primaryKey = false;
                if (StringUtils.isNotBlank(primaryKeyValue)) {
                    primaryKey = true;
                }
                // 主键顺序
                Cell primaryKeyOrderCell = row.getCell(28);
                CellType primaryKeyOrderCellType = primaryKeyOrderCell.getCellType();
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
                String remarks = row.getCell(31).getStringCellValue();

                System.out
                        .println("编号：" + serial + "，列名：" + columnName + "，物理名：" + sqlName + "，类型：" + sqlType +
                                "，长度：" + length + "，不为空：" + notNull + "，主键：" + primaryKey + "，主键顺序：" + primaryKeyOrder +
                                "，备注：" + remarks);
            }
        }

    }

}
