package com.campingsite.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.campingsite.dto.MainPostDto;
import com.campingsite.dto.PostFormDto;
import com.campingsite.dto.PostImgDto;
import com.campingsite.dto.PostSearchDto;
import com.campingsite.entity.Post;
import com.campingsite.entity.PostImg;
import com.campingsite.repository.PostImgRepository;
import com.campingsite.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
	private final PostRepository postRepository;
	private final PostImgService postImgService;
	private final PostImgRepository postImgRepository;
	
	//post 등록
	public Long savePost(PostFormDto postFormDto, List<MultipartFile> postImgFileList) throws Exception {
		
		//post 등록
		Post post =  postFormDto.createPost();
		postRepository.save(post);
		
		//이미지 등록
		for(int i=0; i<postImgFileList.size(); i++) {
			PostImg postImg = new PostImg();
			postImg.setPost(post);
			
			//첫번째 이미지 대표이미지 지정
			if(i==0) {
				postImg.setRepimgYn("Y");
			}else {
				postImg.setRepimgYn("N");
			}
			postImgService.savePostImg(postImg, postImgFileList.get(i));
		}
		
		return post.getId();
	}
	
	//post 가져오기
	@Transactional(readOnly = true) //트랜잭션 읽기 전용(변경감지 수행하지 않음) -> 성능향상
	public PostFormDto getPostDtl(Long postId) {
		//1. item_img테이블의 이미지를 가져온다.
		List<PostImg> postImgList = postImgRepository.findByPostIdOrderByIdAsc(postId);
		List<PostImgDto> postImgDtoList = new ArrayList<>();
		
		//엔티티 객체 -> dto객체로 변환
		for(PostImg postImg : postImgList) {
			PostImgDto postImgDto = PostImgDto.of(postImg);
			postImgDtoList.add(postImgDto);
		}
		
		//2. item테이블에 있는 데이터를 가져온다.
		Post post = postRepository.findById(postId)
				                  .orElseThrow(EntityNotFoundException::new);
		
		//엔티티 객체 -> dto객체로 변환
		PostFormDto postFormDto = PostFormDto.of(post);
		
		//상품의 이미지 정보를 넣어준다.
		postFormDto.setPostImgDtoList(postImgDtoList);
		
		return postFormDto;
	}
	//post 수정
	public Long updatePost(PostFormDto postFormDto, List<MultipartFile> postImgFileList) throws Exception {
		
		Post post = postRepository.findById(postFormDto.getId())
				.orElseThrow(EntityNotFoundException::new);
		
		post.updatePost(postFormDto);
		
		List<Long> postImgIds = postFormDto.getPostImgIds(); //상품 이미지 아이디 리스트를 조회
		
		for(int i=0; i<postImgFileList.size(); i++) {
			postImgService.updatePostImg(postImgIds.get(i), postImgFileList.get(i));
		}
		
		return post.getId();
	}
	
	//상품 리스트 가져오기
	@Transactional(readOnly = true)
	public Page<Post> getAdminPostPage(PostSearchDto postSearchDto, Pageable pageable) {
		return postRepository.getAdminPostPage(postSearchDto, pageable);
	}
	
	//메인화면 아이템 가져오기
	@Transactional(readOnly = true)
	public Page<MainPostDto> getMainItemPage(PostSearchDto postSearchDto, Pageable pageable) {
		return postRepository.getMainPostPage(postSearchDto, pageable);
	}

	
}
