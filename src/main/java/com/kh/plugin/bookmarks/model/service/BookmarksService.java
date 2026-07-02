package com.kh.plugin.bookmarks.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.bookmarks.model.dao.BookmarksMapper;
import com.kh.plugin.bookmarks.model.dto.BookmarksDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarksService {

	private final BookmarksMapper bookmarksMapper;
	
	// 북마크(즐겨찾기) 추가
	@Transactional
	public Void addBookmark(CustomUserDetails user, String stationNo) {
		
		
		
		// user 아이디와 stationNo으로 북마크 땡겨오기
		// 있으면 이미 즐겨찾기한 충전소입니다 예외발생.
		
		// 없으면 북마크 추가 --
		
		
		return null;
		
	}
	
	// 북마크(즐겨찾기) 취소
	@Transactional
	public Void cancelBookmark(CustomUserDetails user, String stationNo) {
		
		// user 아이디와 stationNo 으로 북마크 땡겨오기
		// 있으면 즐겨찾기 삭제 - 물리삭제
		
		// 없으면 잘못된 요청입니다. 예외 발생
		
		return null;
	}
	
	// user 아이디와 stationNo으로 북마크 땡켜오기
	private BookmarksDto findBookmark(String userId, String stationNo) {
		return bookmarksMapper.findBookmark(userId, stationNo);
	}

}
