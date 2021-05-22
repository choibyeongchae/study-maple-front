package com.maple.front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // response후 javascript에서 처리가능하게 설정
		config.addAllowedOrigin("*"); // 모든 ip에 응답 허용
		config.addAllowedHeader("*"); // 모든 헤더 응답 허용
		config.addAllowedMethod("*"); // get,post등등 허용
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
