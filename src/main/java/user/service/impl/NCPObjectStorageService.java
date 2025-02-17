package user.service.impl;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import spring.conf.NaverConfiguration;
import user.service.ObjectStorageService;

@Service
public class NCPObjectStorageService implements ObjectStorageService {
	final AmazonS3 s3;
	
	public NCPObjectStorageService(NaverConfiguration naverConfiguration) {
		s3 = AmazonS3ClientBuilder
				.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(naverConfiguration.getEndPoint(), naverConfiguration.getRegionName()))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(naverConfiguration.getAccessKey(), naverConfiguration.getSecretKey())))
				.build();
	}
	
	@Override
	public String uploadFile(String bucketName, String directoryPath, MultipartFile fileImg) {
		// String bucketName: NCP Bucket 폴더 이름
		// String directoryPath:  NCP 경로
		// MultipartFile fileImg: 업로드 파일
		
		try(InputStream input = fileImg.getInputStream()) {
			String imageFileName = UUID.randomUUID().toString();
			
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(fileImg.getContentType());
			
			PutObjectRequest putObjectRequest = new PutObjectRequest(
																		bucketName, 
																		directoryPath + imageFileName, 
																		input,
																		metadata
																	).withCannedAcl(CannedAccessControlList.PublicRead);
																	// PublicRead : 모둔 사용자가 객체 일기 가능 But 수정/삭제 불가능
			
			s3.putObject(putObjectRequest);
			
			return imageFileName;
		} catch (Exception e) {
			throw new RuntimeException("파일 업로드 오류 발생");
		}
	}

	@Override
	public void deleteFile(String bucketName, String directoryPath, String imageFileName) {
		s3.deleteObject(bucketName, directoryPath + imageFileName);
	}
	
}
