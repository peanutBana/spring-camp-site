package com.campingsite.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainPostDto {
	private Long id;
	
	private String postTitle;
	
	private String postDetil;
	
	private String imgUrl;
	
	private Integer likeCount;

	@QueryProjection
	public MainPostDto(Long id, String postTitle, Integer likeCount, String postDetail, String imgUrl) {
		this.id = id;
		this.postTitle = postTitle;
		this.likeCount = likeCount;
		this.postDetil = postDetail;
		this.imgUrl = imgUrl;
	}
	
}
