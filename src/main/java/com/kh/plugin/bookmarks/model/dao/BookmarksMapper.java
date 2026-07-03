package com.kh.plugin.bookmarks.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.plugin.bookmarks.model.dto.BookmarksDto;
import com.kh.plugin.bookmarks.model.vo.Bookmark;

@Mapper
public interface BookmarksMapper {

	// 북마크 가져오기
	BookmarksDto findBookmark(@Param(value = "userId")String userId, @Param(value = "stationNo")String stationNo);

	// 북마크(즐겨찾기) 추가
	int saveBookmark(Bookmark bookmarkEntity);

	// 북마크(즐겨찾기) 취소
	int deleteBookmark(Long bookmarkNo);

}
