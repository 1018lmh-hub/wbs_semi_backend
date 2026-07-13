package com.kh.plugin.auth.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.kh.plugin.auth.model.dto.LoginRequestDto;
import com.kh.plugin.auth.model.dto.LoginResponseDto;
import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.exception.CustomAuthenticationException;
import com.kh.plugin.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	public LoginResponseDto login(LoginRequestDto lrd) {
		Authentication auth = null; 
		try {
			auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(lrd.getUserId(), lrd.getUserPwd()));
		} catch(AuthenticationException e) {
			throw new CustomAuthenticationException("아이디 또는 비밀번호가 확인해주세요.");
		}
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		Map<String, String> tokens = tokenService.getTokens(user);
		return LoginResponseDto.builder().userId(user.getUsername())
										 .nickname(user.getNickname())
				                         .role(user.getAuthorities().toString())
				                         .accessToken(tokens.get("accessToken"))
				                         .refreshToken(tokens.get("refreshToken"))
				                         .build();
	}

}
