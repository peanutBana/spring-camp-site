package com.campingsite.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.campingsite.dto.CampFormDto;
import com.campingsite.dto.PostFormDto;
import com.campingsite.entity.Camp;
import com.campingsite.entity.CampImg;
import com.campingsite.entity.Post;
import com.campingsite.repository.CampImgRepository;
import com.campingsite.repository.CampRepository;
import com.campingsite.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CampService {
	private final CampRepository campRepository;
	private final CampImgService campImgService;
	private final CampImgRepository campImgRepository;

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

}
