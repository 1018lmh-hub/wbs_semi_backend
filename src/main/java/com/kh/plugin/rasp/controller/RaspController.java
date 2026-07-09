package com.kh.plugin.rasp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.rasp.model.dto.SessionCreateRequest;
import com.kh.plugin.rasp.model.service.RaspService;
import com.kh.plugin.rasp.model.vo.Device;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/rasp")
@RequiredArgsConstructor
public class RaspController {

	private final RaspService raspService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> receive(@RequestBody SessionCreateRequest dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(raspService.save(dto)));
	}
	
	@GetMapping("/current")
	public ResponseEntity<ApiResponse<List<Device>>> findCurrent(){
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(raspService.findCurrent()));
	}
	
	@GetMapping("/serial")
	public ResponseEntity<ApiResponse<List<Device>>> findSerial(){
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(raspService.findSerial()));
	}
	
}
