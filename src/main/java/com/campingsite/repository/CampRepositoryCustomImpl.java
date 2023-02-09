package com.campingsite.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.campingsite.dto.CampFormDto;
import com.campingsite.dto.CampSearchDto;
import com.campingsite.dto.MainCampDto;
import com.campingsite.dto.QMainCampDto;
import com.campingsite.entity.Camp;
import com.campingsite.entity.CampImg;
import com.campingsite.constant.ResvStatus;
import com.campingsite.entity.QCamp;
import com.campingsite.entity.QCampImg;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class CampRepositoryCustomImpl implements CampRepositoryCustom{
	
	private JPAQueryFactory queryFactory;
	
	public CampRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	private BooleanExpression regDtsAfter(String searchDateType) {
		LocalDateTime dateTime = LocalDateTime.now(); 
		
		//현재 날짜로 부터 이전 날짜를 구해준다.
		if(StringUtils.equals("all", searchDateType) || searchDateType == null)  return null;
		else if(StringUtils.equals("1d", searchDateType)) dateTime = dateTime.minusDays(1); 
		else if(StringUtils.equals("1w", searchDateType)) dateTime = dateTime.minusWeeks(1);
		else if(StringUtils.equals("1m", searchDateType)) dateTime = dateTime.minusMonths(1);
		else if(StringUtils.equals("6m", searchDateType)) dateTime = dateTime.minusMonths(6);
		
		return QCamp.camp.regTime.after(dateTime); //이후의 시간
	}
	
	private BooleanExpression searchResvStatusEq(ResvStatus searchResvStatus) {
		return searchResvStatus == null ? null : QCamp.camp.resvStatus.eq(searchResvStatus);
	}
	
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("campName", searchBy)) {
			return QCamp.camp.campName.like("%" + searchQuery + "%"); //itemNm LIKE %청바지%
		} else if(StringUtils.equals("createdBy", searchBy)) {
			return QCamp.camp.createdBy.like("%" + searchQuery + "%"); //createdBy LIKE %test.com%
		}
		
		return null;
	}
	
	@Override
	public Page<Camp> getAdminCampPage(CampSearchDto campSearchDto, Pageable pageable) {
		List<Camp> content = queryFactory
				.selectFrom(QCamp.camp) //select * from item
				.where(regDtsAfter(campSearchDto.getSearchDateType()), // where reg_time = ?
						searchResvStatusEq(campSearchDto.getSearchResvStatus()), //and sell_status = ?
					   searchByLike(campSearchDto.getSearchBy(), campSearchDto.getSearchQuery())) // and itemNm LIKE %검색어%
				.orderBy(QCamp.camp.id.desc())
				.offset(pageable.getOffset()) //데이터를 가져올 시작 index
				.limit(pageable.getPageSize()) //한번에 가지고 올 최대 개수
				.fetch();
		
		long total = queryFactory.select(Wildcard.count).from(QCamp.camp)
                .where(regDtsAfter(campSearchDto.getSearchDateType()),
                		searchResvStatusEq(campSearchDto.getSearchResvStatus()),
                        searchByLike(campSearchDto.getSearchBy(), campSearchDto.getSearchQuery()))
                .fetchOne();
		
		return new PageImpl<>(content, pageable, total);
	}
	
	private BooleanExpression campNmLike(String searchQuery) {
		return StringUtils.isEmpty(searchQuery) ? null : QCamp.camp.campName.like("%" + searchQuery + "%");
	}

	@Override
	public Page<MainCampDto> getMainCampPage(CampFormDto campFromDto, Pageable pageable) {
		QCamp camp= QCamp.camp;
		QCampImg campImg = QCampImg.campImg;
		
		List<MainCampDto> content = queryFactory.select(
					new QMainCampDto(
							camp.id,
							camp.campName,
							camp.emptySiteNum,
							camp.campAddress,
							campImg.imgUrl,
							camp.campTel
							)
					).from(campImg)
				     .join(campImg.camp, camp)
				     .where(campImg.repImgYn.eq("Y"))
				     .orderBy(camp.id.desc())
				     .offset(pageable.getOffset())
					 .limit(pageable.getPageSize())
					 .fetch();
		
		//count(*)
		Long total = queryFactory.select(Wildcard.count)
					 .from(campImg)
				     .join(campImg.camp, camp)
				     .where(campImg.repImgYn.eq("Y"))				  
				     .fetchOne();
		
		return new PageImpl<>(content, pageable, total);
	}


	

}
