package com.campingsite.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.campingsite.constant.PostType;
import com.campingsite.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFormDto {
	private Long id;

	private int userId;
	
	@NotBlank(message = "캠핑장 이름은 필수 입력 값입니다.")
	private String postTitle;
	
	@NotBlank(message = "캠핑장 설명은 필수 입력 값입니다.")	
	private String postDetail;
	
	private List<PostImgDto> postImgDtoList = new ArrayList<>();
	
	private List<Long> postImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Post createPost() {
		return modelMapper.map(this, Post.class);
	}
	
	public static PostFormDto of(Post post) {
		return modelMapper.map(post, PostFormDto.class);
	}
  	
}
