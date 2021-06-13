package hong.mybatis.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "hong.mybatis.springboot.mapper")
@SpringBootApplication
public class MapperApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(MapperApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

}
