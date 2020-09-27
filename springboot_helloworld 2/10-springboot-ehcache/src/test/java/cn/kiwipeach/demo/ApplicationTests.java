package cn.kiwipeach.demo;

import cn.kiwipeach.demo.dao.SysUserDao;
import cn.kiwipeach.demo.domain.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private SysUserDao sysUserDao;

    @Test
    public void contextLoads() {
        SysUser sysUser = sysUserDao.selectById("1001");
        System.out.println(sysUser);
    }

}
