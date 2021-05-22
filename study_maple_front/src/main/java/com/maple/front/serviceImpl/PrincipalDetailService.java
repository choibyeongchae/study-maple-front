package com.maple.front.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maple.front.config.PrincipalDetails;
import com.maple.front.entity.Member;
import com.maple.front.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(username);
		if (member == null) {
			Member loadUserByUsername = new Member();
			loadUserByUsername.setEmail(username);
			loadUserByUsername.setPassword("");
			return new PrincipalDetails(loadUserByUsername);
		}
		return new PrincipalDetails(member);
	}
	
	
	

	
}
