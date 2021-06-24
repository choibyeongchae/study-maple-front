package com.maple.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maple.front.entity.Member;
import com.maple.front.service.MemberService;
import com.maple.front.util.SuccessResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/member/core")
public class MemberController {
	
	@Autowired
	private MemberService Service;
	
	@ApiOperation("회원가입실행")
	@PostMapping(value = "/signup")
	@Transactional(readOnly = false)
	@ResponseBody
	public SuccessResponse mbrSingUp(@RequestBody Map<String, Object> reqMap, HttpServletResponse response ) throws Exception{
		
		Member member = Service.signUp(reqMap);
		
		return new SuccessResponse(response.SC_OK, "회원가입에 성공하였습니다.", member);
		
	}
}


