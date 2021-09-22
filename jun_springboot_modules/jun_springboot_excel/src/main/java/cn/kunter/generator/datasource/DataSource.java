package cn.kunter.generator.datasource;

import cn.kunter.generator.datasource.enums.SourceType;
import cn.kunter.generator.entity.Table;
import cn.kunter.generator.exception.GeneratorException;

import java.util.List;

/**
 * 数据源
 * @author nature
 * @version 1.0 2021/7/20
 */
public interface DataSource {

    List<Table> getTables() throws GeneratorException;

    SourceType getSourceType();

}
