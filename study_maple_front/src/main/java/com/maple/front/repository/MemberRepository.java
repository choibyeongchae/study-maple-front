package com.maple.front.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maple.front.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	
}
