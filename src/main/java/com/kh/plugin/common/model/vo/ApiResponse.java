package com.kh.plugin.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

	private int code;
	private String message;
	private T data;
		
		public static <T> ApiResponse<T> success(T data){
			return new ApiResponse<>(200, "요청에 성공했습니다.", data);
		}
		
		public static <T> ApiResponse<T> success(String message, T data){
			return new ApiResponse<>(200, message, data);
		}
		
		public static <T> ApiResponse<T> created(T data){
			return new ApiResponse<>(201, "요청에 성공했습니다.", data);
		}
		
		public static <T> ApiResponse<T> created(String message, T data){
			return new ApiResponse<>(201, message, data);
		}
		
		public static <T> ApiResponse<T> noContent(){
			return new ApiResponse<>(204, "요청에 성공했습니다.", null);
		}	
		
		public static <T> ApiResponse<T> badRequest() {
			return new ApiResponse<>(400, "잘못된 요청입니다. 입력 값을 확인해주세요.", null);
		}
		
		public static <T> ApiResponse<T> badRequest(String message) {
			return new ApiResponse<>(400, message, null);
		}
		
		public static <T> ApiResponse<T> badRequest(String message, T data) {
			return new ApiResponse<>(400, message, data);
		}
		
		public static <T> ApiResponse<T> unauthorized() {
			return new ApiResponse<>(401, "인증 자격 증명이 유효하지 않습니다. 로그인이 필요합니다.", null);
		}
		
		public static <T> ApiResponse<T> unauthorized(String message) {
			return new ApiResponse<>(401, message, null);
		}
		
		public static <T> ApiResponse<T> forbidden() {
			return new ApiResponse<>(403, "해당 요청에 대한 접근 권한이 없습니다.", null);
		}
		
		public static <T> ApiResponse<T> forbidden(String message) {
			return new ApiResponse<>(403, message, null);
		}
		
		public static <T> ApiResponse<T> notFound() {
			return new ApiResponse<>(404, "요청하신 리소스를 찾을 수 없습니다.", null);
		}
		
		public static <T> ApiResponse<T> notFound(String message) {
			return new ApiResponse<>(404, message, null);
		}
		
		public static <T> ApiResponse<T> internalServerError() {
			return new ApiResponse<>(500, "서버 내부 오류가 발생했습니다. 관리자에게 문의하세요.", null);
		}
		
		public static <T> ApiResponse<T> internalServerError(String message) {
			return new ApiResponse<>(500, message, null);
		}
		
		
		public static <T> ApiResponse<T> fail(int code, String message, T errorDetails) {
			return new ApiResponse<>(code, message, errorDetails);
		}	
	
}
