package com.kh.plugin.inquiryboard.model.dto;

import java.util.List;

import com.kh.plugin.common.model.dto.PageInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InquiryBoardResponseAndPageInfo {
	
	private List<InquiryBoardResponseDto> inquirys;
	private PageInfo pageInfo;

}
