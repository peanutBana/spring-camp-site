package com.campingsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
public class PostController {
	
	//게시글 리스트 화면	
	@GetMapping(value="/list")
	public String postList(){
		return "post/postList";
	}
	
//	@GetMapping(value="/list/{postNm}")
	@GetMapping(value="/list/detail")
	public String postDetail() {
		return "post/postDetail";
	}
	
	@GetMapping(value="/map")
	public String postMap() {
		return "post/campMap";
	}


}
