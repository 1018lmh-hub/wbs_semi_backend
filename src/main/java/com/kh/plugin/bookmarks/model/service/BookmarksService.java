package com.kh.plugin.bookmarks.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.bookmarks.model.dao.BookmarksMapper;
import com.kh.plugin.bookmarks.model.dto.BookmarksDto;
import com.kh.plugin.bookmarks.model.vo.Bookmark;
import com.kh.plugin.exception.InvalidBookmarkException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarksService {

	private final BookmarksMapper bookmarksMapper;
	
	@Transactional
	public Void saveBookmark(CustomUserDetails user, String stationNo) {
		BookmarksDto bookmark = findBookmark(user.getUsername(), stationNo);
		if(bookmark != null) {
			throw new InvalidBookmarkException("이미 즐겨찾기가 된 충전소입니다.");
		}
		Bookmark bookmarkEntity = Bookmark.builder().userId(user.getUsername())
										  .stationNo(stationNo)
										  .build();
		bookmarksMapper.saveBookmark(bookmarkEntity);
		return null;
	}
	
	@Transactional
	public Void deleteBookmark(CustomUserDetails user, String stationNo) {
		BookmarksDto bookmark = findBookmark(user.getUsername(), stationNo);
		if(bookmark == null) {
			throw new InvalidBookmarkException("잘못된 요청입니다.");
		}
		bookmarksMapper.deleteBookmark(bookmark.getBookmarkNo());
		return null;
	}
	
	@Transactional
	public List<BookmarksDto> findBookmarksList(CustomUserDetails user) {
		return bookmarksMapper.findBookmarksList(user.getUsername());
	}
	
	private BookmarksDto findBookmark(String userId, String stationNo) {
		return bookmarksMapper.findBookmark(userId, stationNo);
	}

}
