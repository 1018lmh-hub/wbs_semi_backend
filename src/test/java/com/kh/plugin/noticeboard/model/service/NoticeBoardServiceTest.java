package com.kh.plugin.noticeboard.model.service;

import static org.assertj.core.api.Assertions.assertThatList;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kh.plugin.common.metric.BoardViewCounter;
import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.common.util.Pagination;
import com.kh.plugin.noticeboard.model.dao.NoticeBoardMapper;
import com.kh.plugin.noticeboard.model.dto.NoticeBoardResponseAndPageInfo;
import com.kh.plugin.noticeboard.model.dto.NoticeBoardResponseDto;

@ExtendWith(MockitoExtension.class)
public class NoticeBoardServiceTest {
	
	@Mock
	NoticeBoardMapper noticeBoardMapper;
	@Mock
	Pagination pagination;
	@Mock
    BoardViewCounter boardViewCounter;
	@InjectMocks
	NoticeBoardService noticeBoardService;
	
	@Test
	@DisplayName("공지사항 전체조회")
	void findAll_페이지조회() {
		//given

		List<NoticeBoardResponseDto> notices = new ArrayList<>();
		NoticeBoardResponseDto notice = new NoticeBoardResponseDto();
		notice.setNoticeTitle("제목");
		notice.setNoticeContent("내용");
		
		for(int i=0; i<50; i++) {
			notices.add(notice);
		}
		
		PageInfo pi = new PageInfo();
		pi.setListCount(50);
		pi.setCurrentPage(1);
		pi.setBoardLimit(5);
		pi.setPageLimit(5);
		
		NoticeBoardResponseAndPageInfo returns = new NoticeBoardResponseAndPageInfo(notices, pi);
		
		//when
		Mockito.when(noticeBoardService.findAll(1)).thenReturn(returns);
		
		List<NoticeBoardResponseDto> result = noticeBoardMapper.findAll(pi);
		System.out.println(result);
		
		
		//then
		assertThatList(result).isEqualTo(notices);
	}
	

}
