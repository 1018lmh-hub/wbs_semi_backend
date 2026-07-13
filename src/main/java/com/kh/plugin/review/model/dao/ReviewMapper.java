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

	StationDetailResponse getReviews(@Param(value = "stationNo") String stationNo, @Param(value = "user")CustomUserDetails user);

	int countReviews(String stationNo);

	StationDetailResponse findAll(@Param(value = "stationNo")String stationNo, @Param(value = "user")CustomUserDetails user, @Param(value = "pi")PageInfo pi);

	StationDetailResponse findAllLatest(@Param(value = "stationNo")String stationNo, @Param(value = "user")CustomUserDetails user, @Param(value = "pi")PageInfo pi);
	
	int saveReview(Review reviewEntity);

	ReviewResponseDto findByReviewNo(Long reviewNo);

	int updateReview(Review reviewEntity);

	int deleteReview(Long reviewNo);

}
