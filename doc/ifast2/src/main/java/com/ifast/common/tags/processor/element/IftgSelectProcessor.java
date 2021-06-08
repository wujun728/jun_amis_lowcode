package com.ifast.common.tags.processor.element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.google.common.collect.Maps;
import com.ifast.common.base.service.DictService;
import com.ifast.common.tags.util.IftgUtil;
import com.ifast.common.tags.vo.ValueVO;
import com.ifast.common.utils.SpringContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * select 注解
 * 用法
 * <iftg:select dicType = "dic_of_sex"></iftg:select>
 * 属性 dicType是必填项
 * --情况1：当dicType = 字典中的type 时select下拉数值渲染的value
 * 为字典中的name字段，name 为字典中的name字段
 *
 *
 * --情况2：当dicType = all时候表示select下拉数值渲染的value
 * 为字典中的type字段，name 为字典中的description字段）
 *
 * 注：
 * 控件的其他属性，用户可根据需求完全自定义，如需要加上name属性和Id属性则
 * <iftg:select dicType = "dic_of_sex" name="mySelect" id="selectId"></iftg:select>
 * 
 * @author: zet
 * @date:2018/8/22
 */
@Slf4j
public class IftgSelectProcessor extends AbstractElementTagProcessor {

	private static final String ELEMENT_NAME = "select";
	private static final int    PRECEDENCE   = 300;

	private DictService dictService;
	private boolean     firstLoad = true;

	public IftgSelectProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, // This processor will apply only to HTML mode
		        dialectPrefix, // Prefix to be applied to name for matching
		        ELEMENT_NAME, // Tag name: match specifically this tag
		        true, // Apply dialect prefix to tag name
		        null, // No attribute name: will match by tag name
		        false, // No prefix to be applied to attribute name
		        PRECEDENCE); // Precedence (inside dialect's own precedence);

	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler) {
		// 初始化
		init();

		// 获取值
		String dicType = tag.getAttributeValue("dicType"); // 字典类型
		

		String defaultValue = tag.getAttributeValue("defaultValue");// 默认选中

		String        thValue       = IftgUtil.getTargetAttributeValue(context, tag, "th:value");              // 回显值
		String        defaultSelect = StringUtils.isNoneBlank(thValue) ? thValue : defaultValue;
		List<ValueVO> valueVos      = IftgUtil.getValues(dictService, dicType, new String[] { defaultSelect });

		//
		final IModelFactory modelFactory = context.getModelFactory();
		final IModel        model        = modelFactory.createModel();

		model.add(modelFactory.createOpenElementTag(ELEMENT_NAME, tag.getAttributeMap(), AttributeValueQuotes.DOUBLE, false));

		createSelectOption(modelFactory, model, valueVos);

		model.add(modelFactory.createCloseElementTag(ELEMENT_NAME));
		structureHandler.replaceWith(model, false);
		log.info("select:-> {}", model);
	}

	private void createSelectOption(IModelFactory modelFactory, IModel model, List<ValueVO> valueVos) {
		Map<String, String>  defaultAttr = Maps.newHashMap();
		defaultAttr.put("style", "display:none;");
		model.addModel(createOption(modelFactory, model, "选择类别...", defaultAttr));

		for (ValueVO option : valueVos) {
			Map<String, String> attrs = new HashMap<>();
			attrs.put("value", option.getValue());

			if (Objects.nonNull(option.getSelected()) && option.getSelected()) {
				attrs.put("selected", "selected");
			}
			//
			model.addModel(createOption(modelFactory, model, option.getName(), attrs));
		}

	}

	private IModel createOption(IModelFactory modelFactory, IModel model, String text, Map<String, String> attrs) {
		final IModel optionModel = modelFactory.createModel();
		if (null != attrs) {
			optionModel.add(modelFactory.createOpenElementTag("option", attrs, AttributeValueQuotes.DOUBLE, false));
		} else
			optionModel.add(modelFactory.createOpenElementTag("option"));
		optionModel.add(modelFactory.createText(text));
		optionModel.add(modelFactory.createCloseElementTag("option"));
		return optionModel;

	}

	private void init() {
		if (firstLoad) {
			dictService = SpringContextHolder.getBean(DictService.class);
			firstLoad   = false;
		}
	}

}
