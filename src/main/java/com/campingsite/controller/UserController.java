package com.campingsite.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.campingsite.dto.UserFormDto;
import com.campingsite.entity.User;
import com.campingsite.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping(value="/new")
	public String MemberForm(Model model){
		model.addAttribute("userFormDto", new UserFormDto());
		return "user/userForm"; 
	}
	
	@PostMapping(value="/new")
	public String MemberForm(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model){
		
		//에러 있으면 userForm으로
		if(bindingResult.hasErrors()) {
			return "user/userForm";			
		}
		try {
			User user = User.createUser(userFormDto, passwordEncoder);
			userService.saveUser(user);
		}catch(IllegalStateException e) {
			model.addAttribute("errorMessage",e.getMessage());
			return "user/userForm";	
		}
		
		return "redirect:/";
	}
	
	//로그인 화면
	@GetMapping(value="/login")
	public String loginMember() {
		return "user/userLoginForm";
	}
	
	//로그인 실패시
	@GetMapping(value="/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "user/userLoginForm";
	}
}
