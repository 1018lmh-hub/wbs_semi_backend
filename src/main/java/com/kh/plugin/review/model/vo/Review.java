package com.kh.plugin.review.model.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Review {

	private Long reviewNo;
	private String userId;
	private String stationNo;
	private String reviewTitle;
	private String reviewContent;
	private Integer rating;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private String Status;
	
}
