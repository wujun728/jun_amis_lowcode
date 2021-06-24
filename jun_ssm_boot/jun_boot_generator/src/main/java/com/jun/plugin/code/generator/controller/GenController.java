package com.jun.plugin.code.generator.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.code.generator.config.GenConfig;
import com.jun.plugin.code.generator.domain.GenTable;
import com.jun.plugin.code.generator.service.IGenTableService;
import com.jun.plugin.code.generator.util.AjaxResult;
import com.jun.plugin.code.generator.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 代码生成 操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/api/gen")
public class GenController extends BaseController {
    @Autowired
    private IGenTableService genTableService;
    
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException
    {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（下载方式）
     */
    @GetMapping("/download/{key}/{tableName}/{packages}")
    public void download(HttpServletResponse response,
                         @PathVariable("key") String key,
                         @PathVariable("tableName") String tableName,
                         @PathVariable("packages") String packages) throws IOException {
        System.out.println(GenConfig.getSecretKey());
        System.out.println(key);
        if (GenConfig.getSecretKey().equals(key)) {
            if (StringUtils.isBlank(packages)) {
                packages = GenConfig.getPackageName();
            }

            // 查询是否已经有相同名称的表数据, 如果有, 则先删除
            GenTable query = new GenTable();
            query.setTableName(tableName);
            List<GenTable> list = genTableService.selectGenTableList(query);
            if (list.size() > 0) {
                genTableService.deleteGenTableByIds(String.valueOf(list.stream().map(GenTable::getTableId).toArray(Long[]::new)));
            }

            // 查询表元数据信息
            String[] tableNames = new String[]{tableName};
            List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);

            genTableService.importGenTable(tableList, packages);
            byte[] data = genTableService.downloadCode(tableName);
            genCode(response, data);
        } else {
            response.setStatus(404);
        }
    }
    
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        genTableService.deleteGenTableByIds(ids);
        return AjaxResult.success();
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin" , "*");
        response.addHeader("Access-Control-Expose-Headers" , "Content-Disposition");
        response.setHeader("Content-Disposition" , "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length" , "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}