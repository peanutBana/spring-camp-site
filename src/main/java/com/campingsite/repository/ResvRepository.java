package com.campingsite.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.campingsite.entity.Reservation;

public interface ResvRepository extends JpaRepository<Reservation, Long> {
	@Query("select r from Reservation r where r.user.email = :email order by r.resvDate desc")
	List<Reservation> findReservations(@Param("email") String email, Pageable pageable); //현재 로그인 한 사용자의 주문 데이터를 페이징 조건에 맞춰서 조회
	
	@Query("select count(r) from Reservation r where r.user.email = :email")
	Long countReservations(@Param("email") String email); //현재 로그인한 회원의 주문 개수가 몇개인지 조회
}

 