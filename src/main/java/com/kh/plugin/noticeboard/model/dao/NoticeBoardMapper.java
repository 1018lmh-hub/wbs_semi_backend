package com.kh.plugin.noticeboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.noticeboard.model.dto.NoticeBoardResponseDto;
import com.kh.plugin.noticeboard.model.vo.NoticeBoard;

@Mapper
public interface NoticeBoardMapper {

	List<NoticeBoardResponseDto> findAll(PageInfo pi);

	int countNotices();
	
	boolean existsByNoticeNo(Long noticeNo);

	int increaseCount(Long noticeNo);

	NoticeBoardResponseDto findByNoticeNo(Long noticeNo);

	int saveNotice(NoticeBoard noticeBoard);

	int updateNotice(NoticeBoard noticeBoard);

	

	
	
}
