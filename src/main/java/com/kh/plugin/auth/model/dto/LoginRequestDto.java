package com.kh.plugin.auth.model.dto;

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
public class LoginRequestDto {

	@NotBlank(message="아이디를 입력해주세요.")
	@Size(min=5, max=12, message="아이디는 5~12자만 사용할 수 있습니다.")
	@Pattern(regexp = "^[a-zA-Z0-9_]*$", message="아이디는 알파벳, 숫자, _만 사용가능합니다.")
	private String userId;
	@NotBlank(message="비밀번호를 입력해주세요.")
	@Size(min=8, max=20, message="비밀번호는 8~20자만 사용할 수 있습니다.")
	@Pattern(regexp = "^[a-zA-Z0-9@$!%*#?&]*$", message="비밀번호는 알파벳, 숫자, @$!%*#?&만 사용가능합니다.")
	private String userPwd;
	
}
