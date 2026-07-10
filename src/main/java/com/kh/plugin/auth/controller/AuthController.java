package com.kh.plugin.auth.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.auth.model.dto.LoginRequestDto;
import com.kh.plugin.auth.model.dto.LoginResponseDto;
import com.kh.plugin.auth.model.service.AuthService;
import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.token.model.service.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	private final TokenService tokenService;
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto lrd){
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(authService.login(lrd)));
	}

	// 로그아웃
	@PostMapping("/logout")
	public ResponseEntity<ApiResponse<Void>> logout(@AuthenticationPrincipal CustomUserDetails user, @RequestBody String refreshToken){
		tokenService.logout(user.getUsername(), refreshToken);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null));
	}
	
	// 토큰 재발급
	@PostMapping("/refresh")
	public ResponseEntity<ApiResponse<Map<String, String>>> refresh(@RequestBody Map<String, String> request){
		String refreshToken = request.get("refreshToken");
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(tokenService.tokenRotation(refreshToken)));
	}
	
}
