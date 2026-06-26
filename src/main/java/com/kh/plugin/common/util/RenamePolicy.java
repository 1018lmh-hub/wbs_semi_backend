package com.kh.plugin.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.kh.plugin.exception.InvalidFileFormatException;


// FileRenamePolicy라는 인터페이스를 구현해서
// 이름바꾸기 정책을 만들어주기
@Component
public class RenamePolicy {

	public String rename(MultipartFile file) {
		
		String originName = file.getOriginalFilename();
		String ext = originName.substring(originName.lastIndexOf("."));
		List<String> exts = List.of(".jpg", ".jpeg", ".png", ".gif", ".webp", ".avif", ".heic", ".bmp", ".tiff", ".svg");
		if(!exts.contains(ext.toLowerCase())) {
			throw new InvalidFileFormatException("지원하지 않은 파일 형식입니다.");
		}		
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int randomNum = (int)(Math.random() * 900) + 100;
		String changeName = "PLU_" + currentTime + "_" + randomNum + ext;
		return changeName;
		
	}
	
}
