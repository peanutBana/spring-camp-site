package com.campingsite.dto;

import org.modelmapper.ModelMapper;

import com.campingsite.entity.PostImg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostImgDto {
	private Long id;
	
	private String imgName;
	
	private String oriImgName;
	
	private String imgUrl;		
	
	private String repimgYn;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static PostImgDto of(PostImg postImg) {
		return modelMapper.map(postImg, PostImgDto.class);
	}
}
