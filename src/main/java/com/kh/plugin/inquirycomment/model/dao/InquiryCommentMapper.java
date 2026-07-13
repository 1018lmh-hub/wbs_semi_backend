package com.kh.plugin.inquirycomment.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.plugin.inquirycomment.model.dto.InquiryCommentDto;
import com.kh.plugin.inquirycomment.model.vo.InquiryComment;

@Mapper
public interface InquiryCommentMapper {

	int saveInquiryComment(InquiryComment inquiryCommentEntity);

	InquiryCommentDto findCommentByCommentNo(Long commentNo);

	int updateInquiryComment(InquiryComment commentEntity);

	int deleteInquiryComment(Long commentNo);

	List<InquiryCommentDto> findInquiryComment(Long inquiryNo);

}
