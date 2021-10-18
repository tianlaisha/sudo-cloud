package sudo.client_user;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/1223:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Slf4j
public class TestSODU {

    @Before
    public void init(){
        System.out.println("开始测试");
    }

    @Test
    public void testOne(){
        log.error("粑粑=======================================================");
    }

    @After
    public void after(){
        System.out.println("测试结束");
    }
}
