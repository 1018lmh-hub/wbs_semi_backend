package com.kh.plugin.auth.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.plugin.user.model.dto.UserDto;

@Mapper
public interface AuthMapper {

	@Select("""
				SELECT
				       USER_ID,
				       USER_PWD,
				       NICKNAME,
				       ROLE,
				       STATUS
				  FROM
				       PLU_USER
				 WHERE
				       STATUS = 'Y'
				   AND
				       USER_ID = #{username}               
			""")
	UserDto loadUser(String username);
	
}
