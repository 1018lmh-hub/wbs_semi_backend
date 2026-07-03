package com.kh.plugin.inquiryboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.inquiryboard.model.dto.InquiryBoardResponseDto;
import com.kh.plugin.inquiryboard.model.dto.SaveInquiryBoardDto;
import com.kh.plugin.inquiryboard.model.service.InquiryBoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/inquirys")
@RequiredArgsConstructor
public class InquiryBoardController {
	
	private final InquiryBoardService inquiryBoardService;
	
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<InquiryBoardResponseDto>>> findAll(@RequestParam(name="page", defaultValue = "1") int page){
		List<InquiryBoardResponseDto> inquirys = inquiryBoardService.findAll(page);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(inquirys));
	}
	
	@GetMapping("/{inquiryNo}")
	public ResponseEntity<ApiResponse<InquiryBoardResponseDto>> findByInquiryNo(@PathVariable(value="inquiryNo") Long inquiryNo){
		InquiryBoardResponseDto inquirys = inquiryBoardService.findByInquiryNo(inquiryNo);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(inquirys));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> saveInquiry(@Valid @RequestBody SaveInquiryBoardDto board, @AuthenticationPrincipal CustomUserDetails user){
		inquiryBoardService.saveInquiry(board, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(null));
	}

}
