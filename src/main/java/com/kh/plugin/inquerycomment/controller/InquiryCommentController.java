package com.kh.plugin.inquerycomment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.inquerycomment.model.dto.InquiryCommentRequestDto;
import com.kh.plugin.inquerycomment.model.service.InquiryCommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/inquiries/{inquirieNo}")
@RequiredArgsConstructor
public class InquiryCommentController {

	private final InquiryCommentService inquiryCommentService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> saveInquiryComment(@AuthenticationPrincipal CustomUserDetails user, @PathVariable(value = "inquirieNo") Long inquirieNo, @Valid @RequestBody InquiryCommentRequestDto inquiry){
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(inquiryCommentService.saveInquiryComment(user, inquirieNo, inquiry)));
	}
	
}
