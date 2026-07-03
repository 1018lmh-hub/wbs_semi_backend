package com.kh.plugin.inquiryboard.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.common.util.Pagination;
import com.kh.plugin.common.util.PagingRequestValidator;
import com.kh.plugin.exception.InvalidParameterException;
import com.kh.plugin.inquiryboard.model.dao.InquiryBoardMapper;
import com.kh.plugin.inquiryboard.model.dto.InquiryBoardResponseDto;
import com.kh.plugin.inquiryboard.model.dto.SaveInquiryBoardDto;
import com.kh.plugin.inquiryboard.model.vo.InquiryBoard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InquiryBoardService {

	private final InquiryBoardMapper inquiryBoardMapper;
	private final Pagination pagination;
	
	@Transactional
	public List<InquiryBoardResponseDto> findAll(int page) {
		PageInfo pi = pagination.getPageInfo(countInquiry(), page, 5, 5);
		PagingRequestValidator.validatePage(pi);
		return inquiryBoardMapper.findAll(pi);
	}
	
	private int countInquiry() {
		return inquiryBoardMapper.countInquiry();
	}

	@Transactional
	public InquiryBoardResponseDto findByInquiryNo(Long inquiryNo) {
		existsByInquiryNo(inquiryNo);
		inquiryBoardMapper.increaseCount(inquiryNo);		
		return inquiryBoardMapper.findByInquiryNo(inquiryNo);
		
	}
	
	private void existsByInquiryNo(Long inquiryNo) {
		if(!(inquiryBoardMapper.existsByInquiryNo(inquiryNo))) {
			throw new InvalidParameterException ("존재하지 않는 게시글 요청입니다");
		}
	}
	
	@Transactional
	public void saveInquiry(SaveInquiryBoardDto board, CustomUserDetails user) {
		
        InquiryBoard boardEntity = InquiryBoard.builder().userId(user.getUsername())
        											     .inquiryTitle(board.getInquiryTitle())
        											     .inquiryContent(board.getInquiryContent())
													     .build();
		inquiryBoardMapper.saveInquiry(boardEntity);
		
	}

}
