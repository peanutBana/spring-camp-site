//package com.campingsite.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.campingsite.entity.PostImg;
//
//public interface PostImgRepository extends JpaRepository<PostImg, Long> {
//
//	List<PostImg> findByPostIdOrderByIdAsc(Long postId);
//	
//	//상품의 대표 이미지를 찾음
//	PostImg findByPostIdAndRepimgYn(Long postId, String repimgYn);
//	
//}
