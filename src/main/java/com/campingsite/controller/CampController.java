//package com.campingsite.controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import javax.persistence.EntityNotFoundException;
//import javax.validation.Valid;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.campingsite.dto.CampFormDto;
//import com.campingsite.dto.CampSearchDto;
//import com.campingsite.entity.Camp;
//import com.campingsite.service.CampService;
//
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RestController
//@RequiredArgsConstructor
//public class CampController {
//	private final CampService campService;
//	
//	@GetMapping(value="/admin/camp/new")
//	public String campForm(Model model) {
//		model.addAttribute("campFormDto", new CampFormDto());
//		return "camp/campForm";
//	}
//	
//	@PostMapping(value="/admin/camp/new")
//	public String campNew(@Valid CampFormDto campFormDto, BindingResult bindingResult, 
//			Model model, @RequestParam("campImgFile") List<MultipartFile> campImgFileList) {
//		
//		if(bindingResult.hasErrors()) {
//			return "camp/campForm";
//		}
//		if(campImgFileList.get(0).isEmpty() && campFormDto.getId() == null) {
//			model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
//			return "camp/campForm";
//		}
//		try {
//			campService.saveCamp(campFormDto, campImgFileList);
//		} catch (Exception e) {
//			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다.");
//			return "camp/campForm";
//		}
//		return "redirect:/";
//	}
//		
////		//상품 수정 페이지 보기
////		@GetMapping(value = "/admin/camp/{campId}")
////		public String campDtl(@PathVariable("campId") Long campId, Model model) {
////			try {
////				CampFormDto campFormdto = campService.getCampDtl(campId);
////				model.addAttribute(campFormdto);
////			} catch(EntityNotFoundException e) {
////				model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
////				model.addAttribute("campFormDto", new CampFormDto());
////				return "camp/campForm";
////			}
////			return "camp/campForm";
////		}
////	
////		//상품 수정
////		@PostMapping(value = "/admin/camp/{campId}")
////		public String camoUpdate(@Valid CampFormDto campFormDto, BindingResult bindingResult, 
////				Model model, @RequestParam("campImgFile") List<MultipartFile> campImgFileList) {
////			if(bindingResult.hasErrors()) {
////				return "camp/campForm";
////			}
////			
////			//첫번째 이미지가 있는지 검사(첫번째 이미지는 필수 입력값이기 때문에)
////			if(campImgFileList.get(0).isEmpty() && campFormDto.getId() == null) {
////				model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
////				return "camp/campForm";
////			}
////			
////			try {
////				campService.updateCamp(campFormDto, campImgFileList);
////			} catch (Exception e) {
////				e.printStackTrace();
////				model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
////				return "camp/campForm";
////			}
////			
////			return "redirect:/";
////		}
//		
////		@GetMapping(value = {"/admin/camps", "/admin/camps/{page}"}) //페이지 번호가 없는 경우와 있는 경우 2가지를 매핑
////		public String itemManage(CampSearchDto campSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
////			//url경로에 페이지가 있으면 해당 페이지를 조회하도록 하고 페이지 번호가 없으면 0페이지를 조회하도록 한다.
////			Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3); //of(조회할 페이지의 번호, 한페이지당 조회할 데이터의 갯수)
////			
////			Page<Camp> camps = campService.getAdminCampPage(campSearchDto, pageable);
////			
////			model.addAttribute("camps", camps);
////			model.addAttribute("campSearchDto", campSearchDto);
////			model.addAttribute("maxPage", 5); //상품 관리 메뉴 하단에 보여줄 최대 페이지 번호
////			
////			return "/";
////		}
//}
