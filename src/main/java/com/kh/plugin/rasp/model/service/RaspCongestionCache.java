package com.kh.plugin.rasp.model.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.kh.plugin.rasp.model.dao.RaspMapper;
import com.kh.plugin.rasp.model.dto.CongestionSnapshot;
import com.kh.plugin.rasp.model.vo.Device;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RaspCongestionCache {

    private final RaspMapper raspMapper;
    private static final CongestionSnapshot EMPTY = new CongestionSnapshot(LocalDateTime.now(), Collections.emptyList());
    private final AtomicReference<CongestionSnapshot> currentSnapshot = new AtomicReference<>(EMPTY);
    private final AtomicReference<CongestionSnapshot> historySnapshot = new AtomicReference<>(EMPTY);

    @Scheduled(fixedRate = 10000)
    public void refresh() {
        try {
            LocalDateTime now = LocalDateTime.now();

            List<Device> history = raspMapper.findSerial(now);

            List<Device> current = history.stream()
                    .filter(d -> !d.getCreatedAt().isAfter(now) && d.getFinishAt().isAfter(now))
                    .collect(Collectors.toList());

            currentSnapshot.set(new CongestionSnapshot(now, current));
            historySnapshot.set(new CongestionSnapshot(now, history));
        } catch (Exception e) {
            log.error("혼잡도 캐시 갱신 실패", e);
        }
    }

    public CongestionSnapshot getCurrentSnapshot() {
        return currentSnapshot.get();
    }

    public CongestionSnapshot getHistorySnapshot() {
        return historySnapshot.get();
    }
}