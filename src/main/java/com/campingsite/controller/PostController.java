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
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.campingsite.dto.PostFormDto;
//import com.campingsite.dto.PostSearchDto;
//import com.campingsite.entity.Post;
//import com.campingsite.service.PostService;
//
//import lombok.RequiredArgsConstructor;
//@Controller
//@RequestMapping(value = "/posts")
//@RequiredArgsConstructor
//public class PostController {
//	
//	private final PostService postService;
//	
//	//게시글 리스트 화면	
//	@GetMapping(value="/list")
//	public String postList2(){
//		return "post/postList2";
//	}
//	
//	@GetMapping(value = "/new")
//	public String postForm(Model model) {
//		model.addAttribute("postFormDto", new PostFormDto());
//		return "post/postForm";
//	}
//	//map
//	@GetMapping(value="/posts/map")
//	public String postMap() {
//		return "post/campMap";
//	}
//	//post 등록
//	@PostMapping(value="post/new")
//	public String postNew(@Valid PostFormDto postFormDto, BindingResult bindingResult,Model model, 
//							@RequestParam("postImgFile") List<MultipartFile> postImgFileList){
//		if(bindingResult.hasErrors()) {
//			return "post/postForm";			
//		}
//		
//		//첫번째 이미지 있는지 검사
//		if(postImgFileList.get(0).isEmpty() && postFormDto.getId() == null) {
//			model.addAttribute("errorMessage", "post 상품 이미지는 필수 입력 값 입니다.");
//			return "post/postForm";		
//		}
//		
//		try {
//			postService.savePost(postFormDto, postImgFileList);
//		} catch (Exception e) {
//			model.addAttribute("errorMessage", "post 등록 중 에러가 발생했습니다.");
//			return "post/postForm";
//		}
//		
//		return "redirect:/";
//
//	}
//	
//	//post 수정 페이지 보기
//	@GetMapping(value="/post/{postId}")
//	public String postDtl(@PathVariable("postId") Long postId, Model model) {
//		try {
//			PostFormDto postFormdto = postService.getPostDtl(postId);
//			model.addAttribute(postFormdto);
//		} catch(EntityNotFoundException e) {
//			model.addAttribute("errorMessage", "존재하지 않는 post입니다.");
//			model.addAttribute("itemFormDto", new PostFormDto());
//			return "post/postForm";
//		}
//		
//		return "post/postForm";
//	}
//	
////	post수정
//		@PostMapping(value = "/post/{postId}")
//		public String postUpdate(@Valid PostFormDto postFormDto, BindingResult bindingResult, 
//				Model model, @RequestParam("postImgFile") List<MultipartFile> postImgFileList) {
//			if(bindingResult.hasErrors()) {
//				return "post/postForm";
//			}
//			
//			//첫번째 이미지가 있는지 검사(첫번째 이미지는 필수 입력값이기 때문에)
//			if(postImgFileList.get(0).isEmpty() && postFormDto.getId() == null) {
//				model.addAttribute("errorMessage", "post 상품 이미지는 필수 입력 값 입니다.");
//				return "post/postForm";
//			}
//			
//			try {
//				postService.updatePost(postFormDto, postImgFileList);
//			} catch (Exception e) {
//				e.printStackTrace();
//				model.addAttribute("errorMessage", "post 수정 중 에러가 발생하였습니다.");
//				return "post/postForm";
//			}
//			
//			return "redirect:/";
//		}
//		
//		//상품 상세 페이지
//		@GetMapping(value = "/post/{postId}")
//		public String postDtl(Model model, @PathVariable("postId") Long postId) {
//			PostFormDto postFormDto = postService.getPostDtl(postId);
//			model.addAttribute("post", postFormDto);
//			return "post/postDtl";
//		}
//		
//	
//		@GetMapping(value = {"/posts", "/posts/{page}"}) //페이지 번호가 없는 경우와 있는 경우 2가지를 매핑
//		public String itemManage(PostSearchDto postSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
//		
//			//url경로에 페이지가 있으면 해당 페이지를 조회하도록 하고 페이지 번호가 없으면 0페이지를 조회하도록 한다.
//			Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3); //of(조회할 페이지의 번호, 한페이지당 조회할 데이터의 갯수)
//			
//			Page<Post> posts = postService.getAdminPostPage(postSearchDto, pageable);   
//			
//			model.addAttribute("posts", posts);
//			model.addAttribute("postSearchDto", postSearchDto);
//			model.addAttribute("maxPage", 5); //상품 관리 메뉴 하단에 보여줄 최대 페이지 번호
//			
//			return "post/postMng";
//		}
//
//		
//	
//
//
//}
