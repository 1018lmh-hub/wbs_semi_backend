package com.kh.plugin.inquerycomment.model.service;

import org.springframework.stereotype.Service;

import com.kh.plugin.auth.model.vo.CustomUserDetails;
import com.kh.plugin.inquerycomment.model.dto.InquiryCommentRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InquiryCommentService {

	
	public Void saveInquiryComment(CustomUserDetails user, Long inquirieNo, InquiryCommentRequestDto inquiry) {
		
		return null;
	}

}
