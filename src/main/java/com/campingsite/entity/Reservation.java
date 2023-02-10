//package com.campingsite.entity;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import com.campingsite.entity.User;
//import com.campingsite.constant.OrderStatus;
//import com.campingsite.constant.ResvStatus;
//import com.campingsite.entity.ResvCamp;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@Entity
//@Table(name="reservation")	//테이블 명 설정
//@Getter
//@Setter
//@ToString
//public class Reservation{
//	@Id
//	@Column(name = "resv_id")
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private Long id;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="user_id")
//	private User user;
//	
//	@Enumerated(EnumType.STRING)
//	private OrderStatus orderStatus;
//	
//	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)    //OrderItem에 있는 order에 의해 관리된다.
//	private List<ResvCamp> resvCamps = new ArrayList<>();
//	
//	private LocalDateTime resvDate;
//	
//	private Date chkIn;
//	
//	private Date chkOut;
//	
//	public void addResvCamp(ResvCamp resvCamp) {
//		resvCamps.add(resvCamp);
//		resvCamp.setReservation(this);
//	}
//	
//	public static Reservation createReservation(User user, List<ResvCamp> resvCampList){
//		Reservation reservation = new Reservation();
//		reservation.setUser(user);
//		
//		for(ResvCamp resvCamp : resvCampList) {
//			reservation.addResvCamp(resvCamp);
//		}
//		reservation.setOrderStatus(OrderStatus.ORDER);
//		reservation.setResvDate(LocalDateTime.now());
//		
//		return reservation;
//	}
//	
//	public int getTotalPrice() {
//		int totalPrice = 0;
//		for(ResvCamp resvCamp : resvCamps) {
//			totalPrice += resvCamp.getTotalPrice();
//		}
//		return totalPrice;
//	}
//	
//	public void cancelOrder() {
//		this.orderStatus = OrderStatus.CANCEL;
//		
//		for(ResvCamp resvCamp : resvCamps) {
//			resvCamp.cancel();		//재고 증가
//		}
//	}
//	
//	
//	
//}
