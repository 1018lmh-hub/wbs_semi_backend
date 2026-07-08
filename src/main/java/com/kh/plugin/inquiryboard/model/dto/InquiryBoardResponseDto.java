package com.kh.plugin.inquiryboard.model.dto;

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
public class InquiryBoardResponseDto {
	
	private Long inquiryNo;
	private String userId;
	private String nickname;
	private String inquiryTitle;
	private String inquiryContent;
	private Integer count;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private String status;
	private boolean hasComment;


}
