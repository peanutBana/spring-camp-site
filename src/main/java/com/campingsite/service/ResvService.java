package com.campingsite.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campingsite.dto.ResvDto;
import com.campingsite.entity.Camp;
import com.campingsite.entity.Reservation;
import com.campingsite.entity.ResvCamp;
import com.campingsite.entity.User;
import com.campingsite.repository.CampImgRepository;
import com.campingsite.repository.CampRepository;
import com.campingsite.repository.ResvRepository;
import com.campingsite.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service //service 클래스의 역할
@Transactional //서비스 클래서에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다. 
@RequiredArgsConstructor
public class ResvService {
	private final CampRepository campRepository;
	private final UserRepository userRepository;
	private final CampImgRepository campImgRepository;
	private final ResvRepository resvRepository;
	
	public Long reserve(ResvDto resvDto, String email) {
		Camp camp = campRepository.findById(resvDto.getCampId())
                .orElseThrow(EntityNotFoundException::new);
		
		User user = userRepository.findByEmail(email);
		
		List<ResvCamp> resvCampList = new ArrayList<>(); 
		ResvCamp resvCamp = ResvCamp.createResvCamp(camp, resvDto.getCount());
		resvCampList.add(resvCamp);
		
		Reservation reservation = Reservation.createReservation(user, resvCampList);
		resvRepository.save(reservation);
		
		return user.getId();
	}
}
