package com.maple.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		
		return "home";
	}
	
	@GetMapping("/")
	public ModelAndView redirctHome() {
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/home"));
		return mav;
	}
}
