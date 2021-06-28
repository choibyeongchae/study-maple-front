package com.maple.front.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/member")
public class MemberWebController {

	@ApiOperation("/회원가입 화면")
	@GetMapping("/signup")
	public String signup(Model model) {
		return "/member/signup";
	}
	
	@ApiOperation("로그인 화면")
	@GetMapping("/login")
	public String login(Model model) {
		return "/member/login";
	}
}
