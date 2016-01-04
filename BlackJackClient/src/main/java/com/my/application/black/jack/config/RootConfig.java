package com.my.application.black.jack.config;

import com.my.application.black.jack.config.root.AppSecurityConfig;
import com.my.application.black.jack.init.TestDataInitializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created: Shostak Roman
 * Date: 04.01.2016.
 */
//@Configuration

@SpringBootApplication( scanBasePackageClasses = {ServerConfig.class, AppSecurityConfig.class}, scanBasePackages = "com.my.application.black.jack.controller")
//@EnableAutoConfiguration
//@EnableJpaRepositories(basePackages = "com.my.application.black.jack.dao")
//@EntityScan(basePackages = {"com.my.application.black.jack.model"})
//@Profile(value = "Test")
//@ComponentScan(basePackages = {"com.my.application.black.jack"})
public class RootConfig {
    @Bean(initMethod = "init")
    public TestDataInitializer initTestData() {
        return new TestDataInitializer();
    }

    @Bean(name = "dataSource")
//    @Profile(TestConfig.TEST_PROFILE)
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(org.hsqldb.jdbcDriver.class.getName());
        dataSource.setUrl("jdbc:hsqldb:mem:mydb");
        dataSource.setUsername("sa");
        dataSource.setPassword("jdbc:hsqldb:mem:mydb");
        return dataSource;
    }
}
