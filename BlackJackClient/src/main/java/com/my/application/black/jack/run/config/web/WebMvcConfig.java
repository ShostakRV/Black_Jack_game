package com.my.application.black.jack.run.config.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.templateresolver.*;

import java.util.List;
import java.util.Optional;

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
		"classpath:/static/", "classpath:/public/" };


	@Override
	public void addViewControllers( ViewControllerRegistry registry ) {
		registry.addViewController( "/home" ).setViewName( "index" );
		registry.addViewController( "/" ).setViewName( "index" );
		registry.addViewController( "/hello" ).setViewName( "hello" );
		registry.addViewController( "/login" ).setViewName( "login" );//			8-login-form/8-login-form/index
	}

	@Bean
	public TemplateResolver getTemplateResolver() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix( "classpath:/webapp/" );
		resolver.setSuffix( ".html" );
		resolver.setTemplateMode( "HTML5" );
		return resolver;
	}


//	@Override
//	public void addResourceHandlers( ResourceHandlerRegistry registry ) {
//
//		if (!registry.hasMappingForPattern("/webjars/**")) {
//			registry.addResourceHandler("/webjars/**").addResourceLocations(
//				"classpath:/META-INF/resources/webjars/");
//		}
//		if (!registry.hasMappingForPattern("/**")) {
//			registry.addResourceHandler("/**").addResourceLocations(
//				CLASSPATH_RESOURCE_LOCATIONS);
//		}
//	}


//	@Override
//	public void addInterceptors( InterceptorRegistry registry ) { //todo local interceptor: http://www.mkyong.com/spring-mvc/spring-mvc-internationalization-example/
		//		registry.addInterceptor(new LocaleChangeInterceptor());
		//		registry.addInterceptor(new ThemeChangeInterceptor()).addPathPatterns( "/**").excludePathPatterns( "/admin/**");
		//		registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
//	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		Optional<MappingJackson2HttpMessageConverter> conv = converters.stream()
				.filter(MappingJackson2HttpMessageConverter.class::isInstance)
				.map(MappingJackson2HttpMessageConverter.class::cast)
				.findFirst();
		MappingJackson2HttpMessageConverter realC = conv.get();
		//realC.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8,MediaType.APPLICATION_FORM_URLENCODED))
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