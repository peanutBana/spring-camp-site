package com.campingsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campingsite.dto.CampFormDto;
import com.campingsite.service.CampService;

import lombok.RequiredArgsConstructor;

@Controller
@RestController
@RequiredArgsConstructor
public class CampController {
	private final CampService campService;
	
	@GetMapping(value="/admin/camp/new")
	public String campForm(Model model) {
		model.addAttribute("campFormDto", new CampFormDto());
		return "camp/campForm";
	}
}
