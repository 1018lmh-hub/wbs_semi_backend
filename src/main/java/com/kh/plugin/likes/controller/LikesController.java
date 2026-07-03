package com.kh.plugin.likes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.likes.model.service.LikesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/stations/{stationNo}/reviews/{reviewNo}/likes")
@RequiredArgsConstructor
public class LikesController {
	
	private final LikesService likesService;

	// 좋아요 추가
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> saveLike(@AuthenticationPrincipal CustomUserDetails user, @PathVariable(value = "reviewNo") Long reviewNo){
		likesService.saveLike(user, reviewNo);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(null));
	}
	
	// 좋아요 취소
	@DeleteMapping
	public ResponseEntity<ApiResponse<Void>> deleteLike(@AuthenticationPrincipal CustomUserDetails user, @PathVariable(value = "reviewNo") Long reviewNo){
		likesService.deleteLike(user, reviewNo);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.noContent());
	}
	
}
