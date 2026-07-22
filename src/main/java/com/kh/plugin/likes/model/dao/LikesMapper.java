package com.kh.plugin.likes.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.plugin.likes.model.dto.LikeDto;
import com.kh.plugin.likes.model.vo.Like;

@Mapper
public interface LikesMapper {


	LikeDto findLike(@Param(value = "userId") String userId, @Param(value = "reviewNo")Long reviewNo);

	int saveLike(Like likeEntity);

	int deleteLike(@Param(value = "reviewNo")Long reviewNo, @Param(value = "userId")String username);
	
	

}
