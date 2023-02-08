package com.campingsite.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.campingsite.dto.MainPostDto;
import com.campingsite.dto.PostSearchDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
//	private final PostService postService;
	
//	@GetMapping(value="/")
//	public String main(PostSearchDto postSearchDto, Optional<Integer> page, Model model) {
//		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
//		Page<MainPostDto> posts = postService.getMainPostPage(postSearchDto,pageable);
//		
//		model.addAttribute("posts", posts);
//		model.addAttribute("postSearchDto", postSearchDto);
//		model.addAttribute("maxPage", 5);
//		return "main";
//	}
	@GetMapping(value="/")
	public String main() {
	
		return "main";
	}
}
