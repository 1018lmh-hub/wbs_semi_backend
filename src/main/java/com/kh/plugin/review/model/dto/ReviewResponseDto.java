package com.kh.plugin.review.model.dto;

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
public class ReviewResponseDto {

	private Long reviewNo;
	private String userId;
	private String nickname;
	private String changeProfileName;
	private String stationNo;
	private String reviewTitle;
	private String reviewContent;
	private Integer rating;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private String status;
	private Integer likeCount;
	private boolean liked;
}
