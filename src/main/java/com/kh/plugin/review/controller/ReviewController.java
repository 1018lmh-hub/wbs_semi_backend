package com.kh.plugin.review.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.review.model.dto.ReviewSaveDto;
import com.kh.plugin.review.model.service.ReviewService;
import com.kh.plugin.station.model.dto.StationDetailResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/stations/{stationNo}/reviews")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;
	
	// 후기 전체조회
	@GetMapping
	public ResponseEntity<ApiResponse<StationDetailResponse>> findAll(@RequestParam(value="page", defaultValue = "1") int page, @PathVariable(value="stationNo") String stationNo, @AuthenticationPrincipal CustomUserDetails user){
		StationDetailResponse reviews = reviewService.findAll(page, stationNo, user);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(reviews));
	}
	
	// 후기 전체조회(최신순)
	@GetMapping("/latest")
	public ResponseEntity<ApiResponse<StationDetailResponse>> findAllLatest(@RequestParam(value="page", defaultValue = "1") int page, @PathVariable(value="stationNo") String stationNo, @AuthenticationPrincipal CustomUserDetails user){
		StationDetailResponse reviews = reviewService.findAllLatest(page, stationNo, user);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(reviews));
	}
	
	// 후기 작성
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> saveReview(@PathVariable(value="stationNo") String stationNo, @AuthenticationPrincipal CustomUserDetails user, @Valid @RequestBody ReviewSaveDto review){
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(reviewService.saveReview(stationNo, user, review)));
	}
	
	// 후기 수정
	@PatchMapping("/{reviewNo}")
	public ResponseEntity<ApiResponse<Void>> updateReview(@PathVariable(value="stationNo") String stationNo, @PathVariable(value="reviewNo") Long reviewNo, @AuthenticationPrincipal CustomUserDetails user, @Valid @RequestBody ReviewSaveDto review){
		review.setReviewNo(reviewNo);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(reviewService.updateReview(stationNo, user, review)));
	}
	
	// 후기 삭제
	@DeleteMapping("/{reviewNo}")
	public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable(value="stationNo") String stationNo, @PathVariable(value="reviewNo") Long reviewNo, @AuthenticationPrincipal CustomUserDetails user){
		reviewService.deleteReview(stationNo, reviewNo, user);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.noContent());
	}
}
