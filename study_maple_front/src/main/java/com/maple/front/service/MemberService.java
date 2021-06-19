package com.maple.front.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.maple.front.entity.Member;


@Service
public interface MemberService {
	
	public Member signUp(Map<String, Object> reqMap) throws Exception;
}
