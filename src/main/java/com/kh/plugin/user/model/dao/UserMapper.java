package com.kh.plugin.user.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.plugin.user.model.vo.Profile;
import com.kh.plugin.user.model.vo.User;

@Mapper
public interface UserMapper {

	// 회원가입
	int signUp(@Param(value = "user")User userEntity, @Param(value = "profile")Profile profileEntity);

	// 아이디 중복체크
	int validateDuplicateUserId(String userId);

	// 별명 중복체크
	int validateDuplicateUserNickname(String nickname);
	
}
