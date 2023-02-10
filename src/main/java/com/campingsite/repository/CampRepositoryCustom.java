package com.campingsite.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.campingsite.dto.CampFormDto;
import com.campingsite.dto.CampListDto;
import com.campingsite.dto.CampSearchDto;
import com.campingsite.dto.MainCampDto;
import com.campingsite.entity.Camp;

public interface CampRepositoryCustom {
	Page<Camp> getAdminCampPage(CampSearchDto campSearchDto, Pageable pageable);
	
	Page<MainCampDto> getMainCampPage(CampFormDto campFromDto, Pageable pageable);
	
	Page<CampListDto> getCampListPage(CampSearchDto campSearchDto, Pageable pageable);
}
