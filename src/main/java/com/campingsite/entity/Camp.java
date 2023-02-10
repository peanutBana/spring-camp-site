package com.campingsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.campingsite.constant.CampType;
import com.campingsite.constant.ResvStatus;
import com.campingsite.dto.CampFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="camp")	//테이블 명 설정
@Getter
@Setter
@ToString
public class Camp extends BaseEntity{
	@Id
	@Column(name="camp_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=50)
	private String campName;

	
	@Column(nullable=false)
	private String campAddress;
	
	@Column(nullable=false)
	private String campTel;
	
	@Column(nullable=false, name="price")
	private int price;
	
	@Lob
	@Column(nullable=false)
	private String introduciton;
	
	@Enumerated(EnumType.STRING)
	private CampType campType;
	
	@Column(nullable=false)
	private int emptySiteNum;
	
	@Enumerated(EnumType.STRING)
	private ResvStatus resvStatus;
	
	public void updateCamp(CampFormDto campFormDto) {
		this.campName = campFormDto.getCampName();
		this.campAddress = campFormDto.getCampAddress();	
		this.campTel = campFormDto.getCampTel();
		this.introduciton = campFormDto.getIntroduciton();
		this.campType = campFormDto.getCampType();
		this.emptySiteNum = campFormDto.getEmptySiteNum();
		this.resvStatus = campFormDto.getResvStatus();
	}
	//재고 감소
	public void removeStock(int emptySiteNum) {
		int restStock = this.emptySiteNum - emptySiteNum;
		
		if(restStock < 0) {
		}
		this.emptySiteNum = restStock;
	}
	
	public void addStock(int emptySiteNum) {
		this.emptySiteNum += emptySiteNum;
	}
}