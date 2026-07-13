package com.kh.plugin.file.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.plugin.exception.InvalidFileFormatException;

import lombok.Value;

@Value
public class AttachedFile {

	private final MultipartFile file;
	private final boolean valid;
	private final String changeName;
	
	private AttachedFile(MultipartFile file) {
		
		this.file = file;
		this.valid = (file != null && !file.isEmpty());
		this.changeName = this.valid ? rename(file.getOriginalFilename()) : null;
	}
	
	public static AttachedFile from(MultipartFile file) {
        return new AttachedFile(file);
    }
	
	private String rename(String originName) {
		String ext = originName.substring(originName.lastIndexOf("."));
		List<String> exts = List.of(".jpg", ".jpeg", ".png", ".gif", ".webp", ".avif", ".heic", ".bmp", ".tiff", ".svg");
		if(!exts.contains(ext.toLowerCase())) {
			throw new InvalidFileFormatException("지원하지 않은 파일 형식입니다.");
		}		
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int randomNum = (int)(Math.random() * 900) + 100;
		return "PLU_" + currentTime + "_" + randomNum + ext;
	}
}
