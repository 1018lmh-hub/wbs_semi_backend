package com.kh.plugin.noticeboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveNoticeBoardDto {

	
	private String userId;
	private String noticeTitle;
	private String noticeContent;
	
}
