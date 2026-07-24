package com.kh.plugin.common.metric;

import org.springframework.stereotype.Component;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class BoardViewCounter {

    private final MeterRegistry registry;

    public BoardViewCounter(MeterRegistry registry) {
        this.registry = registry;
    }

    public void increment(String type) {
        Counter.builder("board_view_total")
               .description("게시글 조회 횟수")
               .tag("type", type)
               .register(registry)
               .increment();
    }
}