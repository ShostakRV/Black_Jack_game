package com.my.applicatiom.black.jack;


import com.my.application.black.jack.config.BlackJackWebApplication;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Creator: Shostak Roman
 * Date: 02.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
@ContextHierarchy({@ContextConfiguration(classes = {BlackJackWebApplication.class})})//, IntegrationTestConfig.class
@Transactional
public class SpringIntegrationTest {
    @Autowired
    private WebApplicationContext applicationContext;
    @Autowired
    private UserService userService;
    private MockMvc mockMvc;

    @PostConstruct
    private void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }


    @Test
    public void test1() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/test").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Greetings from Spring Boot!"));

        List<User> users = userService.getList();
        assertEquals(1, users.size());
    }


    @Test
    public void test2() {
        userService.save(new User("Test", "Password"));
        List<User> users = userService.getList();
        assertEquals(2, users.size());
    }

}

