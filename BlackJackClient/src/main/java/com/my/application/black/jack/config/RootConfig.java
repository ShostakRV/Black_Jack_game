package com.my.application.black.jack.config;

import com.my.application.black.jack.config.security.AppSecurityConfig;
import com.my.application.black.jack.config.web.WebMvcConfig;
import com.my.application.black.jack.init.TestDataInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created: Shostak Roman
 * Date: 04.01.2016.
 */
//@Configuration

@SpringBootApplication( scanBasePackageClasses = {ServerConfig.class, AppSecurityConfig.class, WebMvcConfig.class})
@EnableTransactionManagement
//@Profile(value = "Test")
public class RootConfig {
    @Bean(initMethod = "init")
    public TestDataInitializer initTestData() {
        return new TestDataInitializer();
    }
}
