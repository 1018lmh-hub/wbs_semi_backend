package com.kh.plugin.user.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Profile {

	private String userId;
	private String originProfileName;
	private String changeProfileName;
}
