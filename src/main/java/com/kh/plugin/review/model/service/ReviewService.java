package com.kh.plugin.review.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.common.util.Pagination;
import com.kh.plugin.common.util.PagingRequestValidator;
import com.kh.plugin.exception.BoardNotFoundException;
import com.kh.plugin.exception.IdMismatchException;
import com.kh.plugin.review.model.dao.ReviewMapper;
import com.kh.plugin.review.model.dto.ReviewResponseDto;
import com.kh.plugin.review.model.dto.ReviewSaveDto;
import com.kh.plugin.review.model.vo.Review;
import com.kh.plugin.station.model.dto.StationDetailResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
	
	private final ReviewMapper reviewMapper;
	private final Pagination pagination;
	
	// 충전소 상세보기 리뷰조회
	@Transactional
	public StationDetailResponse getReviews(String stationNo, CustomUserDetails user) {
		StationDetailResponse stations = reviewMapper.getReviews(stationNo, user);
		if(stations == null) {
			throw new BoardNotFoundException("후기가 존재하지 않습니다.");
		}
		return stations;
	}

	// 후기 전체보기
	@Transactional
	public StationDetailResponse findAll(int page, String stationNo, CustomUserDetails user) {
		PageInfo pi = pagination.getPageInfo(countReviews(stationNo), page, 5, 5);
		PagingRequestValidator.validatePage(pi);
		StationDetailResponse reviews = reviewMapper.findAll(stationNo, user, pi);
		return reviews;
	}
	
	// 후기 작성하기
	@Transactional
	public Void saveReview(String stationNo, CustomUserDetails user, ReviewSaveDto review) {
		
		Review reviewEntity = Review.builder().userId(user.getUsername())
											  .stationNo(stationNo)
											  .reviewTitle(review.getReviewTitle())
											  .reviewContent(review.getReviewContent())
											  .rating(review.getRating())
											  .build();
		reviewMapper.saveReview(reviewEntity);
		return null;
	}
	
	// 후기 수정하기
	@Transactional
	public Void updateReview(String stationNo, CustomUserDetails user, ReviewSaveDto review) {
		checkId(user, review.getReviewNo());
		
		Review reviewEntity = Review.builder().reviewNo(review.getReviewNo())
											  .userId(user.getUsername())
											  .stationNo(stationNo)
											  .reviewTitle(review.getReviewTitle())
											  .reviewContent(review.getReviewContent())
											  .rating(review.getRating())
											  .build();
		reviewMapper.updateReview(reviewEntity);
		return null;
	}
	
	// 후기 삭제하기
	@Transactional
	public void deleteReview(String stationNo, Long reviewNo, CustomUserDetails user) {
		checkId(user, reviewNo);
		reviewMapper.deleteReview(reviewNo);
	}
	
	// 충전소 후기 갯수 확인
	private int countReviews(String stationNo) {
		return reviewMapper.countReviews(stationNo);
	}
	
	// 후기번호로 후기 조회하기
	private ReviewResponseDto findByReviewNo(Long reviewNo) {
		return reviewMapper.findByReviewNo(reviewNo);
	}
	
	// 메서드 수정 - 아이디와 후기 번호로 작성자 확인
	private void checkId(CustomUserDetails user, Long reviewNo) {
		if(!user.getUsername().equals(findByReviewNo(reviewNo).getUserId())) {
			throw new IdMismatchException("작성자와 일치하지 않습니다.");
		}		
	}



	
	



}
