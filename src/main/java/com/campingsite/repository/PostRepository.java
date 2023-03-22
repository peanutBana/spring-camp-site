package com.campingsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.campingsite.entity.Post;



public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPredicateExecutor<Post>{
	
	List<Post> findByPostTitle(String postTitle);
	
	List<Post> findByPostTitleOrPostDetail(String postTitle, String postDetail);
	
}
