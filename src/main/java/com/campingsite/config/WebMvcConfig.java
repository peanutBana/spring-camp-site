package com.campingsite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

////업로드한 파일을 읽어올 경로를 설정
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer{
//	
//	@Value("${uploadPath}")	//프로퍼티 값을 읽어온다.
//	String uploadPath;
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/images/**")
//				.addResourceLocations(uploadPath);
//	}
//	
//	
//}
