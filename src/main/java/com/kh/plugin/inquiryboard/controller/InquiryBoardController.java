package com.kh.plugin.inquiryboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.inquiryboard.model.dto.InquiryBoardResponseDto;
import com.kh.plugin.inquiryboard.model.service.InquiryBoardService;

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
		List<InquiryBoardResponseDto> notices = inquiryBoardService.findAll(page);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(notices));
	}

}
