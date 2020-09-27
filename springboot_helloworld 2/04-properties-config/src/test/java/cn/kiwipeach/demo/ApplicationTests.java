package cn.kiwipeach.demo;

import cn.kiwipeach.demo.bean.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private SmallStudent smallStudent;

    @Autowired
    private MiddleStudent middleStudent;

    @Autowired
    private HighStudent highStudent;

    @Autowired
    private CollegeStudent collegeStudent;

    @Autowired
    private GraduateStudent graduateStudent;

    /**
     * 测试1:@ConfigurationProperties注解注入属性值
     */
    @Test
    public void testConfigurationProperties() {
        System.out.println(smallStudent);
        System.out.println(smallStudent.getTags());
    }

    /**
     * 测试2:@Value 注解注入属性值
     */
    @Test
    public void testValue() {
        System.out.println(middleStudent);
    }

    /**
     * 测试3:@PropertySource注解指定配置文件进行属性注入
     */
    @Test
    public void testPropertySource() {
        System.out.println(highStudent);
    }

    /**
     * 测试4：测试@ImportResource注解注入spring的xml文件中的bean
     */
    @Test
    public void testImportResource() {
        System.out.println(collegeStudent);
    }

    /**
     * 测试5:测试@Bean注解想ioc容器中添加bean
     */
    @Test
    public void testBean(){
        System.out.println(graduateStudent);
    }

}
