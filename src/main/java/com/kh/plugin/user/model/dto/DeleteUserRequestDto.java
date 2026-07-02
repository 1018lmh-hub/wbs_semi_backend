package com.kh.plugin.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequestDto {

	@NotBlank(message="비밀번호를 입력해주세요.")
	@Size(min=8, max=20, message="비밀번호는 8~20자만 사용할 수 있습니다.")
	@Pattern(regexp = "^[a-zA-Z0-9@$!%*#?&]*$", message="비밀번호는 알파벳, 숫자, @$!%*#?&만 사용가능합니다.")
	private String userPwd;
	
}
