package com.campingsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.campingsite.entity.Camp;

public interface CampRepository  extends JpaRepository<Camp, Long>, QuerydslPredicateExecutor<Camp>{
	
	List<Camp> findByCampName(String campName);
	
}
