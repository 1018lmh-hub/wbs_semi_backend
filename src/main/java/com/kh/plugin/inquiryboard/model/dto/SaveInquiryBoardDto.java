package com.kh.plugin.inquiryboard.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveInquiryBoardDto {
	
	private String userId;
	@NotBlank(message="제목을 입력해주세요.")
	@Size(min=1, max=100, message="제목은 1~100자만 사용할 수 있습니다.")
	private String inquiryTitle;
	@NotBlank(message="본문을 입력해주세요.")
	@Size(min=1, max=2000, message="본문은 1~2000자만 사용할 수 있습니다.")
	private String inquiryContent;

}
