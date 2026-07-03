package com.kh.plugin.inquerycomment.model.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InquiryComment {

	private Long commentNo;
	private String userId;
	private Long inquiryNo;
	private String commentContent;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private String status;
	
}
