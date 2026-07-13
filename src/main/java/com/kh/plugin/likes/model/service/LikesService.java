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
	
	@Transactional
	public Void saveLike(CustomUserDetails user, Long reviewNo) {
		
		LikeDto dbLike = findLike(user.getUsername(), reviewNo);
		if(dbLike != null) {
			throw new InvalidLikeException("이미 좋아요한 후기입니다.");
		}
		Like likeEntity = Like.builder().reviewNo(reviewNo)
				                        .userId(user.getUsername())
				                        .build();
		likesMapper.saveLike(likeEntity);
		return null;
	}

	@Transactional
	public void deleteLike(CustomUserDetails user, Long reviewNo) {
		
		LikeDto dbLike = findLike(user.getUsername(), reviewNo);
		if(dbLike == null) {
			throw new InvalidLikeException("잘못된 요청입니다.");
		}
		likesMapper.deleteLike(reviewNo, user.getUsername());
		
	}
	
	private LikeDto findLike(String userId, Long reviewNo) {
		return likesMapper.findLike(userId, reviewNo);
	}

}
