package com.kh.plugin.review.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ReviewSaveDto {

	private Long reviewNo;
	@NotBlank(message = "제목을 입력해주세요.")
	@Size(min=2, max = 100, message = "제목은 2~100자 까지 입력가능합니다.")
	private String reviewTitle;
	@NotBlank(message = "내용을 입력해주세요.")
	@Size(min=2, max = 500, message = "내용은 2~500자 까지 입력가능합니다.")
	private String reviewContent;
	@NotNull(message = "별점을 입력해주세요.")
	@Min(1)
	@Max(5)
	private Integer rating;
	
}
