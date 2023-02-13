package com.campingsite.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.campingsite.constant.CampType;
import com.campingsite.constant.ResvStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResvFormDto {
	private Long id;
	
	@NotNull(message = "사이트 번호는 필수 입력 값입니다")
	private String siteName;
	
	@NotNull(message = "체크인 날짜는 필수 입력 값입니다")
	private Date chkIn;

	@NotNull(message = "체크아웃 날짜는 필수 입력 값입니다")
	private Date chkOut;
}

