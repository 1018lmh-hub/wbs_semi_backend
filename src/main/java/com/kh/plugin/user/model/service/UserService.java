package com.kh.plugin.user.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.exception.DuplicatedUserIdException;
import com.kh.plugin.file.model.service.FileService;
import com.kh.plugin.file.model.vo.AttachedFile;
import com.kh.plugin.user.model.dao.UserMapper;
import com.kh.plugin.user.model.dto.UserDto;
import com.kh.plugin.user.model.dto.UserSignUpDto;
import com.kh.plugin.user.model.vo.Profile;
import com.kh.plugin.user.model.vo.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;
	private final FileService fileService;
	private final PasswordEncoder passwordEncoder;

	// 회원가입
	@Transactional
	public Void signUp(UserSignUpDto user, MultipartFile file) {
		
        User userEntity = User.builder().userId(validateDuplicateUserId(user.getUserId()))
        								.userPwd(encodePassword(user.getUserPwd()))
        								.nickname(validateDuplicateNickname(user.getNickname()))
        								.build();
		
		Profile profileEntity = Profile.builder().userId(userEntity.getUserId())
												 .originProfileName(file != null ? file.getOriginalFilename() : null)
												 .changeProfileName(fileService.store(AttachedFile.from(file)))
												 .build();
		
		userMapper.signUp(userEntity, profileEntity);
		return null;
		
	}
	
	// 유저아이디로 프로필사진 가져오기
	@Transactional
	public UserDto findProfileByUserId(CustomUserDetails user) {
		return userMapper.findProfileByUserId(user.getUsername());
	}
	
	// 아이디 중복체크
	private String validateDuplicateUserId(String userId) {
		if(userMapper.validateDuplicateUserId(userId) == 1) {
			throw new DuplicatedUserIdException("사용중인 아이디입니다.");
		}
		return userId;
	}
	
	// 별명 중복체크
	private String validateDuplicateNickname(String nickname) {
		if(userMapper.validateDuplicateUserNickname(nickname) == 1) {
			throw new DuplicatedUserIdException("사용중인 별명입니다.");
		}
		return nickname;
	}
	
	// 비밀번호 인코딩
	private String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	
	

}
