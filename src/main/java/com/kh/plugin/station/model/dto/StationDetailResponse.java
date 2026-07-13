package com.kh.plugin.station.model.dto;

import java.util.List;

import com.kh.plugin.bookmarks.model.dto.BookmarksDto;
import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.review.model.dto.ReviewResponseDto;

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
public class StationDetailResponse {

	private List<ReviewResponseDto> reviews;
	private Double avgRating;
	private BookmarksDto bookmark;
	private PageInfo pageInfo;
	
}