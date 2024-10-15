package user.service;

import org.springframework.web.multipart.MultipartFile;

public interface ObjectStorageService {

	public String uploadFile(String bucketName, String string, MultipartFile fileImg);

	public void deleteFile(String bucketName, String string, String imageFileName);

}
