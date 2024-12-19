package com.ruoyi.framework.config.dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Thymeleaf 自定义标签
 */
public class RyDialect extends AbstractProcessorDialect {
    // 定义方言名称
    private static final String DIALECT_NAME = "Ry Dialect";
    public RyDialect() {
        // 设置自定义方言与“方言处理器”优先级相同
        super(DIALECT_NAME, "ry", StandardDialect.PROCESSOR_PRECEDENCE);
    }
    /**
     * 元素处理器
     * @param dialectPrefix 方言前缀
     * @return
     */
    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> processors = new HashSet<IProcessor>();
        //加自定义标签
        processors.add(new RySelectTag(dialectPrefix));        //下拉框标签（根据传入list加载）
        processors.add(new RySelectDictTag(dialectPrefix));    //下拉框标签（根据字典加载）
        processors.add(new RyRadioTag(dialectPrefix));         //单选框标签（单个单选按钮）
        processors.add(new RyRadioListTag(dialectPrefix));     //单选框标签（根据传入list加载）
        processors.add(new RyRadioDictTag(dialectPrefix));     //单选框标签（根据字典加载）
        processors.add(new RyCheckboxTag(dialectPrefix));      //复选框标签（单个复选框）
        processors.add(new RyCheckboxListTag(dialectPrefix));  //复选框标签（根据传入list加载）
        processors.add(new RyCheckboxDictTag(dialectPrefix));  //复选框标签（根据字典加载）
        processors.add(new RySwitchTag(dialectPrefix));        //switch标签
        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    }
}