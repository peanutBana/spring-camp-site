package com.campingsite.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainCampDto {
	
	private Long id;
	
	private String campName;
	
	private Integer emptySiteNum;
	
	private String campAddress;
	
	private String campTel;

	@QueryProjection
	public MainCampDto(Long id, String campName, Integer emptySiteNum, String campAddress, String campTel) {
		this.id = id;
		this.campName = campName;
		this.emptySiteNum = emptySiteNum;
		this.campAddress = campAddress;
		this.campTel = campTel; 
	}

}
