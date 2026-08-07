package com.kh.plugin.user.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.file.model.service.FileService;
import com.kh.plugin.file.model.vo.AttachedFile;
import com.kh.plugin.user.model.dao.UserMapper;
import com.kh.plugin.user.model.dto.UpdateRequestDto;
import com.kh.plugin.user.model.dto.UserSignUpDto;
import com.kh.plugin.user.model.vo.Profile;
import com.kh.plugin.user.model.vo.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	UserMapper userMapper;
	@Mock
	FileService fileService;
	@Mock
	PasswordEncoder passwordEncoder;
	@InjectMocks
	UserService userService;
	
	@Test
	@DisplayName("프로필이 없을 때 file 이 null로 들어감")
	void save_프로필없음() {
		
		// given
		UserSignUpDto dto = new UserSignUpDto();
		dto.setUserId("userId");
		dto.setUserPwd("userpassword");
		dto.setNickname("홍길동");
		MultipartFile file = null;
		
		// when
		userService.signUp(dto, file);
		
		// then
		verify(fileService).store(AttachedFile.from(file));
		verify(userMapper).signUp(any(User.class), any(Profile.class));
		
	}
	
	@Test
	@DisplayName("프로필이 있을 때 file이 적용됨")
	void save_프로필있음() {
		UserSignUpDto dto = new UserSignUpDto();
		dto.setUserId("userId");
		dto.setUserPwd("userpassword");
		dto.setNickname("홍길동");
		MultipartFile file = new MockMultipartFile("file", "kh.jpg", "image/jpg", "가짜내용".getBytes());
		given(fileService.store(any(AttachedFile.class))).willReturn("http://localhost:8081/kh.jpg");
		
		userService.signUp(dto, file);
		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		ArgumentCaptor<Profile> captorProfile = ArgumentCaptor.forClass(Profile.class);
		verify(userMapper).signUp(captor.capture(), captorProfile.capture());
		User saved = captor.getValue();
		Profile savedProfile = captorProfile.getValue();
		assertEquals("http://localhost:8081/kh.jpg", savedProfile.getChangeProfileName());
		assertEquals("홍길동", saved.getNickname());
	}
	
	@Test
	@DisplayName("유저정보 업데이트")
	void update_유저정보() {
		CustomUserDetails user = CustomUserDetails.builder().username("user0101").nickname("shapa26").build();
		UpdateRequestDto dto = new UpdateRequestDto();
		dto.setNewNickname("shapa27");
		
		userService.updateUserInfo(user, dto);
		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		verify(userMapper).updateUserInfo(captor.capture());
		User saved = captor.getValue();
		assertEquals("shapa27", saved.getNickname());
	}
	
	
}
