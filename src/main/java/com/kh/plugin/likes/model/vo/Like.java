package com.kh.plugin.likes.model.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Like {
	
	private Long reviewNo;
	private String userId;
	private LocalDateTime createDate;
	
}
