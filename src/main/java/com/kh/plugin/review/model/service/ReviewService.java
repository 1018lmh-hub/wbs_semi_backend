package com.kh.plugin.review.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.common.util.Pagination;
import com.kh.plugin.common.util.PagingRequestValidator;
import com.kh.plugin.exception.IdMismatchException;
import com.kh.plugin.review.model.dao.ReviewMapper;
import com.kh.plugin.review.model.dto.ReviewResponseDto;
import com.kh.plugin.review.model.dto.ReviewSaveDto;
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
		return reviewMapper.getReviews(stationNo, user);
	}

	@Transactional
	public StationDetailResponse findAll(int page, String stationNo, CustomUserDetails user) {
		PageInfo pi = pagination.getPageInfo(countReviews(stationNo), page, 5, 5);
		PagingRequestValidator.validatePage(pi);
		StationDetailResponse reviews = reviewMapper.findAll(stationNo, user, pi);
		return reviews;
	}
	
	@Transactional
	public void saveReview(String stationNo, CustomUserDetails user, ReviewSaveDto review) {
		reviewMapper.saveReview(stationNo, user, review);
	}
	
	@Transactional
	public void updateReview(String stationNo, CustomUserDetails user, ReviewSaveDto review) {
		checkId(user, review.getReviewNo());
		reviewMapper.updateReview(stationNo, review);
			
	}
	
	@Transactional
	public void deleteReview(String stationNo, Long reviewNo, CustomUserDetails user) {
		checkId(user, reviewNo);
		reviewMapper.deleteReview(stationNo, reviewNo);
	}
	
	private int countReviews(String stationNo) {
		return reviewMapper.countReviews(stationNo);
	}
	
	private ReviewResponseDto findByReviewNo(Long reviewNo) {
		return reviewMapper.findByReviewNo(reviewNo);
	}
	
	private void checkId(CustomUserDetails user, Long reviewNo) {
		if(findByReviewNo(reviewNo).getUserId() == user.getUsername()) {
			throw new IdMismatchException("작성자와 일치하지 않습니다.");
		}		
	}



	
	



}
