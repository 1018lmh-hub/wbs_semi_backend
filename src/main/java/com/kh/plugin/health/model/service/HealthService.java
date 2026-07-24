package com.kh.plugin.health.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HealthService {
	
	@Value("${spring.application.name}")
	private String myapp;
	
	private final DataSource dataSource;
	
	public Map<String, String> checkHealth(){		
		
		
		String database = null;
		
		try (Connection conn = dataSource.getConnection()){
		    database = "UP";
		} catch (SQLException e) {
		    database = "DOWN";
		}
			

		Map<String, String> response = Map.of("status", "UP",
											  "database", database,
											  "timestamp", LocalDateTime.now().toString(),
											  "app", myapp);
		return response;
	}

}
