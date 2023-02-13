package com.campingsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="camp_seat")	//테이블 명 설정
@Getter
@Setter
public class CampSeat {

	@Id
	@Column(name = "camp_seat_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String seatName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "camp_id")
	private Camp camp;	
	
}
