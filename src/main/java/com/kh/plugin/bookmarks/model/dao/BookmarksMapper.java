package com.kh.plugin.bookmarks.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.plugin.bookmarks.model.dto.BookmarksDto;

@Mapper
public interface BookmarksMapper {

	// 북마크 가져오기
	BookmarksDto findBookmark(String userId, String stationNo);

}
