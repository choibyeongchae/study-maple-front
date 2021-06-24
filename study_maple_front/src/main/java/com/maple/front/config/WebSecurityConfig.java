package com.maple.front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

import com.maple.front.filter.JwtAuthenticationFilter;
import com.maple.front.filter.JwtAuthorizationFilter;
import com.maple.front.repository.MemberRefreshRepository;
import com.maple.front.util.UserDetailUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final CorsFilter corsFilter;
	private final MemberRefreshRepository memberRefreshRepository;
	private final JPAQueryFactory queryFactory;
	private final UserDetailUtil userDetailUtil;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(corsFilter)
		.formLogin().disable() // form login 사용안함
		.httpBasic().disable()
		.addFilter(new JwtAuthenticationFilter(authenticationManager(), memberRefreshRepository, userDetailUtil))
		.addFilter(new JwtAuthorizationFilter(authenticationManager(), queryFactory, memberRefreshRepository,userDetailUtil))
		.authorizeRequests()
		.antMatchers("/member/mypage/*").authenticated()
		.anyRequest()
		.permitAll();
		
	}
}
