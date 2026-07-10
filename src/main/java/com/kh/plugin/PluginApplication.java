package com.kh.plugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PluginApplication {

	public static void main(String[] args) {
		SpringApplication.run(PluginApplication.class, args);
	}

}
