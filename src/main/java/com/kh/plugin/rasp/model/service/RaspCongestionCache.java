package com.kh.plugin.rasp.model.service;

import java.util.List;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kh.plugin.rasp.model.dao.RaspMapper;
import com.kh.plugin.rasp.model.vo.Device;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RaspCongestionCache {

    private final RaspMapper raspMapper;

    // 클라이언트가 몇 번을 요청하든, 실제 DB 조회는 여기서 10초에 한 번만 발생
    private final AtomicReference<List<Device>> currentSnapshot =
            new AtomicReference<>(Collections.emptyList());
    private final AtomicReference<List<Device>> historySnapshot =
            new AtomicReference<>(Collections.emptyList());

    @Scheduled(fixedRate = 10000)
    public void refresh() {
        try {
            currentSnapshot.set(raspMapper.findCurrent());
            historySnapshot.set(raspMapper.findSerial());
        } catch (Exception e) {
            log.error("혼잡도 캐시 갱신 실패", e);
            // 실패해도 이전 스냅샷 유지 (덮어쓰지 않음)
        }
    }

    public List<Device> getCurrentSnapshot() {
        return currentSnapshot.get();
    }

    public List<Device> getHistorySnapshot() {
        return historySnapshot.get();
    }
}