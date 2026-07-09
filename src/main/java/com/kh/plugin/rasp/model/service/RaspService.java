package com.kh.plugin.rasp.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.plugin.rasp.model.dao.RaspMapper;
import com.kh.plugin.rasp.model.dto.SessionCreateRequest;
import com.kh.plugin.rasp.model.vo.Device;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RaspService {
	
	private final RaspMapper raspMapper;

	// 데이터 입력
	@Transactional
	public Void save(SessionCreateRequest dto) {
		
		Device device = Device.builder().deviceId(dto.getDeviceId())
										.createdAt(dto.getCreatedAt())
										.finishAt(dto.getFinishAt())
										.build();
		raspMapper.save(device);
		return null;
	}

	// 현시간 사용 데이터 출력
	@Transactional
	public List<Device> findCurrent() {
		return raspMapper.findCurrent();
	}

	// 시간별 데이터 출력
	@Transactional
	public List<Device> findSerial() {
		return raspMapper.findSerial();
	}

}
