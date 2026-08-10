package com.kh.plugin.health.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.plugin.common.model.vo.ApiResponse;
import com.kh.plugin.health.model.service.HealthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
public class HealthController {
<<<<<<< HEAD
	
	private final HealthService healthServcie;
	
=======

	private final HealthService healthServcie;

>>>>>>> 098102bc5d23ff71171614e865d30959a58bf51c
	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, String>>> checkHealth(/*@RequestBody String request*/){
		Map<String, String> response = healthServcie.checkHealth();  
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
	}
<<<<<<< HEAD
	
	

}
=======

}
>>>>>>> 098102bc5d23ff71171614e865d30959a58bf51c
