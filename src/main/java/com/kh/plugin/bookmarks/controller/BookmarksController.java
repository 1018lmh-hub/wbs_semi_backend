package com.kh.plugin.bookmarks.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.bookmarks.model.dto.BookmarksDto;
import com.kh.plugin.bookmarks.model.service.BookmarksService;
import com.kh.plugin.common.model.vo.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
public class BookmarksController {

	private final BookmarksService bookmarksService;
	
	// 북마크 추가
	@PostMapping("/{stationNo}/bookmarks")
	public ResponseEntity<ApiResponse<Void>> saveBookmark(@AuthenticationPrincipal CustomUserDetails user, @PathVariable(value = "stationNo") String stationNo){
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(bookmarksService.saveBookmark(user, stationNo)));
	}
	
	// 북마크 취소
	@DeleteMapping("/{stationNo}/bookmarks")
	public ResponseEntity<ApiResponse<Void>> deleteBookmark(@AuthenticationPrincipal CustomUserDetails user, @PathVariable(value = "stationNo") String stationNo){
		bookmarksService.deleteBookmark(user, stationNo);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.noContent());
	}
	
	// 북마크 검색
	@GetMapping("/bookmarks")
	public ResponseEntity<ApiResponse<List<BookmarksDto>>> findBookmarksList(@AuthenticationPrincipal CustomUserDetails user){
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(bookmarksService.findBookmarksList(user)));
	}
	
}
