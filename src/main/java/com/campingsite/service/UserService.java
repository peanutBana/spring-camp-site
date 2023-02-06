package com.campingsite.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campingsite.entity.User;
import com.campingsite.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service					//service 클래스의 역할
@Transactional				//서비스 클래스에서 로직을 처리하다 에러 발생시 수행 이전상태로 되돌려준다. 
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);	//이메일로 식별된 user 정보가 담긴 User객체 저장
		if(user == null) {
			throw new UsernameNotFoundException(email);	
		}
//		
		return org.springframework.security.core.userdetails.User.builder()
				   .username(user.getEmail())
				   .password(user.getPassword())
				   .roles(user.getRole().toString())
				   .build();
	}
	
	public User saveUser(User user) {
		validateDuplicateUser(user);
		return userRepository.findByEmail(user.getEmail());
	}
	
	private void validateDuplicateUser(User user) {
		User findUser = userRepository.findByEmail(user.getEmail()); 
		
		if(findUser != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}
	
	
}
