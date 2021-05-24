package com.maple.front.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Builder;
import lombok.Data;

@Data
@RedisHash("userRefreshToken")
public class MemberRefreshToken implements Serializable{

	@Id
	private Long id;
	
	@Indexed
	private String email;
	
	private String token;
	
	private Long expireTime;
	
	@Builder
	public MemberRefreshToken(String email, String token, Long expireTime) {
		this.email = email;
		this.token = token;
		this.expireTime = expireTime;
	}
	
	
}
