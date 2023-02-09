package com.campingsite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.campingsite.dto.CampSearchDto;
import com.campingsite.dto.MainCampDto;
import com.campingsite.entity.Camp;

public interface CampRepository  extends JpaRepository<Camp, Long>, QuerydslPredicateExecutor<Camp>, CampRepositoryCustom{
	
	List<Camp> findByCampName(String campName);

	
	
}
