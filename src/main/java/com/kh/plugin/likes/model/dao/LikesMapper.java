package com.kh.plugin.likes.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.plugin.likes.model.dto.LikeDto;
import com.kh.plugin.likes.model.vo.Like;

@Mapper
public interface LikesMapper {

	// 좋아요 가져오기
	LikeDto findLike(@Param(value = "userId") String userId, @Param(value = "reviewNo")Long reviewNo);

	// 좋아요 추가
	int saveLike(Like likeEntity);

	// 좋아요 취소
	int deleteLike(@Param(value = "reviewNo")Long reviewNo, @Param(value = "userId")String username);
	
	

}
