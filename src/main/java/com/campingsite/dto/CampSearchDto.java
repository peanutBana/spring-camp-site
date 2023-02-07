package com.campingsite.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampSearchDto {
	private String searchDateType;
	private String searchBy;
	private String searchQuery = "";
}
