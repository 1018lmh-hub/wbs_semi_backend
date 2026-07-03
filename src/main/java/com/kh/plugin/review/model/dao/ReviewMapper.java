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

	// 충전소 상세보기 후기 조회
	StationDetailResponse getReviews(@Param(value = "stationNo") String stationNo, @Param(value = "user")CustomUserDetails user);

	// 충전소 후기 갯수 확인
	int countReviews(String stationNo);

	// 후기 전체보기
	StationDetailResponse findAll(@Param(value = "stationNo")String stationNo, @Param(value = "user")CustomUserDetails user, @Param(value = "pi")PageInfo pi);

	// 후기 작성하기
	int saveReview(Review reviewEntity);

	// 후기 번호로 후기 가져오기
	ReviewResponseDto findByReviewNo(Long reviewNo);

	// 후기 수정하기
	int updateReview(Review reviewEntity);

	int deleteReview(@Param(value = "stationNo")String stationNo, @Param(value = "reviewNo")Long reviewNo);

}
