package com.campingsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campingsite.entity.CampImg;

public interface CampImgRepository extends JpaRepository<CampImg, Long>{
	List<CampImg> findByCampIdOrderByIdAsc(Long campId);
	
	//대표이미지 찾기
	CampImg findByCampIdAndRepImgYn(Long campId, String repImgYn);
}
