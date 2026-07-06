package com.kh.plugin.inquirycomment.model.dto;

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
public class InquiryCommentDto {

	private Long commentNo;
	private String userId;
	private String nickname;
	private Long inquiryNo;
	private String commentContent;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private String status;
	
}
