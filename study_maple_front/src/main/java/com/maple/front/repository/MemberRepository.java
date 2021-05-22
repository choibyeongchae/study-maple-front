package com.maple.front.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maple.front.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
	
	Member findByEmail(String email);
}
