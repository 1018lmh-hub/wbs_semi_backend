package com.kh.plugin.inquirycomment.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.exception.BoardNotFoundException;
import com.kh.plugin.exception.IdMismatchException;
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

	
	// 댓글 조회
	@Transactional
	public void findInquiryComment(Long inquiryNo) {
		
	}
	
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

	// 댓글 수정
	@Transactional
	public Void updateInquiryComment(CustomUserDetails user, Long inquiryNo, InquiryCommentRequestDto inquiry, Long commentNo) {
		
		// InqueiryNo으로 Inquery가 있는지 확인
//		InquiryDto dbInquiry = inquiryMapper.findInquiryByInquiryNo(inquiryNo);
//		if(dbInquiry == null) {
//			throw new BoardNotFoundException("존재하지 않는 게시물입니다.");
//		}
		InquiryCommentDto dbComment = findCommentByCommentNo(commentNo);
		checkWriter(commentNo, user.getUsername(), dbComment.getUserId());
		
		InquiryComment commentEntity = InquiryComment.builder().commentNo(commentNo)
															   .commentContent(inquiry.getCommentContent())
															   .build();
		inquiryCommentMapper.updateInquiryComment(commentEntity);
		return null;
	}
	
	// 댓글 삭제
	@Transactional
	public void deleteInquiryComment(CustomUserDetails user, Long inquiryNo, Long commentNo) {
		
		// InqueiryNo으로 Inquery가 있는지 확인
//		InquiryDto dbInquiry = inquiryMapper.findInquiryByInquiryNo(inquiryNo);
//		if(dbInquiry == null) {
//			throw new BoardNotFoundException("존재하지 않는 게시물입니다.");
//		}		
		InquiryCommentDto dbComment = findCommentByCommentNo(commentNo);
		checkWriter(commentNo, user.getUsername(), dbComment.getUserId());
		inquiryCommentMapper.deleteInquiryComment(commentNo);
	}
	
	// 댓글 번호로 댓글 조회
	private InquiryCommentDto findCommentByCommentNo(Long commentNo) {
		InquiryCommentDto dbComment = inquiryCommentMapper.findCommentByCommentNo(commentNo);
		if(dbComment == null) {
			throw new BoardNotFoundException("존재하지 않는 댓글입니다.");
		}
		return dbComment;
	}
	
	private void checkWriter(Long commentNo, String userId, String writer) {
		if(!userId.equals(writer)) {
			throw new IdMismatchException("작성자가 일치하지 않습니다.");
		}
	}
	

}
