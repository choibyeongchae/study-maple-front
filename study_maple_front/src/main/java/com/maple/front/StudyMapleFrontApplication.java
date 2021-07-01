package com.maple.front;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.maple.front.util.UserDetailUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
public class StudyMapleFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyMapleFrontApplication.class, args);
	}
	
	@PersistenceContext
	EntityManager em;
	
	@Bean
	public JPAQueryFactory queryFactory() {
		return new JPAQueryFactory(em);
	}
	
	@Bean
	public UserDetailUtil userDetailUtil() {
		return new UserDetailUtil();
	}

}
