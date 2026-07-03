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
import com.kh.plugin.noticeboard.model.dto.NoticeBoardResponseDto;
import com.kh.plugin.noticeboard.model.dto.SaveNoticeBoardDto;
import com.kh.plugin.noticeboard.model.vo.NoticeBoard;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeBoardService {
	
	private final NoticeBoardMapper noticeBoardMapper;
	private final Pagination pagination;
	
	// 공지사항 게시글 전체조회
	@Transactional
	public List<NoticeBoardResponseDto> findAll(int page) {
		PageInfo pi = pagination.getPageInfo(countNotices(), page, 5, 5);
		PagingRequestValidator.validatePage(pi);
		List<NoticeBoardResponseDto> notices = noticeBoardMapper.findAll(pi);
		return notices;
	}
	
	//공지사항 게시글 수 확인
	private int countNotices() {
		return noticeBoardMapper.countNotices();
	}

	// 공지사항 게시글 상세조회
	@Transactional
	public NoticeBoardResponseDto findByNoticeNo(Long noticeNo) {
		
		existsByNoticeNo(noticeNo);
		noticeBoardMapper.increaseCount(noticeNo);		
		return noticeBoardMapper.findByBoardNo(noticeNo);
		
	}
	
	// 게시글 존재여부를 확인하는 메서드
	private void existsByNoticeNo(Long noticeNo) {
		if(noticeBoardMapper.existsByNoticeNo(noticeNo)) {
			throw new InvalidParameterException ("존재하지 않는 게시글 요청입니다");
		}
	}
	

	// 공지사항 게시글 저장
	@Transactional
	public void saveNotice(SaveNoticeBoardDto board, CustomUserDetails user) {
		
        NoticeBoard boardEntity = NoticeBoard.builder().userId(user.getUsername())
        											   .noticeTitle(board.getNoticeTitle())
        											   .noticeContent(board.getNoticeContent())
													   .build();
		noticeBoardMapper.saveNotice(boardEntity);
		
	}
	
	// 공지사항 게시글 수정
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
	
	// 아이디 검증 내부 메서드
	private void checkId(CustomUserDetails user, Long noticeNo) {
		if (user.getUsername() != (noticeBoardMapper.findByBoardNo(noticeNo).getUserId())) {
			throw new IdMismatchException("작성자와 일치하지 않습니다.");
		}
	}



}
