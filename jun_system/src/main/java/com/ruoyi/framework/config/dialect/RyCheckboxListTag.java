package com.ruoyi.framework.config.dialect;

import com.ruoyi.common.utils.StrUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * 自定义多选框标签（根据传入list加载）
 */
public class RyCheckboxListTag extends AbstractElementTagProcessor {
    // 标签名
    private static final String TAG_NAME = "checkboxList";
    // 优先级
    private static final int PRECEDENCE = 10000;

    public RyCheckboxListTag(String dialectPrefix) {
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
    	StringBuilder html = new StringBuilder("");
        //获取标签的属性值
        String name = tag.getAttributeValue("name");
        String cssClass = tag.getAttributeValue("class");
        String required = tag.getAttributeValue("required");
        String itemLabel = tag.getAttributeValue("itemLabel");
        String itemValue = tag.getAttributeValue("itemValue");
        String value = tag.getAttributeValue("value");
        String delims = tag.getAttributeValue("delims");
        String list = tag.getAttributeValue("list");

        //传入的分隔符为空或未传入，则默认为逗号
        delims = StrUtil.isBlank(delims) ? "," : delims;

        if(StrUtil.isNotBlank(itemLabel) && StrUtil.isNotBlank(itemValue) && StrUtil.isNotBlank(list)) {
        	list = EscapeUtil.unescapeHtml4(list);
        	//字符串转JSONArray
            JSONArray array = JSONUtil.parseArray(list);

            if(!JSONUtil.isNull(array) && array.size() > 0) {
            	String iValue = "";
            	for(int i=0;i<array.size();i++) {
            		JSONObject obj = array.getJSONObject(i);
            		iValue = obj.getStr(itemValue);

            		html.append("<label class=\""+cssClass+"\"><input type=\"checkbox\" name=\""+name+"\" value=\""+iValue+"\"");
                    if(StrUtil.isNotBlank(required)) {
                    	html.append(" required=\""+required+"\"");
                    }
                    //判断是否选中
                    if(StrUtils.containsAny(value, delims, iValue)) {
                        html.append(" checked=\"checked\"");
                    }
                    html.append("/>"+obj.getStr(itemLabel)+"</label>");
            	}
            }
        }
    	handler.replaceWith(html.toString(), false);
    }
}