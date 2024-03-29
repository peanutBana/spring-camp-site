package com.campingsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campingsite.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
//	User findByUserId(String userId);

}
