package com.kh.plugin.token.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshToken {

	private String userId;
	private String token;
	private Long expiration;
	
}
