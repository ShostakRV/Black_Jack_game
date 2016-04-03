package com.my.application.black.jack.run.config.web;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created: Shostak Roman
 * Date: 05.01.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan( basePackages = {"com.my.application.black.jack.client"} )
public class WebMvcConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
		"classpath:/META-INF/resources/", "classpath:/resources/",
		"classpath:/static/", "classpath:/public/", "classpath:/public-resources/",
		"classpath:/WEB-INF/resources"
	};


	@Override
	public void addViewControllers( ViewControllerRegistry registry ) {
		registry.addViewController( "/home" ).setViewName( "index" );
		registry.addViewController( "/" ).setViewName( "index" );
		registry.addViewController( "/hello" ).setViewName( "hello" );
		registry.addViewController( "/login" ).setViewName( "login" );//			8-login-form/8-login-form/index
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/css/**").addResourceLocations("/css/**");
		registry.addResourceHandler("/img/**").addResourceLocations("/img/**");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/**");
		registry.addResourceHandler("/sound/**").addResourceLocations("/sound/**");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/**");
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) { //todo local interceptor: http://www.mkyong.com/spring-mvc/spring-mvc-internationalization-example/
//		registry.addInterceptor(new LocaleChangeInterceptor());
//		registry.addInterceptor(new ThemeChangeInterceptor()).addPathPatterns( "/**").excludePathPatterns( "/admin/**");
//		registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
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