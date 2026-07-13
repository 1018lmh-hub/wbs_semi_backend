package com.kh.plugin.token.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.exception.CustomAuthenticationException;
import com.kh.plugin.token.model.dao.TokenMapper;
import com.kh.plugin.token.model.vo.RefreshToken;
import com.kh.plugin.token.util.JwtUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

	private final JwtUtil tokenUtil;
	private final TokenMapper tokenMapper;

	public Map<String, String> getTokens(CustomUserDetails user) {
		Map<String, String> tokens = createTokens(user);
		saveToken(tokens.get("refreshToken"), user.getUsername());
		return tokens;
	}
	
	private Map<String, String> createTokens(CustomUserDetails user) {
		return Map.of("accessToken", tokenUtil.getAccessToken(user),
				      "refreshToken", tokenUtil.getRefreshToken(user));
	}
	
	private void saveToken(String token, String userId) {
		RefreshToken refreshToken = RefreshToken.builder().userId(userId)
				                                          .token(token)
				                                          .expiration(System.currentTimeMillis() + (1000 * 60 * 60 * 24))
				                                          .build();
		tokenMapper.saveToken(refreshToken);
	}
	
	public void logout(String userId, String token) {
		tokenMapper.deleteToken(userId, token.replace("\"", ""));
	}
	
	public Map<String, String> tokenRotation(String refreshToken){
		RefreshToken token = tokenMapper.findByToken(refreshToken);
		if(token == null || token.getExpiration() < System.currentTimeMillis()) {
			throw new CustomAuthenticationException("유효하지 않은 토큰입니다.");
		}
		Claims claims = tokenUtil.parseJwt(token.getToken());
		String userId = claims.getSubject();
		String nickname = (String)claims.get("nickname");
		CustomUserDetails user = CustomUserDetails.builder().nickname(nickname).username(userId).build();
		Map<String, String> tokens = createTokens(user);
		saveToken(tokens.get("refreshToken"), userId);
		return tokens;
	}
}
