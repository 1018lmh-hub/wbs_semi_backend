package com.kh.plugin.file.model.service;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kh.plugin.exception.FileUploadFailedException;
import com.kh.plugin.file.model.vo.AttachedFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3FileService implements FileService {
	
	private final S3Client s3Client;
	
	@Value("${cloud.s3.bucket:local-dev-bucket}")
	private String bucketName;
	@Value("${cloud.region.static:ap-northeast-2}")
	private String region;
	
	@Override
	public String store(AttachedFile attachedFile) {

		if (!attachedFile.isValid()) {
			return null;
		}

		String fileName = attachedFile.getChangeName();
		PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName)
														     .key(fileName)
														     .contentType(attachedFile.getFile().getContentType())
														     .build();
		try {
			s3Client.putObject(request, RequestBody.fromInputStream(attachedFile.getFile().getInputStream(), attachedFile.getFile().getSize()));
		} catch(AwsServiceException | SdkClientException | IOException e) {
			throw new FileUploadFailedException("파일 업로드에 실패했습니다.");
		}
		return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;
	}
	
	@Override
	public void delete(String filePath) {
		
		try {
			URL url = new URL(filePath);
			String path = url.getPath();
			String key = path.substring(1);
			DeleteObjectRequest request = DeleteObjectRequest.builder().bucket(bucketName)
																	   .key(key)
																	   .build();
			s3Client.deleteObject(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
