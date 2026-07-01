package com.kh.plugin.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.user.model.dto.UserDto;
import com.kh.plugin.user.model.dto.UserSignUpDto;
import com.kh.plugin.user.model.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	// 회원가입
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> signUp(@Valid UserSignUpDto user, @RequestParam(name="file", required=false) MultipartFile file){
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(userService.signUp(user, file)));
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<UserDto>> findProfileByUserId(@AuthenticationPrincipal CustomUserDetails user){
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userService.findProfileByUserId(user)));
	}
	
}
