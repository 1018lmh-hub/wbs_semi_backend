package com.kh.plugin.inquerycomment.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class InquiryCommentRequestDto {

	private Long commentNo;
	@NotBlank(message = "내용을 입력해주세요.")
	@Size(min=2, max=500, message = "내용은 2~500자까지 입력이 가능합니다.")
	private String commentContent;
	
}
