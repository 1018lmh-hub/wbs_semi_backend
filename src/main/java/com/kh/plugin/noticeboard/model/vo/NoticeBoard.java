package com.kh.plugin.noticeboard.model.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class NoticeBoard {

	private Long noticeNo;
	private String userId;
	private String noticeTitle;
	private String noticeContent;
	private Integer count;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private String status;
	
}
