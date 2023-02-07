package com.campingsite.dto;

import org.modelmapper.ModelMapper;

import com.campingsite.entity.CampImg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampImgDto {
	private Long id;
	
	private String imgName;
	
	private String oriImgName;
	
	private String imgUrl;		
	
	private String repimgYn;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static CampImgDto of(CampImg campImg) {
		return modelMapper.map(campImg, CampImgDto.class);
	}

}
