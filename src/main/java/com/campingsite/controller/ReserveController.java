package com.campingsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
@Controller
@RequestMapping(value = "/reserves")
@RequiredArgsConstructor
public class ReserveController {
	
	@GetMapping(value="/reserve")
	public String makeReservation() {
		return "reserve/reserveForm";
	}
}
