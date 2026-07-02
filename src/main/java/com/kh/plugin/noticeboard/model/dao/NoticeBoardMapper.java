package com.kh.plugin.noticeboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.noticeboard.model.dto.NoticeBoardResponseDto;

@Mapper
public interface NoticeBoardMapper {

	List<NoticeBoardResponseDto> findAll(@Param(value = "pi")PageInfo pi);

	int countNotices();

	
	
}
