package com.kh.plugin.inquiryboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.inquiryboard.model.dto.InquiryBoardResponseDto;

@Mapper
public interface InquiryBoardMapper {

	List<InquiryBoardResponseDto> findAll(PageInfo pi);

	int countInquiry();

	boolean existsByInquiryNo(Long inquiryNo);

	int increaseCount(Long inquiryNo);

	InquiryBoardResponseDto findByInquiryNo(Long inquiryNo);
	


	

}
