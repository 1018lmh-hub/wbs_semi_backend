package com.kh.plugin.inquirycomment.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.exception.BoardNotFoundException;
import com.kh.plugin.exception.IdMismatchException;
import com.kh.plugin.exception.InvalidParameterException;
import com.kh.plugin.inquiryboard.model.dao.InquiryBoardMapper;
import com.kh.plugin.inquiryboard.model.dto.InquiryBoardResponseDto;
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
	private final InquiryBoardMapper inquiryBoardMapper;

	
	// 댓글 조회
	@Transactional
	public List<InquiryCommentDto> findInquiryComment(Long inquiryNo) {
		existsByInquiryNo(inquiryNo);
		return inquiryCommentMapper.findInquiryComment(inquiryNo);
	}
	
	//댓글 작성
	@Transactional
	public Void saveInquiryComment(CustomUserDetails user, Long inquiryNo, InquiryCommentRequestDto inquiry) {
		
		existsByInquiryNo(inquiryNo);
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
		
		existsByInquiryNo(inquiryNo);
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
		
		existsByInquiryNo(inquiryNo);	
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
	
	// 작성자 검증
	private void checkWriter(Long commentNo, String userId, String writer) {
		if(!userId.equals(writer)) {
			throw new IdMismatchException("작성자가 일치하지 않습니다.");
		}
	}
	
	// 문의글 존재여부 확인	
	private void existsByInquiryNo(Long inquiryNo) {
		if(!(inquiryBoardMapper.existsByInquiryNo(inquiryNo))) {
			throw new BoardNotFoundException ("게시글이 존재하지 않습니다");
		}
	}

}
