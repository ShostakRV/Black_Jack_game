package com.my.application.black.jack.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Developer: Roman Shostak
 * Date: 08-Oct-15.
 */

@SpringBootApplication
public class BlackJackWebApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        //TODO run with: --spring.profiles.active=test
        SpringApplication.run(BlackJackWebApplication.class, args);
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
