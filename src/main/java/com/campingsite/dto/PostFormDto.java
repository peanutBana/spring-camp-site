package com.campingsite.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.campingsite.constant.PostType;
import com.campingsite.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFormDto {
	private Long id;
	
	private String postTitle;
	
	private int userId;
	
	private String postDetail;
	
	private PostType postType;
	
	private List<CampImgDto> postImgDtoList = new ArrayList<>();
	
	private List<Long> campImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Post createPost() {
		return modelMapper.map(this, Post.class);
	}
	
	public static PostFormDto of(Post post) {
		return modelMapper.map(post, PostFormDto.class);
	}
  	
}
