package com.kh.plugin.likes.model.dto;

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
public class LikeDto {

	private Long reviewNo;
	private String userId;
	private LocalDateTime createDate;
	
}
