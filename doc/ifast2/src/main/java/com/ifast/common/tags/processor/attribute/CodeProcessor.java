package com.ifast.common.tags.processor.attribute;

import java.math.BigInteger;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

/**
 * 
 *  if <div iftg:code='1' /> then  <div><span>启用</span></div>
 *  if <div iftg:code='0' /> then  <div><span>停用</span></div>
 * @author amber
 *
 */
public class CodeProcessor extends AbstractAttributeTagProcessor {

	private static final String ATTR_NAME  = "code";
	private static final int    PRECEDENCE = 12000;

	public CodeProcessor(final String dialectPrefix) {
		super(TemplateMode.HTML, // This processor will apply only to HTML mode
		        dialectPrefix, // Prefix to be applied to name for matching
		        null, // No tag name: match any tag name
		        false, // No prefix to be applied to tag name
		        ATTR_NAME, // Name of the attribute that will be matched
		        true, // Apply dialect prefix to attribute name
		        PRECEDENCE, // Precedence (inside dialect's precedence)
		        true); // Remove the matched attribute afterwards

	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue,
	        IElementTagStructureHandler structureHandler) {
		final IEngineConfiguration configuration = context.getConfiguration();

		/*
		 * Obtain the Thymeleaf Standard Expression parser
		 * 获取Thymeleaf的表达式转换器
		 */
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);

		/*
		 * Parse the attribute value as a Thymeleaf Standard Expression
		 */
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);

		/*
		 * Execute the expression just parsed
		 * 使用得到的表达式，处理上下文内容，得到具体传入的参数值
		 * Demo中传入的是一个数字
		 */
		final BigInteger position = (BigInteger) expression.execute(context);

		/*
		 * Create the DOM structure that will be substituting our custom tag.
		 */
		final IModelFactory modelFactory = context.getModelFactory();
		final IModel        model        = modelFactory.createModel();
		// 根据传入的参数，修改 Tag 的具体内容。
		if (position.equals(0)) {
			model.add(modelFactory.createOpenElementTag("span", "style", "background:red"));
			model.add(modelFactory.createText(HtmlEscape.escapeHtml5("无效")));
		} else {
			model.add(modelFactory.createOpenElementTag("span"));
			model.add(modelFactory.createText(HtmlEscape.escapeHtml5("启用")));
		}
		model.add(modelFactory.createCloseElementTag("span"));

		/*
		 * Instruct the engine to replace this entire element with the specified model.
		 * 通过 structureHandler 修改tag标签的内容
		 */
		structureHandler.replaceWith(model, false);

	}

}
