package com.kh.plugin.user.model.dto;

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
public class UserDto {
	
	private String userId;
	private String userPwd;
	private String nickname;
	private String originProfileName;
	private String changeProfileName;
	private LocalDateTime enrollDate;
	private LocalDateTime modifyDate;
	private String role;
	private String status;

}
