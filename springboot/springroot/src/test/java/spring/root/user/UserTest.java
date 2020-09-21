package spring.root.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.root.user.dao.UserDao;
import com.spring.root.user.entity.User;

import spring.root.AppTest;


public class UserTest extends AppTest{
	
	@Autowired  
    private UserDao userDao;  
      
    @Test  
    public void testInsert(){  
        User user = new User();  
        user.setName("系统管理员");  
        user.setLoginName("admin");
        user.setPassword("admin");
        userDao.insert(user);  
        System.out.println("插入用户信息-->"+user.getName());  
    }  
}
