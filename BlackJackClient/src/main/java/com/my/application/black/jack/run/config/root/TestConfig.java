package com.my.application.black.jack.run.config.root;

import com.my.application.black.jack.run.config.root.init.TestDataInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created: Shostak Roman
 * Date: 03.01.2016.
 */

@Profile(TestConfig.TEST_PROFILE)
@Configuration
public class TestConfig {

    static final String TEST_PROFILE = "test";

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(org.hsqldb.jdbcDriver.class.getName());
        dataSource.setUrl("jdbc:hsqldb:mem:mydb");
        dataSource.setUsername("sa");
        dataSource.setPassword("jdbc:hsqldb:mem:mydb");
        return dataSource;
    }

    @Bean(initMethod = "init")
    public TestDataInitializer initTestData() {
        return new TestDataInitializer();
    }

}
