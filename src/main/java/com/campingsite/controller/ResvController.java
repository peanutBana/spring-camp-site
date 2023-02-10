package com.campingsite.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class ResvController {
	
//	private final ResvService resvService;
//	
//	@PostMapping(value="/reserve")		//principal => 로그인한 사용자의 정보가 담겨있음  ResponseEntity
//	public @ResponseBody ResponseEntity reserve(@RequestBody @Valid ResvDto resvDto, BindingResult bindingResult, Principal principal ) {
//		if(bindingResult.hasErrors()) {
//			StringBuilder sb = new StringBuilder();
//			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//			
//			for(FieldError fieldError : fieldErrors) {
//				sb.append(fieldError.getDefaultMessage());
//			}
//			return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
//		}
//		
//		String email = principal.getName();		//사용자 email 가져옴
//		Long resvId;
//		
//	
//		try {
//			resvId = resvService.reserve(resvDto, email);
//		}catch (Exception e){
//			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//
//		}	
//		return new ResponseEntity<Long>(resvId, HttpStatus.OK);
//	}
	
	@GetMapping(value="/reserve")
	public String makeReservation() {
		return "reserve/reserveForm";
	}
}
