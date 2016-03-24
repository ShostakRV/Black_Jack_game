package com.my.application.black.jack.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Developer: Roman Shostak
 * Date: 08-Oct-15.
 */

@Configuration
@ConditionalOnClass(value = {RootConfig.class})
@EnableAutoConfiguration
@ComponentScan("com.my.application.black.jack")
//@SpringBootApplication

public class BlackJackWebApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        //TODO run with: --spring.profiles.active=test
        SpringApplication.run(BlackJackWebApplication.class, "--spring.profiles.active=test");
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        application.profiles("test");
        return application.sources(RootConfig.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }
}
