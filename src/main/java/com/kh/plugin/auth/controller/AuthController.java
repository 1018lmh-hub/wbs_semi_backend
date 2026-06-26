package com.kh.plugin.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.auth.model.dto.LoginRequestDto;
import com.kh.plugin.auth.model.dto.LoginResponseDto;
import com.kh.plugin.auth.model.service.AuthService;
import com.kh.plugin.common.model.vo.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto lrd){
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(authService.login(lrd)));
	}
	
	
//	@PostMapping("/refresh")
//	public ResponseEntity<ApiResponse<LoginResponseDto>> refresh(@RequestBody Map<String, String>request){
//		String refreshToken = request.get("refreshToken");
//		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(authService.refresh(refreshToken)));
//	}
//	
//	@PostMapping("/logout")
//	public ResponseEntity<ApiResponse<Void>> logout(@RequestParam String userId){
//		authService.logout()
//		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null));
//	}
	


}
