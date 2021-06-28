package com.maple.front.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maple.front.config.PrincipalDetails;
import com.maple.front.entity.Member;
import com.maple.front.entity.QMember;
import com.maple.front.repository.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private JPAQueryFactory queryFactory;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		QMember qMember = QMember.member;
		Member member = queryFactory.selectFrom(qMember).where(qMember.mbr_email.eq(username)).fetchFirst();
		if (member == null || !member.getMbr_role().equals("ROLE_USER")) {
			Member loadUserByUsername = new Member();
			loadUserByUsername.setMbr_email(username);
			loadUserByUsername.setMbr_pass("");
			return new PrincipalDetails(loadUserByUsername);
		}
		return new PrincipalDetails(member);
	}
	
	
	

	
}
