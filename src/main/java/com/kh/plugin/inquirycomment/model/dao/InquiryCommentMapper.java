package com.kh.plugin.inquirycomment.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.plugin.inquirycomment.model.dto.InquiryCommentDto;
import com.kh.plugin.inquirycomment.model.vo.InquiryComment;

@Mapper
public interface InquiryCommentMapper {

	// 댓글 작성
	int saveInquiryComment(InquiryComment inquiryCommentEntity);

	// 댓글 번호로 댓글 조회하기
	InquiryCommentDto findCommentByCommentNo(Long commentNo);

}
