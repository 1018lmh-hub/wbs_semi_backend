package com.kh.plugin.common.util;

import org.springframework.stereotype.Component;

import com.kh.plugin.common.model.dto.PageInfo;

@Component
public class Pagination {

	public PageInfo getPageInfo(int listCount, int currentPage, int boardLimit, int pageLimit) {
		
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		int offset = (currentPage - 1) * boardLimit;
		
		return new PageInfo(listCount, currentPage, boardLimit, pageLimit, maxPage, startPage, endPage, offset);
	}
	
}
