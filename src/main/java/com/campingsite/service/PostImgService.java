//package com.campingsite.service;
//
//import javax.persistence.EntityNotFoundException;
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.thymeleaf.util.StringUtils;
//
//import com.campingsite.entity.PostImg;
//import com.campingsite.repository.PostImgRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class PostImgService {
//
//	@Value("${postImgLocation}")
//	private String postImgLocation; //C:/shop/item
//	
//	private final PostImgRepository postImgRepository;
//	
//	private final FileService fileService;
//	
//
//	//이미지 저장 메소드
//	public void savePostImg(PostImg postImg, MultipartFile postImgFile) throws Exception {
//		String oriImgName = postImgFile.getOriginalFilename(); //파일 이름
//		String imgName = "";
//		String imgUrl = "";
//		
//		//파일 업로드
//		if(!StringUtils.isEmpty(oriImgName)) {
//			imgName = fileService.uploadFile(postImgLocation, oriImgName, postImgFile.getBytes());
//			imgUrl = "/images/post/" + imgName;
//		}
//		
//		//상품 이미지 정보 저장
//		postImg.updatePostImg(oriImgName, imgName, imgUrl);
//		postImgRepository.save(postImg);
//		
//	}
//	
//	//이미지 업데이트 메소드
//		public void updatePostImg(Long postImgId, MultipartFile postImgFile) throws Exception {
//			if(!postImgFile.isEmpty()) { //파일이 있으면
//				PostImg savedPostImg = postImgRepository.findById(postImgId)
//						.orElseThrow(EntityNotFoundException::new);
//				
//				//기존 이미지 파일 삭제
//				if(!StringUtils.isEmpty(savedPostImg.getImgName())) {
//					// C:/shop/item/a110f979-1467-4c7e-8346-52373e55018d.jpg
//					fileService.deleteFile(postImgLocation + "/" + savedPostImg.getImgName());
//				}
//				
//				//수정된 이미지 파일 업로드
//				String oriImgName = postImgFile.getOriginalFilename(); 
//				String imgName = fileService.uploadFile(postImgLocation, oriImgName, postImgFile.getBytes());
//				String imgUrl = "/images/item/" + imgName;
//				
//				//★ savedItemImg는 현재 영속상태이므로 데이터를 변경하는 것만으로 변경감지 기능이 동작하여 트랜잭션이 끝날때 update쿼리가 실행된다.
//				//-> 엔티티가 반드시 영속상태여야 한다.
//				savedPostImg.updatePostImg(oriImgName, imgName, imgUrl);
//			}
//		}
//}
