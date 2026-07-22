package com.kh.plugin.station.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.review.model.service.ReviewService;
import com.kh.plugin.station.model.dto.StationDetailResponse;
import com.kh.plugin.station.model.service.StationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
public class StationController {

	private final StationService stationService;
	private final ReviewService reviewService;
		
	@GetMapping	
	public ResponseEntity<ApiResponse<String>> getStations(){
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(stationService.getStations()));
	}
	
	@GetMapping("/{stationNo}")
	public ResponseEntity<ApiResponse<StationDetailResponse>> getStation(@PathVariable(value = "stationNo") String stationNo, @AuthenticationPrincipal CustomUserDetails user){
		StationDetailResponse reviews = reviewService.getReviews(stationNo, user);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(reviews));
	}
	
}
