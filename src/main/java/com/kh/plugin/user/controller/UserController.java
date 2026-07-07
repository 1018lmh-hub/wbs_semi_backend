package com.kh.plugin.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.user.model.dto.DeleteUserRequestDto;
import com.kh.plugin.user.model.dto.UpdatePwdRequestDto;
import com.kh.plugin.user.model.dto.UpdateRequestDto;
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
	
	// 회원정보 조회(profile 조회)
	@GetMapping
	public ResponseEntity<ApiResponse<UserDto>> findProfileByUserId(@AuthenticationPrincipal CustomUserDetails user){
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userService.findProfileByUserId(user)));
	}
	
	// 회원정보 수정
	@PatchMapping
	public ResponseEntity<ApiResponse<Void>> updateUserInfo(@AuthenticationPrincipal CustomUserDetails user, @Valid @RequestBody UpdateRequestDto newNickname){
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(userService.updateUserInfo(user, newNickname)));
	}
	
	// 프로필 수정
	@PatchMapping("/profile")
	public ResponseEntity<ApiResponse<Void>> updateUserProfile(@AuthenticationPrincipal CustomUserDetails user, @RequestParam(name="file") MultipartFile file){
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(userService.updateUserProfile(user, file)));
	}
	
	// 비밀번호 변경
	@PatchMapping("/password")
	public ResponseEntity<ApiResponse<Void>> updateUserPwd(@AuthenticationPrincipal CustomUserDetails user, @Valid @RequestBody UpdatePwdRequestDto newPwd){
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(userService.updateUserPwd(user, newPwd)));
	}
	
	// 회원탈퇴
	@DeleteMapping
	public ResponseEntity<ApiResponse<Void>> deleteUser(@AuthenticationPrincipal CustomUserDetails user, @Valid @RequestBody DeleteUserRequestDto deleteUserRequest){
		userService.deleteUser(user, deleteUserRequest);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.noContent());
	}

	// 프로필 삭제
	@DeleteMapping("/profile")
	public ResponseEntity<ApiResponse<Void>> deleteProfile(@AuthenticationPrincipal CustomUserDetails user){
		userService.deleteProfile(user);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.noContent());
	}
	
	
	
}
