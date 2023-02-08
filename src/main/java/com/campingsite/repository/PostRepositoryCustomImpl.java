//package com.campingsite.repository;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.thymeleaf.util.StringUtils;
//
//import com.campingsite.constant.PostType;
//import com.campingsite.dto.MainPostDto;
//import com.campingsite.dto.PostSearchDto;
//import com.campingsite.entity.Q;
//import com.campingsite.entity.QItemImg;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//
//public class PostRepositoryCustomImpl implements PostRepositoryCustom{
//	
//	private JPAQueryFactory queryFactory;
//	
//	public PostRepositoryCustomImpl(EntityManager em) {
//		this.queryFactory = new JPAQueryFactory(em);
//	}
//	
//	private BooleanExpression regDtsAfter(String searchDateType) {
//		LocalDateTime dateTime = LocalDateTime.now(); 
//		
//		//현재 날짜로 부터 이전 날짜를 구해준다.
//		if(StringUtils.equals("all", searchDateType) || searchDateType == null)  return null;
//		else if(StringUtils.equals("1d", searchDateType)) dateTime = dateTime.minusDays(1); 
//		else if(StringUtils.equals("1w", searchDateType)) dateTime = dateTime.minusWeeks(1);
//		else if(StringUtils.equals("1m", searchDateType)) dateTime = dateTime.minusMonths(1);
//		else if(StringUtils.equals("6m", searchDateType)) dateTime = dateTime.minusMonths(6);
//		
//		return QItem.item.regTime.after(dateTime); //이후의 시간
//	}
//	
//	private BooleanExpression searchSellStatusEq(PostType searchPostType) {
//		return searchPostType == null ? null : QItem.post.postType.eq(searchPostType);
//	}
//	
//	
//	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
//		if(StringUtils.equals("postTitle", searchBy)) {
//			return QItem.post.postTitle.like("%" + searchQuery + "%"); //itemNm LIKE %청바지%
//		} else if(StringUtils.equals("createdBy", searchBy)) {
//			return QItem.post.createdBy.like("%" + searchQuery + "%"); //createdBy LIKE %test.com%
//		}
//		
//		return null;
//	}
//
//	@Override
//	public Page<MainPostDto> getMainPostPage(PostSearchDto postSearchDto, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
