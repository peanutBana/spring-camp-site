package com.campingsite.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.campingsite.dto.CampFormDto;
import com.campingsite.dto.MainCampDto;
import com.campingsite.dto.MainPostDto;
import com.campingsite.dto.PostSearchDto;
import com.campingsite.service.CampService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final CampService campService;
	
	@GetMapping(value="/")
	public String main(CampFormDto campFormDto, Optional<Integer> page, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);		//한 페이지당 가져올 아이템 개수
		Page<MainCampDto> camps = campService.getMainCampPage(campFormDto,pageable);
		
		model.addAttribute("camps", camps);		
		return "main";
	}
}
