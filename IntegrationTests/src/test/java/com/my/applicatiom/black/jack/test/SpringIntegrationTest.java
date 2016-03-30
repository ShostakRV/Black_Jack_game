package com.my.applicatiom.black.jack.test;


import com.my.applicatiom.black.jack.test.config.IntegrationTestConfig;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.server.config.ServerConfig;
import com.my.application.black.jack.server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.junit.Assert.assertEquals;

//import org.springframework.web.context.WebApplicationContext;

/**
 * Creator: Shostak Roman
 * Date: 02.01.2016.
 */
@RunWith( SpringJUnit4ClassRunner.class )
//@WebAppConfiguration
@ActiveProfiles( "test" )
@ContextHierarchy( {@ContextConfiguration( classes = {ServerConfig.class, IntegrationTestConfig.class} )} )//, IntegrationTestConfig.class
@Transactional
public class SpringIntegrationTest {

//	@Autowired
//	private WebApplicationContext applicationContext;
	@Autowired
	private UserService userService;
	//    private MockMvc mockMvc;

	@PostConstruct
	private void init() {
		//        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}


	@Test
	public void test1() throws Exception {
		//        mockMvc.perform(MockMvcRequestBuilders.get("/test").accept(MediaType.APPLICATION_JSON))
		//                .andExpect(status().isOk())
		//                .andExpect(content().string("Greetings from Spring Boot!"));

		List<User> users = userService.getList();
		assertEquals( 1, users.size() );
	}


	@Test
	public void test2() {
		userService.save( new User( "Test", "Password" ) );
		List<User> users = userService.getList();
		assertEquals( 2, users.size() );
	}

}

