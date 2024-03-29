package com.campingsite.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.campingsite.dto.CampFormDto;
import com.campingsite.dto.CampImgDto;
import com.campingsite.dto.CampListDto;
import com.campingsite.dto.CampSearchDto;
import com.campingsite.dto.MainCampDto;
import com.campingsite.dto.ResvFormDto;
import com.campingsite.entity.Camp;
import com.campingsite.entity.CampImg;
import com.campingsite.entity.Post;
import com.campingsite.entity.Reservation;
import com.campingsite.entity.User;
import com.campingsite.repository.CampImgRepository;
import com.campingsite.repository.CampRepository;
import com.campingsite.repository.ResvRepository;
import com.campingsite.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CampService {
	private final CampRepository campRepository;
	private final UserRepository userRepository;
	private final CampImgService campImgService;
	private final CampImgRepository campImgRepository;
	private final ResvRepository resvRepository;
	private final User user;

	//상품 등록
	public Long saveCamp(CampFormDto campFormDto, List<MultipartFile> campImgFileList) throws Exception{
		Camp camp = campFormDto.createCamp();
		campRepository.save(camp);
		
		//이미지 등록
		for(int i=0; i<campImgFileList.size(); i++) {
			CampImg campImg = new CampImg();
			campImg.setCamp(camp);
			
			if(i == 0) {
				campImg.setRepImgYn("Y");
			}else {
				campImg.setRepImgYn("N");
			}
			campImgService.saveCampImg(campImg, campImgFileList.get(i));
		}
		return camp.getId();
	}
	
	public Long saveResv(ResvFormDto resvFormDto) throws Exception{
		Reservation reservation = resvFormDto.createResv();
		resvRepository.save(reservation);
		
		return reservation.getId();
	}
	
	//캠프 가져오기
	@Transactional(readOnly = true) //트랜잭션 읽기 전용(변경감지 수행하지 않음) -> 성능향상
	public CampFormDto getCampDtl(Long campId) {
		//1. item_img테이블의 이미지를 가져온다.
		List<CampImg> campImgList = campImgRepository.findByCampIdOrderByIdAsc(campId);
		List<CampImgDto> campImgDtoList = new ArrayList<>();
		
		for(CampImg campImg : campImgList) {
			CampImgDto campImgDto = CampImgDto.of(campImg);
			campImgDtoList.add(campImgDto);
		}
		Camp camp = campRepository.findById(campId)
				                  .orElseThrow(EntityNotFoundException::new);
		
		//엔티티 객체 -> dto객체로 변환
		CampFormDto campFormDto = CampFormDto.of(camp);
		
		//상품의 이미지 정보를 넣어준다.
		campFormDto.setCampImgDtoList(campImgDtoList);
		
		return campFormDto;
	}
	
	@Transactional(readOnly = true) //트랜잭션 읽기 전용(변경감지 수행하지 않음) -> 성능향상
	public CampFormDto getCampInfo(Long campId) {
	
		Camp camp = campRepository.findById(campId)
				                  .orElseThrow(EntityNotFoundException::new);
		//엔티티 객체 -> dto객체로 변환
		CampFormDto campFormDto = CampFormDto.of(camp);
		
		return campFormDto;
	}
	
	@Transactional(readOnly = true) //트랜잭션 읽기 전용(변경감지 수행하지 않음) -> 성능향상
	public CampFormDto getCampResv(Long campId) {
		//1. item_img테이블의 이미지를 가져온다.
		List<CampImg> campImgList = campImgRepository.findByCampIdOrderByIdAsc(campId);
		List<CampImgDto> campImgDtoList = new ArrayList<>();
		
		for(CampImg campImg : campImgList) {
			CampImgDto campImgDto = CampImgDto.of(campImg);
			campImgDtoList.add(campImgDto);
		}
		Camp camp = campRepository.findById(campId)
				                  .orElseThrow(EntityNotFoundException::new);
		
		//엔티티 객체 -> dto객체로 변환
		CampFormDto campFormDto = CampFormDto.of(camp);
		
		//상품의 이미지 정보를 넣어준다.
		campFormDto.setCampImgDtoList(campImgDtoList);
		
		return campFormDto;
	}
	
	
	//수정
	public Long updateCamp(CampFormDto campFormDto,List<MultipartFile> campImgFileList) throws Exception {
		Camp camp = campRepository.findById(campFormDto.getId())
				.orElseThrow(EntityNotFoundException::new);
		
		camp.updateCamp(campFormDto);
		
		List<Long> campImgIds = campFormDto.getCampImgIds();
		
		for(int i=0; i<campImgFileList.size(); i++) {
			campImgService.updateCampImg(campImgIds.get(i), campImgFileList.get(i));
		}
		return camp.getId();
	}
	
	//삭제
	public void deleteCamp(Long campId) {
		Camp camp = campRepository.findById(campId).orElseThrow(EntityNotFoundException::new);
		campRepository.delete(camp);
	}
	

	//상품 리스트 가져오기
		@Transactional(readOnly = true)
		public Page<Camp> getAdminCampPage(CampSearchDto campSearchDto, Pageable pageable) {
			return campRepository.getAdminCampPage(campSearchDto, pageable);
		}
		
		//메인화면 아이템 가져오기
		@Transactional(readOnly = true)
		public Page<MainCampDto> getMainCampPage(CampFormDto campFormDto, Pageable pageable) {
			return campRepository.getMainCampPage(campFormDto, pageable);
		}
		
		@Transactional(readOnly = true)
		public Page<CampListDto> getCampListPage(CampSearchDto campSearchDto, Pageable pageable) {
			return campRepository.getCampListPage(campSearchDto, pageable);
		}
		
//		@Transactional(readOnly = true)
//		public boolean validateBoard(Long boardId, String id) {
//			User curUser= userRepository.findByUserId(id);
//			Camp camp = campRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
//
//			User savedUser= camp.getUser();
//
//			if(!StringUtils.equals(curUser.getId(), savedUser.getId())) {
//				return false;
//			}
//			return true;
//		}
}
