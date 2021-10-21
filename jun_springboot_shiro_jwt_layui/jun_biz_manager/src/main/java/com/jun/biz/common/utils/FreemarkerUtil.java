package com.jun.biz.common.utils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020/10/27 13:35
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
public class FreemarkerUtil {

    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_30);

    public static String genHtml(String code, String content, Object model) {
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate(code, content);
        CONFIGURATION.setTemplateLoader(stringLoader);
        Map<String, Object> root = new HashMap<>(2);
        root.put("model", model);
        StringWriter result = new StringWriter();
        try {
            Template template = CONFIGURATION.getTemplate(code, "utf-8");
            template.process(root, result);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException("freemarker生成静态文件失败！", e);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(genHtml("aaa", "欢迎：${model}", "zhang3"));
        System.out.println(genHtml("aaa", "欢迎：${model}", "li4"));
    }
}

