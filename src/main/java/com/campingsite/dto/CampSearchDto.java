package com.campingsite.dto;

import com.campingsite.constant.ResvStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampSearchDto {
	private String searchDateType;
	private ResvStatus searchResvStatus;
	private String searchBy;
	private String searchQuery = "";
}
