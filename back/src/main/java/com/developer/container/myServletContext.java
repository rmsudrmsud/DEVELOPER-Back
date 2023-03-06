package com.developer.container;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

@EnableWebMvc //WebApplicationContext컨테니어
public class myServletContext implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		WebMvcConfigurer.super.addCorsMappings(registry);
		registry.addMapping("/**")
			.allowedOrigins("http://192.168.0.20:5500")
			.allowCredentials(true)
			.allowedMethods("GET", "POST", "PUT", "DELETE");
	}

	//@Bean
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
//		cmr.setDefaultEncoding("UTF-8");
//		cmr.setMaxUploadSize(1000*1024);
//		cmr.setMaxUploadSizePerFile(100*1024);
//		return cmr;
//	}
}