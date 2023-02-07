package com.campingsite.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.campingsite.dto.PostFormDto;

import lombok.RequiredArgsConstructor;

public class PostController {
	
	//게시글 리스트 화면	
	@GetMapping(value="/list")
	public String postList(){
		return "post/postList";
	}
	
	@GetMapping(value="/list2")
	public String postList2(){
		return "post/postList2";
	}
	
	//글쓰기 화면
	@GetMapping(value="/new")
	public String writePost(@Valid PostFormDto postFormDto, BindingResult bindingResult,Model model, 
							@RequestParam("campImgFile") List<MultipartFile> campImgFileList){
		if(bindingResult.hasErrors()) {
			return "post/writePost";			
		}
		
		//첫번째 이미지 있는지 검사
		if(campImgFileList.get(0).isEmpty() && postFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
			return "post/writePost";		
		}
		return "redirect:/";
	}
	
	//수정 페이지 보기
//	@GetMapping(value="/update/{postId}")
	
	
	
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
