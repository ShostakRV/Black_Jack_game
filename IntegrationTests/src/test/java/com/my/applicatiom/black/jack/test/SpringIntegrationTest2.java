package com.my.applicatiom.black.jack.test;


import com.my.application.black.jack.server.config.BlackJackWebApplication;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
public class SpringIntegrationTest2 {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserService userService;

    @Test
    public void test1() {

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

