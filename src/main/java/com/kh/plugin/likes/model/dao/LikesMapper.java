package com.kh.plugin.likes.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.plugin.likes.model.dto.LikeDto;

@Mapper
public interface LikesMapper {

	// 좋아요 가져오기
	LikeDto findLike(@Param(value = "userId") String userId, @Param(value = "reviewNo")Long reviewNo);
	
	

}
