package com.campingsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.camoingsite.constant.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="user")	//테이블 명 설정
@Getter
@Setter
@ToString
public class User extends BaseEntity{
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private Long email;
	
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
