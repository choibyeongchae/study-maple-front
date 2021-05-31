package com.maple.front.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maple.front.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
	
	// 디폴트 지원 기본키로 찾음
	Member findByEmail(String email);
}
