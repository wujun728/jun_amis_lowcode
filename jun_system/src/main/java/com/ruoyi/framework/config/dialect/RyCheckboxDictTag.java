package com.ruoyi.framework.config.dialect;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.utils.StrUtils;
import com.ruoyi.project.system.dict.service.DictTypeService;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;
import java.util.Map;

/**
 * 自定义多选框标签（根据字典加载）
 */
public class RyCheckboxDictTag extends AbstractElementTagProcessor {
    // 标签名
    private static final String TAG_NAME = "checkboxDict";
    // 优先级
    private static final int PRECEDENCE = 10000;

    public RyCheckboxDictTag(String dialectPrefix) {
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
        //获取 Spring上下文
        ApplicationContext applicationContext = SpringContextUtils.getApplicationContext(context);
        //获取标签的属性值
        String dictType = tag.getAttributeValue("dictType");
        String name = tag.getAttributeValue("name");
        String value = tag.getAttributeValue("value");
        String delims = tag.getAttributeValue("delims");
        String cssClass = tag.getAttributeValue("class");
        String required = tag.getAttributeValue("required");

        //传入的分隔符为空或未传入，则默认为逗号
        delims = StrUtil.isBlank(delims) ? "," : delims;

        //获取字典service的bean
        DictTypeService dictDataService = applicationContext.getBean(DictTypeService.class);
        //根据字典类型查询字典项
        List<Map<String, Object>> dictDataList = dictDataService.selectDictItemList(dictType);

        //生成html
        StringBuilder html = new StringBuilder("");
        int i = 0;
        String id = "";
        String dictValue = "";
        for (Map<String, Object> map : dictDataList) {
            id = name+"_"+i;
            dictValue = MapUtil.getStr(map, "dict_value");

            html.append("<label class=\""+cssClass+"\"><input type=\"checkbox\" name=\""+name+"\" value=\""+dictValue+"\"");
            if(StrUtil.isNotBlank(required)) {
                html.append(" required=\""+required+"\"");
            }
            //判断是否选中
            if(StrUtils.containsAny(value, delims, dictValue)) {
                html.append(" checked=\"checked\"");
            }
            html.append("/>"+MapUtil.getStr(map, "dict_label")+"</label>");

            i++;
        }
        handler.replaceWith(html.toString(), false);
    }
}