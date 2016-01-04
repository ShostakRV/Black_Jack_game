package com.my.application.black.jack.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Developer: Roman Shostak
 * Date: 08-Oct-15.
 */


public class BlackJackWebApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
//        if (ArrayUtils.isEmpty(args)) {
//        args = new String[]{"-Dspring.profiles.active=" + TestConfig.TEST_PROFILE};
//        }
        ConfigurableApplicationContext app = SpringApplication.run(RootConfig.class, args);
//        app.getEnvironment().addActiveProfile("test");
    }


//    @Override
//    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
//        application.profiles(TEST_PROFILE);
//        //TestConfig.class,
//        //ServerConfig.class, AppSecurityConfig.class
//        return application.sources(RootConfig.class);
//    }

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.getEnvironment().setActiveProfiles(TestConfig.TEST_PROFILE);
//
//        servletContext.addListener(new ContextLoaderListener(rootContext));
//
//                super.onStartup(servletContext);
//    }
}
