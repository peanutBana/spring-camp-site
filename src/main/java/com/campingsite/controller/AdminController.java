package com.campingsite.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.campingsite.entity.Camp;
import com.campingsite.entity.Reservation;
import com.campingsite.entity.User;
import com.campingsite.repository.CampRepository;
import com.campingsite.repository.ResvRepository;
import com.campingsite.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {	
	private final UserRepository userRepository;
	private final ResvRepository resvRepository;
	private final CampRepository campRepository;
	
	
	@GetMapping(value="/setting")
	public String loginMember(Optional<Integer> page, Model model) {
		List<User> users = userRepository.findAll();
		List<Camp> camps = campRepository.findAll();
		List<Reservation> reservations = resvRepository.findAll();

		model.addAttribute("users", users);
		model.addAttribute("camps", camps);
		model.addAttribute("reservations", reservations);
		
		return "admin/setting";
	}
}
