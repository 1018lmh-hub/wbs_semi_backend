package com.kh.plugin.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
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
	
	@ExceptionHandler(FileDeleteFailedException.class)
	public ResponseEntity<ApiResponse<Void>> handlerFileDeleteFailedException(FileDeleteFailedException e){
		return ResponseEntity.status(500).body(ApiResponse.badRequest(e.getMessage()));
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
	
	@ExceptionHandler(IdMismatchException.class)
	public ResponseEntity<ApiResponse<Void>> handlerIdMismatch(IdMismatchException e){
		return ResponseEntity.status(400).body(ApiResponse.badRequest(e.getMessage()));
	}
	
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<ApiResponse<Void>> handlerPasswordMismatch(PasswordMismatchException e){
		return ResponseEntity.status(400).body(ApiResponse.badRequest(e.getMessage()));
	}
	
	@ExceptionHandler(ProfileFileNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handlerProfileFileNotFound(ProfileFileNotFoundException e){
		return ResponseEntity.status(404).body(ApiResponse.notFound(e.getMessage()));
	}
	
	@ExceptionHandler(InvalidBookmarkException.class)
	public ResponseEntity<ApiResponse<Void>> handlerInvalidBookmark(InvalidBookmarkException e){
		return ResponseEntity.status(400).body(ApiResponse.badRequest(e.getMessage()));
	}
	
	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<ApiResponse<Void>> handlerInvalidParameter(InvalidParameterException e){
		return ResponseEntity.status(400).body(ApiResponse.badRequest(e.getMessage()));
	}
	
	
	@ExceptionHandler(InvalidLikeException.class)
	public ResponseEntity<ApiResponse<Void>> handlerInvalidLike(InvalidLikeException e){
		return ResponseEntity.status(400).body(ApiResponse.badRequest(e.getMessage()));
	}
	
	@ExceptionHandler(BoardNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handlerBoardNotFound(BoardNotFoundException e){
		return ResponseEntity.status(400).body(ApiResponse.badRequest(e.getMessage()));
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiResponse<Void>> handlerAuthentication(AuthenticationException e){
		return ResponseEntity.status(401).body(ApiResponse.unauthorized(e.getMessage()));
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<Void>> handlerAccessDenied(AccessDeniedException e){
		return ResponseEntity.status(403).body(ApiResponse.unauthorized(e.getMessage()));
	}
}
