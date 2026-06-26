package com.kh.plugin.token.model.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.plugin.token.model.vo.RefreshToken;

@Mapper
public interface TokenMapper {

	@Insert("INSERT INTO TOKEN VALUES(#{userId}, #{token}, #{expiration})")
	void saveToken(RefreshToken token);
	
	@Delete("DELETE FROM TOKEN WHERE USER_ID = #{userId} AND TOKEN = #{token}")
	void deleteToken(String userId, String token);
	
	@Select("SELECT USER_ID, TOKEN, EXPIRATION FROM TOKEN WHERE TOKEN = #{token}")
	RefreshToken findByToken(String token);
	
}
