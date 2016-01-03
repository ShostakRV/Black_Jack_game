package com.my.application.black.jack.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created: Shostak Roman
 * Date: 03.01.2016.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.my.application.black.jack.dao")
@EntityScan(basePackages = {"com.my.application.black.jack.model"})
@ComponentScan(basePackages = {"com.my.application.black.jack.service", "com.my.application.black.jack.dao"})
public class ServerConfig {

}
