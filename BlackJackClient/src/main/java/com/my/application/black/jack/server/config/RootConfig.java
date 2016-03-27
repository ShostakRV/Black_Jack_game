package com.my.application.black.jack.server.config;

import com.my.application.black.jack.server.config.security.AppSecurityConfig;
import com.my.application.black.jack.server.config.web.WebMvcConfig;
import com.my.application.black.jack.client.init.TestDataInitializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created: Shostak Roman
 * Date: 04.01.2016.
 */
//@Configuration

@Configuration
@ConditionalOnClass(value = {ServerConfig.class, AppSecurityConfig.class, WebMvcConfig.class})
//        ( scanBasePackageClasses = {ServerConfig.class, AppSecurityConfig.class, WebMvcConfig.class})
@EnableTransactionManagement
//@Profile(value = "Test")
public class RootConfig {
    @Bean(initMethod = "init")
    public TestDataInitializer initTestData() {
        return new TestDataInitializer();
    }
}
