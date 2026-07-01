package com.kh.plugin.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class UpdateRequestDto {

	@NotBlank(message="별명을 입력해주세요.")
	@Size(min =2, max=10, message="별명은 2~10자만 사용할 수 있습니다.")
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]*$", message="별명은 알파벳, 숫자, 한글만 사용가능합니다.")
	private String newNickname;
	
}
