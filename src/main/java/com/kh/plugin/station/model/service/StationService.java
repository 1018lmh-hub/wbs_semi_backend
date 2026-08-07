package com.kh.plugin.station.model.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kh.plugin.exception.APINotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StationService {

	@Value("${api.key}")
	private String key;
	
	public String getStations() {
		String url = "https://bigdata.kepco.co.kr/openapi/v1/EVchargeManage.do?addr=서울특별시&apiKey=" + key + "&returnType=json";
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new APINotFoundException("API를 불러오지 못했습니다.");
		}
		String apiResponse = new RestTemplate().getForObject(uri, String.class);
		return apiResponse;
	}

}
