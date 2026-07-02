package com.kh.plugin.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.noticeboard.model.dto.NoticeBoardResponseDto;
import com.kh.plugin.noticeboard.model.service.NoticeBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	private final NoticeBoardService noticeBoardService;
	
	@PostMapping("/{noticeNo}")
	public ResponseEntity<ApiResponse<Void>> save(@PathVariable(value="noticeNo") Long noticeNo){
		NoticeBoardResponseDto notices = noticeBoardService.findByNoticeNo(noticeNo);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(notices));
	}
}
