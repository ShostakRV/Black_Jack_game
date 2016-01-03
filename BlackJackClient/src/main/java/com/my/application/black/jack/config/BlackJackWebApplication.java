package com.my.application.black.jack.config;

import com.my.application.black.jack.config.security.SecurityUserDetailsService;
import com.my.application.black.jack.init.TestDataInitializer;
import com.my.application.black.jack.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Developer: Roman Shostak
 * Date: 08-Oct-15.
 */

@Configuration

@EnableAutoConfiguration
//@EnableJpaRepositories(basePackages = "com.my.application.black.jack.dao")
//@EntityScan(basePackages = {"com.my.application.black.jack.model"})
@ComponentScan(basePackages = {"com.my.application.black.jack"})
public class BlackJackWebApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(BlackJackWebApplication.class, args);
    }

    @Bean(initMethod = "init")
    public TestDataInitializer initTestData() {
        return new TestDataInitializer();
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(BlackJackWebApplication.class);//, ServerConfig.class, AppSecurityConfig.class
    }


}
