package com.kh.plugin.user.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.exception.DuplicatedUserIdException;
import com.kh.plugin.exception.FileDeleteFailedException;
import com.kh.plugin.exception.PasswordMismatchException;
import com.kh.plugin.file.model.service.FileService;
import com.kh.plugin.file.model.vo.AttachedFile;
import com.kh.plugin.user.model.dao.UserMapper;
import com.kh.plugin.user.model.dto.DeleteUserRequestDto;
import com.kh.plugin.user.model.dto.UpdatePwdRequestDto;
import com.kh.plugin.user.model.dto.UpdateRequestDto;
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
												 .originProfileName(file != null && !file.isEmpty() ? file.getOriginalFilename() : null)
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
	
	// 회원정보 수정
	@Transactional
	public Void updateUserInfo(CustomUserDetails user, UpdateRequestDto newNickname) {

		User userEntity = User.builder().userId(user.getUsername())
										.nickname(validateDuplicateNickname(newNickname.getNewNickname()))
										.build();
		
		userMapper.updateUserInfo(userEntity);
		return null;
	}
	
	// 프로필 수정
	@Transactional
	public Void updateUserProfile(CustomUserDetails user, MultipartFile file) {

		UserDto dbUser = findUserByUserId(user.getUsername());
		// 파일 null 체크, rename, 파일형식 확인
		Profile profileEntity = Profile.builder().userId(user.getUsername())
				 					   .originProfileName(file != null && !file.isEmpty() ? file.getOriginalFilename() : null)
				 					   .changeProfileName(fileService.store(AttachedFile.from(file)))
				 					   .build();
		userMapper.updateUserProfile(profileEntity);
		deleteProfileFile(dbUser.getChangeProfileName());
		
		return null;
	}
	
	// 비밀번호 변경
	@Transactional
	public Void updateUserPwd(CustomUserDetails user, UpdatePwdRequestDto newPwd) {

		// 유저 아이디로 비밀번호를 가져와서 기존의 비밀번호와 매칭
		UserDto dbUser = findUserByUserId(user.getUsername());
		User userEntity = User.builder().userId(dbUser.getUserId())
										.userPwd(encodePassword(newPwd.getNewPwd()))
										.build();
		userMapper.updateUserPwd(userEntity);
		checkPassword(newPwd.getUserPwd(), dbUser.getUserPwd());
		return null;
	}
	
	// 회원탈퇴
	@Transactional
	public void deleteUser(CustomUserDetails user, DeleteUserRequestDto deleteUserRequest) {
		
		// 유저 아이디로 비밀번호를 가져와서 기존의 비밀번호와 매칭
		UserDto dbUser = findUserByUserId(user.getUsername());
		checkPassword(deleteUserRequest.getUserPwd(), dbUser.getUserPwd());
		userMapper.deleteUser(dbUser);
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
	
	// 유저 아이디로 유저 정보 가져오기
	private UserDto findUserByUserId(String userId) {
		return userMapper.findUserByUserId(userId);
	}
	
	// 인코딩 비밀번호 확인하는 메서드
	private void checkPassword(String rawPassword, String encodedPassword) {
		if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
			throw new PasswordMismatchException("기존 비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
		}
	}

	// 수정 후 기존 파일 지우는 메서드
	private void deleteProfileFile(String profileName) {
		if(profileName == null) {
			return;
		} 
		Path filePath = Paths.get(profileName.substring(profileName.lastIndexOf("/") + 1));
		try {
			Files.delete(filePath);
		} catch(IOException e) {
			throw new FileDeleteFailedException("파일 삭제중 오류가 발생했습니다.");
		}
	}
	

}
