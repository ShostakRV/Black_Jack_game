package com.my.application.black.jack;


import com.my.application.black.jack.config.IntegrationTestConfig;
import com.my.application.black.jack.config.ServerConfig;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * Creator: Shostak Roman
 * Date: 02.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({@ContextConfiguration(classes = {ServerConfig.class, IntegrationTestConfig.class})})
public class SpringIntegrationTest {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserService userService;

    @Test
    public void test1() {

        List<User> users = userService.getList();
        assertEquals(0, users.size());
    }


    @Test
    public void test2() {
        userService.save(new User("Test", "Password"));
        List<User> users = userService.getList();
        assertEquals(1, users.size());
    }

}

