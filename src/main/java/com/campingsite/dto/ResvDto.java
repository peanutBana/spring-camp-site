package com.campingsite.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ResvDto {
	 @NotNull(message = "camp 아이디는 필수 입력 값입니다.")
	    private Long CampId;

	    @Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
	    @Max(value = 999, message = "최대 주문 수량은 999개 입니다.")
	    private int count;
}
