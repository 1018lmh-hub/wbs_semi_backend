package com.kh.plugin.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class LoginResponseDto {

	private String userId;
	private String nickname;
	private String accessToken;
	private String refreshToken;
	private String role;
	
}
