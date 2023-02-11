package com.campingsite.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.campingsite.dto.CampFormDto;
import com.campingsite.dto.CampListDto;
import com.campingsite.dto.CampSearchDto;
import com.campingsite.dto.MainCampDto;
import com.campingsite.service.CampService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/camps")//,method=RequestMethod.GET
@RequiredArgsConstructor
public class CampController {
	private final CampService campService;
	//추천 캠핑장 리스트
	@GetMapping(value="/list")
	public String campList(CampSearchDto campSearchDto, Optional<Integer> page, Model model){
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);		//한 페이지당 가져올 아이템 개수
		Page<CampListDto> camps = campService.getCampListPage(campSearchDto, pageable);
		
		model.addAttribute("camps", camps);		
		model.addAttribute("campSearchDto", campSearchDto);
		model.addAttribute("maxPage", 5);
		
		return "camp/campList";
	}
	
	//camp 등록 페이지 진입
	@GetMapping(value = "/new")
	public String itemForm(Model model) {
		model.addAttribute("campFormDto", new CampFormDto());
		return "camp/campForm";
	}
	
	//camp 등록
	@PostMapping(value="/new")
	public String campNew(@Valid CampFormDto campFormDto, BindingResult bindingResult, 
			Model model, @RequestParam("campImgFile") List<MultipartFile> campImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "camp/campForm";
		}
		if(campImgFileList.get(0).isEmpty() && campFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
			return "camp/campForm";
		}
		try {
			campService.saveCamp(campFormDto, campImgFileList);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다.");
			return "camp/campForm";
		}
		return "redirect:/";
	}


		//camp 수정 페이지 보기
		@GetMapping(value = "/update/{campId}")
		public String campDtl(@PathVariable("campId") Long campId, Model model) {
			try {
				CampFormDto campFormdto = campService.getCampDtl(campId);
				model.addAttribute(campFormdto);
			} catch(EntityNotFoundException e) {
				model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
				model.addAttribute("campFormDto", new CampFormDto());
				return "camp/campForm";
			}
			return "camp/campForm";
		}
	
		//camp 수정
		@PostMapping(value = "/update/{campId}")
			public String camoUpdate(@Valid CampFormDto campFormDto, BindingResult bindingResult, 
				Model model, @RequestParam("campImgFile") List<MultipartFile> campImgFileList) {
			if(bindingResult.hasErrors()) {
				return "camp/campForm";
			}
			
			//첫번째 이미지가 있는지 검사(첫번째 이미지는 필수 입력값이기 때문에)
			if(campImgFileList.get(0).isEmpty() && campFormDto.getId() == null) {
				model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
				return "camp/campForm";
			}
			
			try {
				campService.updateCamp(campFormDto, campImgFileList);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
				return "camp/campForm";
			}
			
			return "redirect:/";
		}
		
		//camp 상세 페이지
		@GetMapping(value = "/{campId}")
		public String campDtl(Model model, @PathVariable("campId") Long campId) {
			CampFormDto campFormDto = campService.getCampDtl(campId);
			model.addAttribute("camp", campFormDto);
			return "camp/campDtl";
		}
		
//		@DeleteMapping(value = "delete/{campId}")
//		public @ResponseBody ResponseEntity deleteCamp(@PathVariable("campId")Long campId) {
//			 campService.deleteCamp(campId);
//			 return new ResponseEntity<Long>(campId, HttpStatus.OK);
//		 }
//		
//		 
//		@DeleteMapping(value = "/{campId}/delete")
//		public String deleteCamp(@PathVariable("campId")Long campId) {
//			 campService.deleteCamp(campId);
//			 return "camp/campDtl";
//		 }
}
