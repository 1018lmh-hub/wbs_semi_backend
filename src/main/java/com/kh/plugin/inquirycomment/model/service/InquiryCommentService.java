package com.kh.plugin.inquirycomment.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.inquirycomment.model.dao.InquiryCommentMapper;
import com.kh.plugin.inquirycomment.model.dto.InquiryCommentDto;
import com.kh.plugin.inquirycomment.model.dto.InquiryCommentRequestDto;
import com.kh.plugin.inquirycomment.model.vo.InquiryComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InquiryCommentService {

	private final InquiryCommentMapper inquiryCommentMapper;
//	private final InquiryBoardMapper inquiryMapper;
	
	//댓글 작성
	@Transactional
	public Void saveInquiryComment(CustomUserDetails user, Long inquiryNo, InquiryCommentRequestDto inquiry) {
		
		// InqueiryNo으로 Inquery가 있는지 확인
//		InquiryDto dbInquiry = inquiryMapper.findInquiryByInquiryNo(inquiryNo);
//		if(dbInquiry == null) {
//			throw new BoardNotFoundException("존재하지 않는 게시물입니다.");
//		}
		InquiryComment inquiryCommentEntity = InquiryComment.builder().userId(user.getUsername())
																	  .inquiryNo(inquiryNo)
																	  .commentContent(inquiry.getCommentContent())
																	  .build();
		inquiryCommentMapper.saveInquiryComment(inquiryCommentEntity);
		return null;
	}

	public void updateInquiryComment(CustomUserDetails user, Long inquiryNo, InquiryCommentRequestDto inquiry, Long commentNo) {
		
		// InqueiryNo으로 Inquery가 있는지 확인
//		InquiryDto dbInquiry = inquiryMapper.findInquiryByInquiryNo(inquiryNo);
//		if(dbInquiry == null) {
//			throw new BoardNotFoundException("존재하지 않는 게시물입니다.");
//		}
		
		
	}
	
	private InquiryCommentDto findCommentByCommentNo(Long commentNo) {
		return inquiryCommentMapper.findCommentByCommentNo(commentNo);
	}

}
