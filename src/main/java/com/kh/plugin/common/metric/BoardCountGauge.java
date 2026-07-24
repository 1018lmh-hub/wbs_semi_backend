package com.kh.plugin.common.metric;

import org.springframework.stereotype.Component;

import com.kh.plugin.inquiryboard.model.service.InquiryBoardService;
import com.kh.plugin.noticeboard.model.service.NoticeBoardService;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class BoardCountGauge {
    public BoardCountGauge(MeterRegistry registry, InquiryBoardService inquiryBoardService, NoticeBoardService noticeBoardService) {
        Gauge.builder("board_count_current", () -> inquiryBoardService.countInquirys() + noticeBoardService.countNotices())
             .description("현재 전체 게시글 수")
             .register(registry);
    }

}