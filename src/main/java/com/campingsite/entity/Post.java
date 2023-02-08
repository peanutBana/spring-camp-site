package com.campingsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.campingsite.constant.PostType;
import com.campingsite.dto.PostFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="post")	//테이블 명 설정
@Getter
@Setter
@ToString
public class Post extends BaseEntity{
	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
	@Column(nullable=false)
	private String postTitle;
	
	@Enumerated(EnumType.STRING)
	private PostType postType;
	
	@Column(nullable=false)
	private int likeCount;
	
	@Lob
	@Column(nullable=false)
	private String postDetail;
	
	@JoinColumn(name="user_id")
	private int userId;
	
	@JoinColumn(name="camp_id")
	private int campId;
	
	public void updatePost(PostFormDto postFormDto) {
		this.postTitle = postFormDto.getPostTitle();
		this.postDetail = postFormDto.getPostDetail();
			
	}
	
	
}
