package com.my.application.black.jack.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Developer: Roman Shostak
 * Date: 08-Oct-15.
 */

//@Configuration
//@ConditionalOnClass(value = {RootConfig.class, ServerConfig.class, WebMvcConfig.class, AppSecurityConfig.class})
//@EnableAutoConfiguration
//@ComponentScan("com.my.application.black.jack.client")
@SpringBootApplication
public class BlackJackWebApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        //TODO run with: --spring.profiles.active=test
        SpringApplication.run( BlackJackWebApplication.class, "--spring.profiles.active=test");
    }
    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application) {
        application.profiles( "test" );
        return application.sources(BlackJackWebApplication.class);
    }

//    @Override
//    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
//        application.profiles("test");
//        return application.sources(RootConfig.class);
//    }
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//    }
}
