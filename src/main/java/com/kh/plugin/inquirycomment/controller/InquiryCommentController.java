package com.kh.plugin.inquirycomment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.inquirycomment.model.dto.InquiryCommentRequestDto;
import com.kh.plugin.inquirycomment.model.service.InquiryCommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/inquirys/{inquiryNo}/inquirycomments")
@RequiredArgsConstructor
public class InquiryCommentController {

	private final InquiryCommentService inquiryCommentService;
	
	// 댓글 작성
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> saveInquiryComment(@AuthenticationPrincipal CustomUserDetails user, @PathVariable(value = "inquiryNo") Long inquiryNo, @Valid @RequestBody InquiryCommentRequestDto inquiry){
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(inquiryCommentService.saveInquiryComment(user, inquiryNo, inquiry)));
	}
	
	// 댓글 수정
	@PatchMapping("/{commentNo}")
	public ResponseEntity<ApiResponse<Void>> updateInquiryComment(@AuthenticationPrincipal CustomUserDetails user, @PathVariable(value = "inquiryNo") Long inquiryNo, @Valid @RequestBody InquiryCommentRequestDto inquiry, @PathVariable(value = "commentNo") Long commentNo){
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(inquiryCommentService.updateInquiryComment(user, inquiryNo, inquiry, commentNo)));
	}
	
}
