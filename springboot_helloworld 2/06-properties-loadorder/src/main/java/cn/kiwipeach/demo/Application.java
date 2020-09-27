package cn.kiwipeach.demo;

import cn.kiwipeach.demo.bean.HighStudent;
import cn.kiwipeach.demo.bean.Student;
import cn.kiwipeach.demo.utils.IocUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Import(IocUtil.class)
public class Application {
    /**
     * 多profile环境:
     * 1.在application.yml指定激活目标配置文件
     * 2.VM虚拟机参数: -Dspring.profiles.active=prod
     * 2.Environment Variables参数中(即系统环境变量中，注意添加环境变量后需要重启开发工具才能生效) : spring.profiles.active=prod
     * 3.打包jar中激活配置文件 java -jar 06-properties-loadorder-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
     */
    public static void main(String[] args) throws Exception {
        /*加载顺序：*/
        //1.命令行参数 java -jar 06-properties-loadorder-0.0.1-SNAPSHOT.jar --server.port=3333
        //2 maven参数 java -jar 06-properties-loadorder-0.0.1-SNAPSHOT.jar --server.port=3333 -Dserver.port=4444
        //3.Java系统属性（System.getProperties()）
        //System.setProperty("server.port","4444");
        //4.Java环境变量（System.getenv()）
        //addEnv("server.port","5555");
        //5.由jar包外向jar包内进行寻找，优先加载带主配置文件(application.yml)再加载（application-dev.yml或application-dev.yml）
        //6.@Configuration注解类上的@PropertySource，见cn.kiwipeach.demo.bean.HighStudent类
        //7.通过SpringApplication.setDefaultProperties指定的默认属性
        SpringApplication application = new SpringApplication(Application.class);
        Map<String, Object> defaultMap = new HashMap<>();
        defaultMap.put("server.context-path", "/kiwipeach-web");//会被@PropertySource属性覆盖
        application.setDefaultProperties(defaultMap);
        application.run(args);
        //正常启动方式
        //SpringApplication.run(Application.class, args);
        //HighStudent student = IocUtil.getBean(HighStudent.class);
        //System.out.println(student);

    }


    public static void addEnv(String key,String value) throws Exception {
        Map<String,String> myEnv = new HashMap<>();
        myEnv.putAll(System.getenv());
        myEnv.put(key,value);
        Class[] classes = Collections.class.getDeclaredClasses();
        Map<String, String> env = System.getenv();
        for (Class cl : classes) {
            if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                Field field = cl.getDeclaredField("m");
                field.setAccessible(true);
                Object obj = field.get(env);
                Map<String, String> map = (Map<String, String>) obj;
                map.clear();
                map.putAll(myEnv);
            }
        }
    }

}
