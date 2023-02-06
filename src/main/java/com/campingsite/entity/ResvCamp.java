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
import lombok.ToString;

@Entity
@Table(name="resv_camp")	//테이블 명 설정
@Getter
@Setter
@ToString
public class ResvCamp extends BaseEntity{
	@Id
	@Column(name = "resv_camp_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="camp_id")
	private Camp camp;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="resv_id")
	private Reservation reservation;
	
	private int resvPrice;
//	
//	private int campId;
//	
//	private int resv_id;
}
