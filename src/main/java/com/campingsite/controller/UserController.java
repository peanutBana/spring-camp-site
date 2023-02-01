package com.campingsite.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

	//로그인 화면
	@GetMapping(value="/login")
	public String loginMember() {
		return "user/userLoginForm";
	}
}
