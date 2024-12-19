package com.ruoyi.framework.config.dialect;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.StrUtils;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;

import com.ruoyi.project.system.dict.service.DictTypeService;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 自定义字典标签（根据字典加载）
 */
public class RySelectDictTag extends AbstractElementTagProcessor {
    // 标签名
    private static final String TAG_NAME = "selectDict";
    // 优先级
    private static final int PRECEDENCE = 10000;
    public RySelectDictTag(String dialectPrefix) {
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
        //获取标签的属性dictType的值
        String dictType = tag.getAttributeValue("dictType");
        if(StrUtil.isBlank(dictType)) {
        	return;
        }

        //根据字典类型查询字典项
        DictTypeService dictTypeService = applicationContext.getBean(DictTypeService.class);
        List<Map<String, Object>> dictDataList = dictTypeService.selectDictItemList(dictType);

        String headLabel = "";
        String headValue = "";
        String value = "";
        String delims = "";
        //拼接替换html
        StringBuilder html = new StringBuilder("<select");

        //获取标签所有属性
        IAttribute[] attrs = tag.getAllAttributes();
        if(ArrayUtil.isNotEmpty(attrs)) {
        	String attrName = "";
        	String attrValue = "";

        	for(IAttribute attr : attrs) {
        		attrName = attr.getAttributeCompleteName();
            	attrValue = attr.getValue();

            	if(!StrUtil.containsAnyIgnoreCase(attrName, "headLabel", "headValue", "value", "delims")) {
            		html.append(" "+attrName);

            		if(attrValue != null) {
            			html.append("=\""+attrValue+"\"");
            		}
            	}
        		else if("headLabel".equals(attrName)) {
        			headLabel = attrValue;
        		}
        		else if("headValue".equals(attrName)) {
        			headValue = attrValue;
        		}
        		else if("value".equals(attrName)) {
        			value = attrValue;
        		}
                else if("delims".equals(attrName)) {
                    delims = attrValue;
                }
        	}
        }
        html.append(">");

        //添加头部option
        if(StrUtil.isNotBlank(headLabel)) {
            html.append("<option value=\""+headValue+"\"");
            //判断是否选中
            if(StrUtils.containsAny(value, delims, headValue)) {
                html.append(" selected=\"selected\"");
            }
            html.append(">"+headLabel+"</option>");
        }

        String dictValue = "";
    	for (Map<String, Object> map : dictDataList) {
        	dictValue = MapUtil.getStr(map, "dict_value");
            //添加选项option
        	html.append("<option value=\""+dictValue+"\"");
            //判断是否选中
            if(StrUtils.containsAny(value, delims, dictValue)) {
                html.append(" selected=\"selected\"");
            }
        	else if (null == value && "Y".equals(MapUtil.getStr(map, "is_default"))) {//若传入的value值为空，则根据字典的“是否默认”自动选中选项
        		html.append(" selected=\"selected\"");
        	}
        	html.append(">"+MapUtil.getStr(map, "dict_label")+"</option>");
        }
    	html.append("</select>");
    	handler.replaceWith(html.toString(), false);
    }
}