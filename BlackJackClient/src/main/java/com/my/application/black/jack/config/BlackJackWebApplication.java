package com.my.application.black.jack.config;

import com.my.application.black.jack.config.root.TestConfig;
import com.my.application.black.jack.init.TestDataInitializer;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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

    public static final String TEST_PROFILE = TestConfig.TEST_PROFILE;

    public static void main(String[] args) {
        if (ArrayUtils.isEmpty(args)) {
//            args = new String[]{"-Dspring.profiles.active=" + TEST_PROFILE};
        }
        SpringApplication.run(BlackJackWebApplication.class, args);
    }

    @Bean(initMethod = "init")
    public TestDataInitializer initTestData() {
        return new TestDataInitializer();
    }

    @Bean(name = "datasource")
//    @Profile(TestConfig.TEST_PROFILE)
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(org.hsqldb.jdbcDriver.class.getName());
        dataSource.setUrl("jdbc:hsqldb:mem:mydb");
        dataSource.setUsername("sa");
        dataSource.setPassword("jdbc:hsqldb:mem:mydb");
        return dataSource;
    }


    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        application.profiles(TEST_PROFILE);
        //TestConfig.class,
        //ServerConfig.class, AppSecurityConfig.class
        return application.sources( BlackJackWebApplication.class);
    }


}
