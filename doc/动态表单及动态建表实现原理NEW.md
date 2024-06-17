## [动态表单及动态建表实现原理](http://www.blogjava.net/limq/archive/2009/09/19/295690.html)

  1 应用场景
 项目中往往需要动态的创建一个表单，或者添加一个新的数据模板，这时候因为需要在运行时动态的创建表以及动态的维护表字段甚至表关系 使得普通java解决方案变得困难重重。 

  2 实现工具Hibernate + Spring + Groovy +FreemarkerHibernate 作用很简单负责创建数据库表这样可以避免我们自己去写复杂的sql和判断。Spring 作为桥梁起到连接纽带的作用。Groovy做为动态语言，在项目运行时根据模板创建访问数据库，或者控制层代码。Freamker 可以根据提前定义好的模板生成 hibernate配置文件，以及Groovy代码。   3 实现原理 首先创建Form 和 FromAttribute 两张表关系一对多。Form表记录表单的名称，类别，甚至是作为在动态生成表单时的css样式信息。FromAttribute记录表单字段信息，如名称，类别等。有了表单以及表单项的信息后就可以创建数据库表了。测试代码：
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedBlockStart.gif)public void testGenerator() {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    Form form = formService.getAll().get(0);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    List<FormAttribute> list = formAttributeService
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)        .getAttributeListByFormId(form.getId());
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    form.setFormAttributeList(list);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    DbGenerator dg = new DbGenerator(form, dataSource);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    dg.generator();
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedBlockEnd.gif)  }
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)DbGenerator
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import java.io.IOException;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import java.io.StringWriter;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import java.io.Writer;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import java.sql.SQLException;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import java.util.HashMap;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import java.util.Map;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import java.util.Properties;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import javax.sql.DataSource;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import org.hibernate.tool.hbm2ddl.SchemaExport;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import org.slf4j.Logger;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import org.slf4j.LoggerFactory;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import freemarker.template.Configuration;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import freemarker.template.Template;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import freemarker.template.TemplateException;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedBlockStart.gif)public class DbGenerator {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)  
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)  private DataSource dataSource;
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)  protected Map root = new HashMap();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)  private static Logger log = LoggerFactory.getLogger(FormGenerator.class);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)  protected String path;
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)  protected String packageName;
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)  private Form form;
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)  protected Configuration getConfig(String resource) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    Configuration cfg = new Configuration();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    cfg.setDefaultEncoding("UTF-8");
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    cfg.setClassForTemplateLoading(this.getClass(), resource);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    return cfg;
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)  }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)  public DbGenerator(Form form ,DataSource dataSource) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    this.form = form;
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    this.dataSource = dataSource;
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)  }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)  public void generator() {
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    if(null == form.getFormAttributeList() || form.getFormAttributeList().size() == 0){
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      return ;
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)    }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    Template t;
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    try {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      t = getConfig("/template").getTemplate("hibernate.ftl");
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      Writer out = new StringWriter();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      t.process(getMapContext(), out);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      String xml = out.toString();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      createTable(xml);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      log.debug(xml);
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    } catch (IOException e) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      e.printStackTrace();
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    } catch (TemplateException e) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      e.printStackTrace();
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)    }
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)  }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)  @SuppressWarnings("unchecked")
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)  Map getMapContext() {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    root.put("entity", form);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    return root;
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)  }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)  public void createTable(String xml) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    org.hibernate.cfg.Configuration conf = new org.hibernate.cfg.Configuration();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    conf.configure("/hibernate/hibernate.cfg.xml");
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    Properties extraProperties = new Properties();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    extraProperties.put("hibernate.hbm2ddl.auto", "create");
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    conf.addProperties(extraProperties);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    conf.addXML(xml);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    SchemaExport dbExport;
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    try {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      dbExport = new SchemaExport(conf, dataSource.getConnection());
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      // dbExport.setOutputFile(path);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      dbExport.create(false, true);
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    } catch (SQLException e) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      // TODO Auto-generated catch block
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      e.printStackTrace();
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)    }
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)  }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedBlockEnd.gif)}
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedBlockStart.gif)class hibernateGenerator {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedBlockEnd.gif)}hibernate.ftl
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)<?xml version="1.0" encoding="UTF-8"?>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)<!DOCTYPE hibernate-mapping 
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif) PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)     "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)<hibernate-mapping>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  <class
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    name="${entity.name}"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    table="`${entity.tableName}`"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    dynamic-update="false"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    dynamic-insert="false"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    select-before-update="false"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    optimistic-lock="version">
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    <id
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      name="id"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      column="id"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      type="java.lang.String"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      unsaved-value="null">
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      <generator class="uuid" />
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    </id>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    <#if entity.formAttributeList?exists>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      <#list entity.formAttributeList as attr>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)        <#if attr.name == "id">        
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)        <#else>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    <property
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      name="${attr.name}"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      type="java.lang.String"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      update="true"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      insert="true"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      access="property"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      column="`${attr.columnName}`"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      length="${attr.length}"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      not-null="false"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      unique="false"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    />
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)        </#if>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)      </#list>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    </#if>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  </class>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)</hibernate-mapping>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)hibernate.cfg.xml
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)<!DOCTYPE hibernate-configuration
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)<hibernate-configuration>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)<session-factory>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)    <property name="dialect">org.hibernate.dialect.SQLServerDialect</property>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  <property name="connection.driver_class">net.sourceforge.jtds.jdbc.Driver</property>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  <property name="connection.url">jdbc:jtds:sqlserver://127.0.0.1:1433;databasename=struts;SelectMethod=cursor</property>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  <property name="connection.username">sa</property>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  <property name="connection.password">sa</property>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  <property name="show_sql">true</property>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  <property name="hibernate.hbm2ddl.auto">update</property>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)<!-- 
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  <mapping resource="hibernate/FormAttribute.hbm.xml" />
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  <mapping resource="hibernate/Form.hbm.xml" />
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)  -->
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)</session-factory>
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)</hibernate-configuration> 创建好数据库后 就要利用groovy动态创建访问代码了：先看测试代码 再看具体实现：
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedBlockStart.gif)public void testGroovy() {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    Form form = formService.get("1");
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    List<FormAttribute> list = formAttributeService
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)        .getAttributeListByFormId(form.getId());
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    form.setFormAttributeList(list);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    FormGenerator fg = new FormGenerator(form);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    String groovycode = fg.generator();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    ClassLoader parent = getClass().getClassLoader();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    GroovyClassLoader loader = new GroovyClassLoader(parent);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    Class groovyClass = loader.parseClass(groovycode);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    GroovyObject groovyObject = null;
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    try {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      groovyObject = (GroovyObject) groovyClass.newInstance();
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    } catch (InstantiationException e) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      e.printStackTrace();
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    } catch (IllegalAccessException e) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      e.printStackTrace();
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)    }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    // map中key为formAttribute中描述该表单字段在数据库中的名称c_columnName
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    // 具体情况根据formAttribute而定
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    Map map = new HashMap();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    map.put("name", "limq");
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    // 调用insert方法插入数据
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    int c = (Integer) groovyObject.invokeMethod("insert", map);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    // 调用getAll方法获得所有动态表中的数据
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    Object o = groovyObject.invokeMethod("getAll", null);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    List list2 = (List) o;
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    Object obj = list2.get(0);
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    try {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      String tname = (String) BeanUtils.getDeclaredProperty(obj, "name");
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      System.out.println(tname);
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    } catch (IllegalAccessException e) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      e.printStackTrace();
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    } catch (NoSuchFieldException e) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      e.printStackTrace();
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)    }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    // 调用search方法查询动态表
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    List<Map> returnList = (List) groovyObject.invokeMethod("search", map);
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    for (Map map2 : returnList) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      // 同理此处根据FromAttribute而定
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      System.out.println(map2.get("id"));
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      System.out.println(map2.get("name"));
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      System.out.println(map2.get("type"));
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)    }
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedBlockEnd.gif)  }FormGenerator ： 创建访问数据库Groovy代码
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedBlockStart.gif)public class FormGenerator {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)  protected Map root = new HashMap();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)  private static Logger log = LoggerFactory.getLogger(FormGenerator.class);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    protected String path ;
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    protected String packageName ;
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    private Form form ; 
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    protected Configuration getConfig(String resource) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)       Configuration cfg = new Configuration();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      cfg.setDefaultEncoding("UTF-8");
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      cfg.setClassForTemplateLoading(this.getClass(), resource);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      return cfg;
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)    }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    public FormGenerator(Form form){
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      this.form = form;
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)    }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    public String generator(){
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      String returnstr = null;
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      Template t;
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)      try {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)        t = getConfig("/template").getTemplate("FormService.ftl");
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)        //Writer out = new OutputStreamWriter(new FileOutputStream(new File(path)),"UTF-8");
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)        Writer out = new StringWriter();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)        t.process(getMapContext(), out);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)        returnstr = out.toString();
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)        log.debug(returnstr);
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)      } catch (IOException e) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)        e.printStackTrace();
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)      } catch (TemplateException e) {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)        e.printStackTrace();
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)      }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      return returnstr;
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)    }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    @SuppressWarnings("unchecked")
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    Map getMapContext() {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      root.put("entity", form);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      root.put("insert", SqlHelper.buildInsertStatement(form));
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      root.put("update", SqlHelper.buildUpdateStatement(form));
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      root.put("insertParameter", SqlHelper.buildInsertparameter(form));
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      root.put("updateParameter", SqlHelper.buildUpdateparameter(form));
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      root.put("delete", SqlHelper.buildDeleteStatement(form));
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      root.put("query", SqlHelper.buildQueryStatement(form));  
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)      return root;
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)    }
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedBlockEnd.gif)}FormService.ftl
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import java.sql.ResultSet
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import java.sql.SQLException
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import java.sql.Types 
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import org.springframework.jdbc.core.RowMapper
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import org.springframework.jdbc.core.RowMapperResultSetExtractor
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import com.glnpu.sige.core.dao.DataSourceFactory
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import org.apache.commons.lang.builder.ToStringBuilder;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)import org.apache.commons.lang.builder.ToStringStyle;
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedBlockStart.gif)class ${entity.name?cap_first}Dao {
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)   def insert = '${insert}'
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)   def delete = '${delete}'
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)   def update = '${update}'
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)   def int insert( entity){
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    def Object[] params = [${insertParameter}]
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    <#assign size = entity.formAttributeList?size/>
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    def int[] types=[<#list 1..size+1 as p>Types.VARCHAR,<#rt/></#list>]
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    return DataSourceFactory.getJdbcTemplate().update(insert, params, types)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)  }
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)   def int update( entity){
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    def Object[] params = [${updateParameter}]
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    return DataSourceFactory.getJdbcTemplate().update(update, params)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)  }
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)   def int delete(String entityId){
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    def Object[] params =[entityId]
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    return DataSourceFactory.getJdbcTemplate().update(delete, params)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)  }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)  def search(entity){
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockStart.gif)    ${query}
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    println(query);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    return DataSourceFactory.getJdbcTemplate().queryForList(query);
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)    
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedSubBlockEnd.gif)  }
![img](http://www.blogjava.net/Images/OutliningIndicators/InBlock.gif)  
![img](http://www.blogjava.net/Images/OutliningIndicators/ExpandedBlockEnd.gif)}
![img](http://www.blogjava.net/Images/OutliningIndicators/None.gif) 
  以上代码示意了如何利用 freemarker 生成 Groovy 和 hibernate 相关代码，以及如何利用Groovy动态的对数据库进行创建和增删改查操作，了解以上的原理后就可以方便的在运行时利用freemarker生成表示层页面以及代码来进行展示。