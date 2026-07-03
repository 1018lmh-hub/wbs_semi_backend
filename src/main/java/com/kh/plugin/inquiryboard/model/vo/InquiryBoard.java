package com.kh.plugin.inquiryboard.model.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InquiryBoard {
	
	private Long inquiryNo;
	private String userId;
	private String inquiryTitle;
	private String inquiryContent;
	private Integer count;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private String status;

}
