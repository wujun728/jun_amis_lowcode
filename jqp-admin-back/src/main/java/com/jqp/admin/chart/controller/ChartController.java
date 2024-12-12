package com.jqp.admin.chart.controller;

import cn.hutool.json.JSONUtil;
import com.jqp.admin.chart.data.Chart;
import com.jqp.admin.common.config.SessionContext;
import com.jqp.admin.db.service.JdbcService;
import com.jqp.admin.page.service.PageService;
import com.jqp.admin.util.TemplateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/chart")
public class ChartController {
    @Resource
    JdbcService jdbcService;

    @Resource
    PageService pageService;

    @RequestMapping("/{code}")
    public String page(Model model, @PathVariable("code") String code){
        model.addAttribute("js","/admin/chart/js/"+code+".js?_rt="+System.currentTimeMillis());
        return "page";
    }

    @RequestMapping(value="/js/{code}.js",produces = "text/javascript; charset=utf-8")
    @ResponseBody
    public String js(@PathVariable("code") String code,HttpServletResponse response){
        response.addHeader("Cache-Control","no-store");
        Chart chart = jdbcService.findOne(Chart.class, "code", code);
        String config = chart.getConfig();
        Map<String,Object> params = new HashMap<>();
        SessionContext.putUserSessionParams(params);
        if(StringUtils.isNotBlank(chart.getQuerysql())){
            String[] arr = chart.getQuerysql().split(";");
            for(int i=0;i<arr.length;i++){
                String sql = arr[i];
                if(StringUtils.isBlank(sql)){
                    continue;
                }
                sql = sql.replaceAll(";","");
                sql = pageService.getQuerySql(sql);

                List<Map<String, Object>> maps = jdbcService.find(sql);
                params.put("data"+i, JSONUtil.toJsonPrettyStr(maps));
            }
        }
        config = TemplateUtil.getValue(config,params);
        return config;
    }
}
