package com.kh.plugin.inquiryboard.model.service;

import org.springframework.stereotype.Service;

import com.kh.plugin.common.util.Pagination;
import com.kh.plugin.inquiryboard.model.dao.InquiryBoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InquiryBoardService {

	private final InquiryBoardMapper inquiryBoardMapper;
	private final Pagination pagination;

}
