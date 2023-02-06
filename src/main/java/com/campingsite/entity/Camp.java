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

import com.camoingsite.constant.CampType;

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
	private int emptySiteNum;
	
	@Column(nullable=false)
	private String campLocation;
	
	@Column(nullable=false)
	private String campAddress;
	
	private String campTel;
	
	@Lob
	@Column(nullable=false)
	private String facilities;
	
	@Lob
	@Column(nullable=false)
	private String introduciton;
	
	@Enumerated(EnumType.STRING)
	private CampType campType;
	
}
