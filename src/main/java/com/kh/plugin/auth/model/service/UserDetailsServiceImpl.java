package com.kh.plugin.auth.model.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.kh.plugin.auth.model.dao.AuthMapper;
import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.exception.UsernameNotFoundException;
import com.kh.plugin.user.model.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final AuthMapper mapper;
	
	// 아이디로 DB아이디 조회, 비밀번호 일치여부 확인
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto user = mapper.loadUser(username);
		if(user == null) {
			throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
		}
		return CustomUserDetails.builder().username(user.getUserId())
										  .password(user.getUserPwd())
										  .nickname(user.getNickname())
										  .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())))
										  .status(user.getStatus())
										  .build();
	}

}
