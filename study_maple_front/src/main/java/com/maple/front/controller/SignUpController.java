package com.maple.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maple.front.entity.Member;
import com.maple.front.service.MemberService;
import com.maple.front.util.SuccessResponse;

import io.swagger.annotations.ApiOperation;



@Controller
public class SignUpController {
	
	@Autowired
	MemberService Service;
	
	
	@ApiOperation("회원가입화면 맵핑 signup_mapping")
	@GetMapping(value = "/")
	public void signup_mapping(Model model) {
		
	}
	
	@ApiOperation("회원가입")
	@GetMapping(value = "/signup")
	
	public String signup(Model model) {
		
		return "signup";
	}
	
	@ApiOperation("회원가입실행")
	@PostMapping(value = "/mbrsignup")
	@Transactional(readOnly = false)
	@ResponseBody
	public SuccessResponse mbrSingUp(@RequestBody Map<String, Object> reqMap, HttpServletResponse response ) throws Exception{
		
		Member member = Service.signUp(reqMap);
		
		return new SuccessResponse(response.SC_OK, "가입성공", member);
		
	}
}


