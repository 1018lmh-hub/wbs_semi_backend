package com.kh.plugin.common.util;

import com.kh.plugin.common.model.dto.PageInfo;
import com.kh.plugin.exception.InvalidParameterException;

public class PagingRequestValidator {
	
	public static void validatePage(PageInfo pi ) {
		if((pi.getMaxPage() < pi.getCurrentPage()) || pi.getCurrentPage() < 1){
			throw new InvalidParameterException ("존재하지 않는 페이지 요청입니다");  
		}
	}
}
