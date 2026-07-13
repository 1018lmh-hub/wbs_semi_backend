package com.kh.plugin.bookmarks.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.bookmarks.model.dto.BookmarksDto;
import com.kh.plugin.bookmarks.model.vo.Bookmark;

@Mapper
public interface BookmarksMapper {

	BookmarksDto findBookmark(@Param(value = "userId")String userId, @Param(value = "stationNo")String stationNo);

	int saveBookmark(Bookmark bookmarkEntity);

	int deleteBookmark(Long bookmarkNo);

	List<BookmarksDto> findBookmarksList(String userId);

}
