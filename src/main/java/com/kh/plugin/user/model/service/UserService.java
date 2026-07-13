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
import com.kh.plugin.exception.ProfileFileNotFoundException;
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
	
	@Transactional
	public UserDto findProfileByUserId(CustomUserDetails user) {
		return userMapper.findProfileByUserId(user.getUsername());
	}
	
	@Transactional
	public Void updateUserInfo(CustomUserDetails user, UpdateRequestDto newNickname) {
		User userEntity = User.builder().userId(user.getUsername())
										.nickname(validateDuplicateNickname(newNickname.getNewNickname()))
										.build();
		userMapper.updateUserInfo(userEntity);
		return null;
	}
	
	@Transactional
	public Void updateUserProfile(CustomUserDetails user, MultipartFile file) {
		UserDto dbUser = findUserByUserId(user.getUsername());
		Profile profileEntity = Profile.builder().userId(user.getUsername())
				 					   .originProfileName(file != null && !file.isEmpty() ? file.getOriginalFilename() : null)
				 					   .changeProfileName(fileService.store(AttachedFile.from(file)))
				 					   .build();
		userMapper.updateUserProfile(profileEntity);
		deleteProfileFile(dbUser.getChangeProfileName());
		return null;
	}
	
	@Transactional
	public void deleteProfile(CustomUserDetails user) {
		UserDto dbUser = findUserByUserId(user.getUsername());
		if(dbUser.getChangeProfileName() == null) {
			throw new ProfileFileNotFoundException("프로필 파일이 존재하지 않습니다.");
		}
		userMapper.deleteProfile(user.getUsername());
		deleteProfileFile(dbUser.getChangeProfileName());
	}
	
	@Transactional
	public Void updateUserPwd(CustomUserDetails user, UpdatePwdRequestDto newPwd) {
		UserDto dbUser = findUserByUserId(user.getUsername());
		User userEntity = User.builder().userId(dbUser.getUserId())
										.userPwd(encodePassword(newPwd.getNewPwd()))
										.build();
		userMapper.updateUserPwd(userEntity);
		checkPassword(newPwd.getUserPwd(), dbUser.getUserPwd());
		return null;
	}
	
	@Transactional
	public void deleteUser(CustomUserDetails user, DeleteUserRequestDto deleteUserRequest) {
		UserDto dbUser = findUserByUserId(user.getUsername());
		checkPassword(deleteUserRequest.getUserPwd(), dbUser.getUserPwd());
		userMapper.deleteUser(dbUser);
	}
	
	private String validateDuplicateUserId(String userId) {
		if(userMapper.validateDuplicateUserId(userId) == 1) {
			throw new DuplicatedUserIdException("사용중인 아이디입니다.");
		}
		return userId;
	}
	
	private String validateDuplicateNickname(String nickname) {
		if(userMapper.validateDuplicateUserNickname(nickname) == 1) {
			throw new DuplicatedUserIdException("사용중인 별명입니다.");
		}
		return nickname;
	}
	
	private String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
	
	private UserDto findUserByUserId(String userId) {
		return userMapper.findUserByUserId(userId);
	}
	
	private void checkPassword(String rawPassword, String encodedPassword) {
		if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
			throw new PasswordMismatchException("기존 비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
		}
	}

	private void deleteProfileFile(String profileName) {
		if(profileName == null) {
			return;
		} 
		Path filePath = Paths.get("uploads", profileName.substring(profileName.lastIndexOf("/") + 1));
		try {
			Files.delete(filePath);
		} catch(IOException e) {
			throw new FileDeleteFailedException("파일 삭제중 오류가 발생했습니다.");
		}
	}

}
