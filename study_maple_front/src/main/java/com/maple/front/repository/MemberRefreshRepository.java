package com.maple.front.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.maple.front.entity.MemberRefreshToken;

public interface MemberRefreshRepository extends CrudRepository<MemberRefreshToken, Long>{

	Optional<MemberRefreshToken> findByEmail(String email);
}
