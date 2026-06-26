package com.kh.plugin.user.model.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class User {

	private String userId;
	private String userPwd;
	private String nickname;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private String role;
	private String status;
	
}
