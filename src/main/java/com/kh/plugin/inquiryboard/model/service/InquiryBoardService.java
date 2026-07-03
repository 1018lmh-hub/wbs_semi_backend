package com.kh.plugin.inquiryboard.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.common.util.Pagination;
import com.kh.plugin.common.util.PagingRequestValidator;
import com.kh.plugin.inquiryboard.model.dao.InquiryBoardMapper;
import com.kh.plugin.inquiryboard.model.dto.InquiryBoardResponseDto;

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
		List<InquiryBoardResponseDto> inquirys = inquiryBoardMapper.findAll(pi);
		return inquirys;
	}
	
	private int countInquiry() {
		return inquiryBoardMapper.countInquiry();
	}

}
