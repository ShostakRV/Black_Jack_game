package com.my.application.black.jack.config.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created: Shostak Roman
 * Date: 05.01.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.my.application.black.jack.controller"})
public class WebMvcConfig {
}
