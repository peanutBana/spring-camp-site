//package com.campingsite.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Table(name="post_img")	//테이블 명 설정
//@Getter
//@Setter
//public class PostImg {
//	@Id
//	@Column(name = "post_img_id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
//	private String imgName;
//	
//	private String oriImgName;	//원본 이미지 파일명
//	
//	private String imgUrl;	//이미지 조회 경로
//	
//	private String repimgYn;	//대표 이미지 여부
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "post_id")
//	private Post post;
//	
//	//원본 이미지 파일명, 업데이트 할 이미지 파일명, 이미지 경로를 파라메터로 받아서 이미지 정보를 업데이트하는 메소드.
//	public void updatePostImg(String oriImgName, String imgName, String imgUrl) {
//		this.oriImgName = oriImgName;
//		this.imgName = imgName;
//		this.imgUrl = imgUrl;
//	}
//		
//}
