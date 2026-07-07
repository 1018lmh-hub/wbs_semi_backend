package com.kh.plugin.noticeboard.model.dto;

import java.util.List;

import com.kh.plugin.common.model.dto.PageInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeBoardResponseAndPageInfo {

	private List<NoticeBoardResponseDto> notices;
	private PageInfo pageInfo;
}
