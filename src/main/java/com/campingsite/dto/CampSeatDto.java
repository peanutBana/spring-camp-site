package com.campingsite.dto;

import org.modelmapper.ModelMapper;

import com.campingsite.entity.CampSeat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampSeatDto {
	private Long id;
	
	private String seatName;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static CampSeatDto of(CampSeat campSeat) {
		return modelMapper.map(campSeat, CampSeatDto.class);
	}

}
