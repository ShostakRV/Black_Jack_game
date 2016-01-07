package com.my.application.black.jack.config;

import com.my.application.black.jack.config.root.AppSecurityConfig;
import com.my.application.black.jack.config.root.TestConfig;
import com.my.application.black.jack.config.root.WebMvcConfig;
import com.my.application.black.jack.init.TestDataInitializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created: Shostak Roman
 * Date: 04.01.2016.
 */
//@Configuration

@SpringBootApplication( scanBasePackageClasses = {ServerConfig.class, AppSecurityConfig.class, WebMvcConfig.class})
//@Profile(value = "Test")
public class RootConfig {
    @Bean(initMethod = "init")
    public TestDataInitializer initTestData() {
        return new TestDataInitializer();
    }
}
