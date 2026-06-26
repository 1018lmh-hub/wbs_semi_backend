package com.kh.plugin.auth.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomUserDetails implements UserDetails {

	private String username;
	private String password;
	private String nickname;
	private Collection<? extends GrantedAuthority> authorities;
	private String status;
	
}
