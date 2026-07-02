package com.kh.plugin.noticeboard.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.common.util.Pagination;
import com.kh.plugin.noticeboard.model.dao.NoticeBoardMapper;
import com.kh.plugin.noticeboard.model.dto.NoticeBoardResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeBoardService {
	
	private final NoticeBoardMapper noticeBoardMapper;
	private final Pagination pagination;
	
	@Transactional
	public List<NoticeBoardResponseDto> findAll(int page) {
		PageInfo pi = pagination.getPageInfo(countNotices(), page, 5, 5);
		List<NoticeBoardResponseDto> notices = noticeBoardMapper.findAll(pi);
		return notices;
	}
	
	
	private int countNotices() {
		return noticeBoardMapper.countNotices();
	}

}
