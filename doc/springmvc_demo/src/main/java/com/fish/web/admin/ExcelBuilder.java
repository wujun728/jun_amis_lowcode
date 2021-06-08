package com.fish.web.admin;

import com.fish.domain.Order;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 *
 * @author www.codejava.net
 */
public class ExcelBuilder extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        List<Order> orders = (List<Order>) model.get("orders");

        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet(new Date().getTime() + ".xls");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + new Date().getTime() + ".xls" + "\"");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("ID");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Address");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("isDone");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Email");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Name");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("Note");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("OrderId");
        header.getCell(6).setCellStyle(style);
        header.createCell(7).setCellValue("Phone");
        header.getCell(7).setCellStyle(style);
        header.createCell(8).setCellValue("Price");
        header.getCell(8).setCellStyle(style);
        header.createCell(9).setCellValue("Time");
        header.getCell(9).setCellStyle(style);


        // create data rows
        int rowCount = 1;

        for (Order order : orders) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(order.getId());
            aRow.createCell(1).setCellValue(order.getAddress());
            aRow.createCell(2).setCellValue(order.getDone());
            aRow.createCell(3).setCellValue(order.getEmail());
            aRow.createCell(4).setCellValue(order.getName());
            aRow.createCell(5).setCellValue(order.getNote());
            aRow.createCell(6).setCellValue(order.getOrderId());
            aRow.createCell(7).setCellValue(order.getPhone());
            aRow.createCell(8).setCellValue(order.getPrice());
            aRow.createCell(9).setCellValue(order.getTime().toString());
        }
    }

}
