package com.maple.front.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maple.front.entity.Member;
import com.maple.front.repository.MemberRefreshRepository;
import com.maple.front.repository.MemberRepository;
import com.maple.front.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepository reopsitory;

	@Override
	public Member signUp(Map<String, Object> reqMap) throws Exception {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		Member mbr = Member.builder()
				.mbr_email(String.valueOf(reqMap.get("mbr_email")))
				.mbr_name(String.valueOf(reqMap.get("mbr_pass")))
				.mbr_pass(passwordEncoder.encode(String.valueOf(reqMap.get("mbr_pass_chk"))))
				.mbr_phone(String.valueOf(reqMap.get("mbr_phone")))
				.mbr_born(String.valueOf(reqMap.get("mbr_birth")))
				.mbr_adress(String.valueOf(reqMap.get("mbr_zip")))
				.mbr_zip(String.valueOf(reqMap.get("mbr_adress")))
				.mbr_role("ROLE_USER")
				.build();
		
		Member rtnMbr = reopsitory.save(mbr);
		
		return rtnMbr;
	}
	
	
	
}
