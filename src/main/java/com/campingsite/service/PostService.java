package com.campingsite.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campingsite.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
	private final PostRepository postRepository;
	private final CampImgService campImgService;

}
