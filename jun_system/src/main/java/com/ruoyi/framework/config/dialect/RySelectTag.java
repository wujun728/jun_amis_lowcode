package com.ruoyi.framework.config.dialect;

import com.ruoyi.common.utils.StrUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * 自定义下拉框标签（根据传入list加载）
 */
public class RySelectTag extends AbstractElementTagProcessor {
    // 标签名
    private static final String TAG_NAME = "select";
    // 优先级
    private static final int PRECEDENCE = 10000;

    public RySelectTag(String dialectPrefix) {
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
     * @param context  模板页上下文
     * @param tag      待处理标签
     * @param handler  元素标签结构处理器
     */
    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler handler) {
        String headLabel = "";
        String headValue = "";
        String itemLabel = "";
        String itemValue = "";
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

            	if(!StrUtil.containsAnyIgnoreCase(attrName, "list", "headLabel", "headValue", "itemLabel", "itemValue", "value", "delims")) {
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
        		else if("itemLabel".equals(attrName)) {
                    itemLabel = attrValue;
        		}
        		else if("itemValue".equals(attrName)) {
                    itemValue = attrValue;
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

        //传入的分隔符为空或未传入，则默认为逗号
        delims = StrUtil.isBlank(delims) ? "," : delims;

        //添加头部option
        if(StrUtil.isNotBlank(headLabel)) {
        	html.append("<option value=\""+headValue+"\"");
            //判断是否选中
            if(StrUtils.containsAny(value, delims, headValue)) {
                html.append(" selected=\"selected\"");
            }
            html.append(">"+headLabel+"</option>");
        }

        //获取标签的属性list的值
        String list = tag.getAttributeValue("list");
        if(StrUtil.isNotBlank(itemLabel) && StrUtil.isNotBlank(itemValue) && StrUtil.isNotBlank(list)) {
        	list = EscapeUtil.unescapeHtml4(list);
        	//字符串转JSONArray
            JSONArray array = JSONUtil.parseArray(list);

            if(!JSONUtil.isNull(array) && array.size() > 0) {
            	String iValue = "";
            	for(int i=0;i<array.size();i++) {
            		JSONObject obj = array.getJSONObject(i);
                    iValue = obj.getStr(itemValue);
                    //添加选项option
            		html.append("<option value=\""+iValue+"\"");
            		//判断是否选中
                    if(StrUtils.containsAny(value, delims, iValue)) {
                        html.append(" selected=\"selected\"");
                    }
                	html.append(">"+obj.getStr(itemLabel)+"</option>");
            	}
            }
        }
    	html.append("</select>");
    	handler.replaceWith(html.toString(), false);
    }
}