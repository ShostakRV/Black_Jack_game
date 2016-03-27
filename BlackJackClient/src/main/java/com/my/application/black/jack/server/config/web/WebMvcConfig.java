package com.my.application.black.jack.server.config.web;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created: Shostak Roman
 * Date: 05.01.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.my.application.black.jack.client"})
public class WebMvcConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter{
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"};


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }
//    @Override

//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//    }

//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer conf) {
//    }
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        UrlBasedViewResolverRegistration urlBasedViewResolverRegistration = registry.jsp();
//        System.out.print("");
//    }
}