package com.my.application.black.jack.run.config;

import com.my.application.black.jack.run.config.root.TestConfig;
import com.my.application.black.jack.run.config.security.AppSecurityConfig;
import com.my.application.black.jack.run.config.web.WebMvcConfig;
import com.my.application.black.jack.server.config.ServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Developer: Roman Shostak
 * Date: 08-Oct-15.
 */

@SpringBootApplication( scanBasePackageClasses = {
	ServerConfig.class, WebMvcConfig.class, AppSecurityConfig.class, TestConfig.class
} )
public class BlackJackWebApplication extends SpringBootServletInitializer {

	public static void main( String[] args ) {
		//TODO run with: --spring.profiles.active=test
		SpringApplication.run( BlackJackWebApplication.class, "--spring.profiles.active=test" );
	}

	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder application ) {
		application.profiles( "test" );
		return application.sources( BlackJackWebApplication.class );
	}
}
