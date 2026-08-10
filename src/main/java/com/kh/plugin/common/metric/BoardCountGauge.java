package com.kh.plugin.common.metric;

import org.springframework.stereotype.Component;

import com.kh.plugin.inquiryboard.model.service.InquiryBoardService;
import com.kh.plugin.noticeboard.model.service.NoticeBoardService;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class BoardCountGauge {
<<<<<<< HEAD
    public BoardCountGauge(MeterRegistry registry, InquiryBoardService inquiryBoardService, NoticeBoardService noticeBoardService) {
=======
	
    public BoardCountGauge(MeterRegistry registry, InquiryBoardService inquiryBoardService, NoticeBoardService noticeBoardService) {
    	
>>>>>>> 098102bc5d23ff71171614e865d30959a58bf51c
        Gauge.builder("board_count_current", () -> inquiryBoardService.countInquirys() + noticeBoardService.countNotices())
             .description("현재 전체 게시글 수")
             .register(registry);
    }
<<<<<<< HEAD

=======
>>>>>>> 098102bc5d23ff71171614e865d30959a58bf51c
}