package com.campingsite.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.campingsite.constant.CampType;
import com.campingsite.constant.ResvStatus;
import com.campingsite.entity.Camp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampFormDto {
	private Long id;
	
	@NotBlank(message = "캠핑장 이름은 필수 입력 값입니다.")
	private String campName;
	
	@NotNull(message = "재고는 필수 입력 값입니다")
	private int emptySiteNum;
	
	@NotNull(message = "주소는 필수 입력 값입니다")
	private String campAddress;
	
	private String campTel;
	
	@NotBlank(message = "상세 설명은 필수 입력 값입니다.")
	private String introduciton;
	
	private CampType campType;

	private ResvStatus resvStatus;
	
	private List<CampImgDto> campImgDtoList = new ArrayList<>();
	
	private List<Long> campImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Camp createCamp() {
		return modelMapper.map(this, Camp.class);
	}

	public static CampFormDto of(Camp camp) {
		return modelMapper.map(camp, CampFormDto.class);
	}
	
}
