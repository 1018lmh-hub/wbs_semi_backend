package com.kh.plugin.noticeboard.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.common.util.Pagination;
import com.kh.plugin.common.util.PagingRequestValidator;
import com.kh.plugin.exception.IdMismatchException;
import com.kh.plugin.exception.InvalidParameterException;
import com.kh.plugin.noticeboard.model.dao.NoticeBoardMapper;
import com.kh.plugin.noticeboard.model.dto.NoticeBoardResponseAndPageInfo;
import com.kh.plugin.noticeboard.model.dto.NoticeBoardResponseDto;
import com.kh.plugin.noticeboard.model.dto.SaveNoticeBoardDto;
import com.kh.plugin.noticeboard.model.vo.NoticeBoard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeBoardService {
	
	private final NoticeBoardMapper noticeBoardMapper;
	private final Pagination pagination;
	
	@Transactional
	public NoticeBoardResponseAndPageInfo findAll(int page) {
		PageInfo pi = pagination.getPageInfo(countNotices(), page, 5, 5);
		PagingRequestValidator.validatePage(pi);
		List<NoticeBoardResponseDto> notices = noticeBoardMapper.findAll(pi);
		return new NoticeBoardResponseAndPageInfo(notices, pi);
	}
	
	private int countNotices() {
		return noticeBoardMapper.countNotices();
	}

	@Transactional
	public NoticeBoardResponseDto findByNoticeNo(Long noticeNo) {
		existsByNoticeNo(noticeNo);
		noticeBoardMapper.increaseCount(noticeNo);		
		return noticeBoardMapper.findByNoticeNo(noticeNo);
	}
	
	private void existsByNoticeNo(Long noticeNo) {
		if(!(noticeBoardMapper.existsByNoticeNo(noticeNo))) {
			throw new InvalidParameterException ("존재하지 않는 게시글 요청입니다");
		}
	}

	@Transactional
	public void saveNotice(SaveNoticeBoardDto board, CustomUserDetails user) {
        NoticeBoard boardEntity = NoticeBoard.builder().userId(user.getUsername())
        											   .noticeTitle(board.getNoticeTitle())
        											   .noticeContent(board.getNoticeContent())
													   .build();
		noticeBoardMapper.saveNotice(boardEntity);
	}
	
	@Transactional
	public void updateNotice(Long noticeNo, SaveNoticeBoardDto board, CustomUserDetails user) {
		existsByNoticeNo(noticeNo);
		checkId(user, noticeNo);
        NoticeBoard boardEntity = NoticeBoard.builder().noticeNo(noticeNo)
        											   .userId(user.getUsername())
        											   .noticeTitle(board.getNoticeTitle())
													   .noticeContent(board.getNoticeContent())
													   .build();
        noticeBoardMapper.updateNotice(boardEntity);
	}
	
	private void checkId(CustomUserDetails user, Long noticeNo) {
		if (!(user.getUsername()).equals((noticeBoardMapper.findByNoticeNo(noticeNo)).getUserId())) {
			throw new IdMismatchException("작성자와 일치하지 않습니다.");
		}
	}

	@Transactional
	public void deleteNotice(Long noticeNo, CustomUserDetails user) {
		existsByNoticeNo(noticeNo);
		checkId(user, noticeNo);
		noticeBoardMapper.deleteNotice(noticeNo);
	}

}
