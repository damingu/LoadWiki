package com.web.blog.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.web.blog.interceptor.LoginInterceptor;

@Configuration
public class LoginConfig implements WebMvcConfigurer{

	@Autowired
	private LoginInterceptor loginItc;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.allowedHeaders("*")
		.exposedHeaders("auth-token");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//      registry.addInterceptor(loginItc)
//      .addPathPatterns("/user/**")
//      .excludePathPatterns(Arrays.asList("/user/login", "/user/join"));
//		registry.addInterceptor(loginItc)
//			.addPathPatterns("/user/login")
//			//.excludePathPatterns(Arrays.asList("/user/login", "/user/join", "/user/**"));
//			.excludePathPatterns(Arrays.asList("/user/**"));
	}
	
	
}
