package com.ruoyi.framework.config.dialect;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;

import com.ruoyi.project.system.dict.service.DictTypeService;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 自定义单选框标签（根据字典加载）
 */
public class RyRadioDictTag extends AbstractElementTagProcessor {
    // 标签名
    private static final String TAG_NAME = "radioDict";
    // 优先级
    private static final int PRECEDENCE = 10000;

    public RyRadioDictTag(String dialectPrefix) {
        super(
                // 此处理器将仅应用于HTML模式
                TemplateMode.HTML,
                // 要应用于名称的匹配前缀
                dialectPrefix,
                // 标签名称：匹配此名称的特定标签
                TAG_NAME,
                // 将标签前缀应用于标签名称
                true,
                // 无属性名称：将通过标签名称匹配
                null,
                // 没有要应用于属性名称的前缀
                false,
                // 优先(内部方言自己的优先)
                PRECEDENCE
        );
    }

    /**
     * 处理自定义标签 DOM 结构
     * @param context 模板页上下文
     * @param tag     待处理标签
     * @param handler 元素标签结构处理器
     */
    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler handler) {
    	//获取 Spring上下文
        ApplicationContext applicationContext = SpringContextUtils.getApplicationContext(context);
        //获取标签的属性值
        String dictType = tag.getAttributeValue("dictType");
        String name = tag.getAttributeValue("name");
        String value = tag.getAttributeValue("value");
        String cssClass = tag.getAttributeValue("class");
        String required = tag.getAttributeValue("required");

        //获取字典service的bean
        DictTypeService dictDataService = applicationContext.getBean(DictTypeService.class);
        //查询数据库
        List<Map<String, Object>> dictDataList = dictDataService.selectDictItemList(dictType);

        StringBuilder html = new StringBuilder();
        html.append("");

        //添加option
        int i = 0;
        String id = "";
        String dictValue = "";
    	for (Map<String, Object> map : dictDataList) {
            id = name+"_"+i;
        	dictValue = MapUtil.getStr(map, "dict_value");

        	html.append("<label class=\""+cssClass+"\"> <input type=\"radio\" name=\""+name+"\" value=\""+dictValue+"\"");
            if(StrUtil.isNotBlank(required)) {
            	html.append(" required=\""+required+"\"");
            }
            if (StrUtil.isNotBlank(value) && dictValue.equals(value)) {//若传入的value值不为空，则根据value值匹配选项值
        		html.append(" checked=\"checked\"");
			}
        	else if (StrUtil.isBlank(value) && "Y".equals(MapUtil.getStr(map, "is_default"))) {//若传入的value值为空，则根据字典的“是否默认”自动选中选项
        		html.append(" checked=\"checked\"");
        	}
            html.append("> "+MapUtil.getStr(map, "dict_label")+" </label>");
        	i++;
        }

        handler.replaceWith(html.toString(), false);
    }
}