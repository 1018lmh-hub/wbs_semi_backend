package com.kh.plugin.noticeboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.kh.plugin.noticeboard.model.dto.NoticeBoardResponseDto;
import com.kh.plugin.noticeboard.model.dto.SaveNoticeBoardDto;
import com.kh.plugin.noticeboard.model.service.NoticeBoardService;
import com.kh.plugin.review.model.dto.ReviewSaveDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeBoardController {
	
	private final NoticeBoardService noticeBoardService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<NoticeBoardResponseDto>>> findAll(@RequestParam(name="page", defaultValue = "1") int page){
		List<NoticeBoardResponseDto> notices = noticeBoardService.findAll(page);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(notices));
	}
	
	@GetMapping("/{noticeNo}")
	public ResponseEntity<ApiResponse<NoticeBoardResponseDto>> findByNoticeNo(@PathVariable(value="noticeNo") Long noticeNo){
		NoticeBoardResponseDto notices = noticeBoardService.findByNoticeNo(noticeNo);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(notices));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> saveNotice(@Valid @RequestBody SaveNoticeBoardDto board, @AuthenticationPrincipal CustomUserDetails user){
		noticeBoardService.saveNotice(board, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(null));
	}
	
	@GetMapping("/{noticeNo}")
	public ResponseEntity<ApiResponse<Void>> updateNotice(@PathVariable(value="noticeNo") Long noticeNo, @Valid @RequestBody SaveNoticeBoardDto board, @AuthenticationPrincipal CustomUserDetails user){
		noticeBoardService.updateNotice(noticeNo, board, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(null));
	} 
	

	
	

}
