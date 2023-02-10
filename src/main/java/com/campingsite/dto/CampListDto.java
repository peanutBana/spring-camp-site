package com.campingsite.dto;

import com.campingsite.constant.CampType;
import com.campingsite.constant.ResvStatus;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class CampListDto {
	private Long id;
	private String campName;
	private int emptySiteNum;
	private String campTel;
	private String introduction;
	private String imgUrl;
	private CampType campType;
	private ResvStatus resvStatus;
	
	
	@QueryProjection
	public CampListDto(Long id, String campName, int emptySiteNum, String campTel, 
				String introduction, String imgUrl, CampType campType, ResvStatus resvStatus) {
		this.id = id;
		this.campName = campName;
		this.emptySiteNum = emptySiteNum;
		this.campTel = campTel;
		this.introduction = introduction;
		this.imgUrl = imgUrl;
		this.campType = campType;
		this.resvStatus = resvStatus;
	}
}