package com.kh.plugin.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.plugin.common.model.vo.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomAuthenticationException.class)
	public ResponseEntity<ApiResponse<Void>> handlerCustomAuthentication(CustomAuthenticationException e){
		return ResponseEntity.status(401).body(ApiResponse.unauthorized(e.getMessage()));
	}
	
	@ExceptionHandler(InvalidFileFormatException.class)
	public ResponseEntity<ApiResponse<Void>> handlerInvalidFileFormat(InvalidFileFormatException e){
		return ResponseEntity.status(400).body(ApiResponse.badRequest(e.getMessage()));
	}

	
	@ExceptionHandler(DuplicatedUserIdException.class)
	public ResponseEntity<ApiResponse<Void>> handlerDuplicatedUserId(DuplicatedUserIdException e){
		return ResponseEntity.status(400).body(ApiResponse.badRequest(e.getMessage()));
	}

	@ExceptionHandler(DataPersistenceException.class)
	public ResponseEntity<ApiResponse<Void>> handlerDataPersistence(DataPersistenceException e){
		return ResponseEntity.status(500).body(ApiResponse.internalServerError(e.getMessage()));
	}	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handlerArgumentsNotValid(MethodArgumentNotValidException e){
		Map<String, String> errors = new HashMap();
		e.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
		return ResponseEntity.status(400).body(ApiResponse.badRequest("잘못된 파라미터 입력", errors));
	}
	
}
