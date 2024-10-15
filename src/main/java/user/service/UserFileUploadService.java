package user.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import user.bean.UserFileUploadDTO;

public interface UserFileUploadService {

	public void fileUpload(List<UserFileUploadDTO> dtoList);

	public Map<String, Object> list();

	public UserFileUploadDTO fileInfo(String seq);

	public void update(UserFileUploadDTO userFileUploadDTO, MultipartFile img);

	public void delete(List<Integer> seqList);

	
}
