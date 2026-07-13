package com.kh.plugin.rasp.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kh.plugin.rasp.model.dao.RaspMapper;
import com.kh.plugin.rasp.model.dto.CongestionSnapshot;
import com.kh.plugin.rasp.model.dto.SessionCreateRequest;
import com.kh.plugin.rasp.model.vo.Device;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RaspService {
	private final RaspMapper raspMapper;
	private final RaspCongestionCache congestionCache;

	@Transactional
	public Void save(SessionCreateRequest dto) {
		Device device = Device.builder().deviceId(dto.getDeviceId())
										.createdAt(dto.getCreatedAt())
										.finishAt(dto.getFinishAt())
										.build();
		raspMapper.save(device);
		return null;
	}

	public CongestionSnapshot findCurrent() {
		return congestionCache.getCurrentSnapshot();
	}

	public CongestionSnapshot findSerial() {
		return congestionCache.getHistorySnapshot();
	}
}