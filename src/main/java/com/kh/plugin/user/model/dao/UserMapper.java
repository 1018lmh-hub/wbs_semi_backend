package com.kh.plugin.user.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.plugin.user.model.dto.UserDto;
import com.kh.plugin.user.model.vo.Profile;
import com.kh.plugin.user.model.vo.User;

@Mapper
public interface UserMapper {

	int signUp(@Param(value = "user")User userEntity, @Param(value = "profile")Profile profileEntity);

	int validateDuplicateUserId(String userId);

	int validateDuplicateUserNickname(String nickname);

	UserDto findProfileByUserId(String username);

	int updateUserInfo(User user);

	UserDto findUserByUserId(String userId);

	int updateUserPwd(User userEntity);

	int deleteUser(UserDto dbUser);

	int updateUserProfile(Profile profileEntity);

	int deleteProfile(String username);
	
}
