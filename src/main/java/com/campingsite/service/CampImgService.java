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
//import com.campingsite.entity.CampImg;
//import com.campingsite.repository.CampImgRepository;
//
//import lombok.RequiredArgsConstructor;
//
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class CampImgService {
//	
//	@Value("${campImgLocation}")
//	private String campImgLocation;
//	
//	private final CampImgRepository campImgRepository;
//	
//	private final FileService fileService;
//	
//	public void saveCampImg(CampImg campImg, MultipartFile campImgFile) throws Exception{
//		String oriImgName = campImgFile.getOriginalFilename();
//		String imgName = "";
//		String imgUrl = "";
//		
//		//파일 업로
//		if(!StringUtils.isEmpty(oriImgName)) {
//			imgName = fileService.uploadFile(campImgLocation, oriImgName, campImgFile.getBytes());
//			imgUrl = "/images/camp_img" + imgName;
//		}
//		
//		campImg.updateCampImg(oriImgName, imgName, imgUrl);
//		campImgRepository.save(campImg);
//	}
//	
//	//이미지 업데이트 메소드
//	public void updateCampImg(Long campImgId, MultipartFile campImgFile) throws Exception {
//		if(!campImgFile.isEmpty()) { //파일이 있으면
//			CampImg savedCampImg = campImgRepository.findById(campImgId)
//					.orElseThrow(EntityNotFoundException::new);
//			
//			//기존 이미지 파일 삭제
//			if(!StringUtils.isEmpty(savedCampImg.getImgName())) {
//				// C:/shop/item/a110f979-1467-4c7e-8346-52373e55018d.jpg
//				fileService.deleteFile(campImgLocation + "/" + savedCampImg.getImgName());
//			}
//			
//			//수정된 이미지 파일 업로드
//			String oriImgName = campImgFile.getOriginalFilename(); 
//			String imgName = fileService.uploadFile(campImgLocation, oriImgName, campImgFile.getBytes());
//			String imgUrl = "/images/item/" + imgName;
//			
//			//★ savedItemImg는 현재 영속상태이므로 데이터를 변경하는 것만으로 변경감지 기능이 동작하여 트랜잭션이 끝날때 update쿼리가 실행된다.
//			//-> 엔티티가 반드시 영속상태여야 한다.
//			savedCampImg.updateCampImg(oriImgName, imgName, imgUrl);
//		}
//	}
//	
//	
//	
//	
//	
//	
//}
