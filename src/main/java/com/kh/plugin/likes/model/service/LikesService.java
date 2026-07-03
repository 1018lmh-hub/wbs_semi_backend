package com.kh.plugin.likes.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.exception.InvalidLikeException;
import com.kh.plugin.likes.model.dao.LikesMapper;
import com.kh.plugin.likes.model.dto.LikeDto;
import com.kh.plugin.likes.model.vo.Like;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikesService {

	private final LikesMapper likesMapper;
	
	// 좋아요 추가
	@Transactional
	public Void saveLike(CustomUserDetails user, Long reviewNo) {
		
		LikeDto dbLike = findLike(user.getUsername(), reviewNo);
		if(dbLike != null) {
			throw new InvalidLikeException("이미 좋아요한 후기입니다.");
		}
		Like likeEntity = Like.builder().
		return null;
	}

	// 좋아요 취소
	@Transactional
	public void deleteLike(CustomUserDetails user, Long reviewNo) {
		
	}
	
	private LikeDto findLike(String userId, Long reviewNo) {
		return likesMapper.findLike(userId, reviewNo);
	}

}
