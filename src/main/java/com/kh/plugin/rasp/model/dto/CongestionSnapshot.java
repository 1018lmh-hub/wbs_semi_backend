package com.kh.plugin.rasp.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.kh.plugin.rasp.model.vo.Device;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CongestionSnapshot {
	
    private final LocalDateTime asOf; 
    private final List<Device> devices;
    
}