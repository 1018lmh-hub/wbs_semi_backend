package com.kh.plugin.noticeboard.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeBoardResponseDto {

	private Long noticeNo;
	private String userId;
	private String noticeTitle;
	private String noticeContent;
	private Integer count;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
}
