package com.kh.plugin.bookmarks.model.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Bookmark {

	private Long bookmarkNo;
	private String userId;
	private String stationNo;
	private LocalDateTime createDate;
	
}
