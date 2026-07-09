package com.kh.plugin.rasp.model.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Device {

	private Long logId;
	private Long deviceId;
	private LocalDateTime createdAt;
	private LocalDateTime finishAt;
	
}
