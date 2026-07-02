package com.kh.plugin.noticeboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.noticeboard.model.dto.NoticeBoardResponseDto;
import com.kh.plugin.noticeboard.model.service.NoticeBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeBoardController {
	
	private final NoticeBoardService noticeBoardService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<NoticeBoardResponseDto>>> findAll(@RequestParam(value="page", defaultValue = "1") int page){
		List<NoticeBoardResponseDto> notices = noticeBoardService.findAll(page);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(notices));
	}

}
