package com.ifast.common.tags;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.ifast.common.tags.processor.attribute.CodeProcessor;
import com.ifast.common.tags.processor.element.IftgSelectProcessor;

/**
 * 自定注解处理入口
 *
 * @author: zet
 * @date:2018/8/22
 */
@Component
public class IftgDialectEntrance extends AbstractProcessorDialect  { // SpringStandardDialect {
	private static final String NAME   = "IFastTag";
	private static final String PREFIX = "iftg";

	protected IftgDialectEntrance() {
		super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		return createStandardProcessorsSet(dialectPrefix);
	}

	private Set<IProcessor> createStandardProcessorsSet(String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		// iftg:code=""
		processors.add(new CodeProcessor(PREFIX));
		// <iftg:select/> 注解
		 processors.add(new IftgSelectProcessor(PREFIX));
		return processors;
	}

 

}
