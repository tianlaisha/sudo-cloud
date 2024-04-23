package sudo.client_user;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/1823:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HelloControlerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        // 加载webapplicationcontext  进行启动springboot进行加载测试
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    // 测试userbyid  接口
    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login").param("username", "zhangsan")
                .param("password", "123456")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
