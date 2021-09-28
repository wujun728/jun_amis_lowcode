package com.jun.plugin.generatorexcel;

import com.jun.plugin.generatorexcel.model.DatasourceProperties;
import com.jun.plugin.generatorexcel.model.ReadConfiguration;
import com.jun.plugin.generatorexcel.service.ExportService;
import com.jun.plugin.generatorexcel.service.SqlReader;

/**
 * @Author: Chenzedeng
 * @Email: chenzedeng@geexfinance.com
 * @Date: 2021/4/20 16:43
 * @description:
 * @version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        ReadConfiguration readConfiguration = new ReadConfiguration();
        DatasourceProperties datasourceProperties = readConfiguration.getDatasourceProperties();

        System.out.println("-----开始解析-----");
        SqlReader sqlReader = new SqlReader(datasourceProperties);
        ExportService exportService = new ExportService();
        exportService.exportFile(datasourceProperties.getExportPath(), datasourceProperties.getDatabaseName(), sqlReader.getExcelViewList());
    }
}
