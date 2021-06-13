package hong.mybatis.springboot.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import hong.mybatis.springboot.MapperApplication;
import hong.mybatis.springboot.model.Country;
import hong.mybatis.springboot.service.CountryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(MapperApplication.class)
public class CountryServiceTest {

	@Autowired
	private CountryService countryService;

	@Test
	public void test() {
		Country country = new Country();
		List<Country> all = countryService.getAll(country);
		for (Country c : all) {
			System.out.println(c.getCountryname());
		}
	}
}
