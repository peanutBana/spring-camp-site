package com.campingsite.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.campingsite.entity.User;
import com.campingsite.constant.ResvStatus;
import com.campingsite.entity.ResvCamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="reservations")	//테이블 명 설정
@Getter
@Setter
@ToString
public class Reservation{
	@Id
	@Column(name = "resv_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private ResvStatus resvStatus;
	
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)    //OrderItem에 있는 order에 의해 관리된다.
	private List<ResvCamp> orderItems = new ArrayList<>();
	
	private Date chkIn;
	
	private Date chkOut;
	
}
