package com.kh.plugin.review.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.review.model.dto.ReviewResponseDto;
import com.kh.plugin.review.model.dto.ReviewSaveDto;
import com.kh.plugin.review.model.vo.Review;
import com.kh.plugin.station.model.dto.StationDetailResponse;

@Mapper
public interface ReviewMapper {

	// 충전소 상세보기 리뷰 가져오기
	StationDetailResponse getReviews(@Param(value = "stationNo") String stationNo, @Param(value = "user")CustomUserDetails user);

	// 충전소 리뷰 갯수 확인
	int countReviews(String stationNo);

	// 리뷰 전체보기
	StationDetailResponse findAll(@Param(value = "stationNo")String stationNo, @Param(value = "user")CustomUserDetails user, @Param(value = "pi")PageInfo pi);

	// 리뷰 작성하기
	int saveReview(Review reviewEntity);

	ReviewResponseDto findByReviewNo(Long reviewNo);

	int updateReview(@Param(value = "stationNo")String stationNo, @Param(value = "review")ReviewSaveDto review);

	int deleteReview(@Param(value = "stationNo")String stationNo, @Param(value = "reviewNo")Long reviewNo);

}
