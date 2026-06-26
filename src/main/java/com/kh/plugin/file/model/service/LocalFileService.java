package com.kh.plugin.file.model.service;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.plugin.common.util.RenamePolicy;
import com.kh.plugin.file.model.vo.AttachedFile;
import com.kh.plugin.user.model.dao.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LocalFileService implements FileService{

	private final Path fileLocation;
	
	public LocalFileService() {
		this.fileLocation = Paths.get("uploads").toAbsolutePath().normalize();
	}
	
	@Override 
	public String store(AttachedFile attachedFile) {
		
		if(!attachedFile.isValid()) {
			return null;
		}
		MultipartFile file = attachedFile.getFile();
		String changeName = attachedFile.getChangeName();
		Path targetLocation = this.fileLocation.resolve(changeName);
		try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return "http://localhost/uploads/" + changeName;
		} catch (IOException e) {
			throw new RuntimeException("부적절한 파일입니다.");
		}
		
	}
	
}
