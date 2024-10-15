package user.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import user.bean.UserFileUploadDTO;
import user.bean.UserPaging;
import user.dao.UserFileUploadDAO;
import user.service.ObjectStorageService;
import user.service.UserFileUploadService;

@Service
public class UserFileUploadServiceImpl implements UserFileUploadService {
	@Autowired
	private UserFileUploadDAO fileUploadDAO;
	@Autowired
	private HttpSession session;
	@Autowired
	private ObjectStorageService objectStorageService;
	private String bucketName = "bitcamp-9th-bucket-129";

	@Override
	public void fileUpload(List<UserFileUploadDTO> dtoList) {
		fileUploadDAO.fileUpload(dtoList);
//		for (UserFileUploadDTO dto : dtoList) {
//			fileUploadDAO.fileUpload(dto);
//		}
	}

	@Override
	public Map<String, Object> list() {
		Map<String, Object> resultMap = new HashMap<String, Object>();		
		List<UserFileUploadDTO> dtoList = fileUploadDAO.list();

		resultMap.put("list", dtoList);
		resultMap.put("totalA", fileUploadDAO.getTotalA());
		
		return resultMap;
	}

	@Override
	public UserFileUploadDTO fileInfo(String seq) {
		return fileUploadDAO.fileInfo(seq);
	}

	@Override
	public void update(UserFileUploadDTO userFileUploadDTO, MultipartFile img) {
		// NCP Object Storage >> 이미지 덮어쓰기 불가능
		// DB에서 seq에 해당하는 imageFileName 커내와 Object Storage(NCP) 이미지 삭제 후 이미지 업로드
		
		UserFileUploadDTO dto = fileUploadDAO.fileInfo(userFileUploadDTO.getSeq()+"");
		String imageFileName = dto.getImageFileName();
		if(img.getSize() != 0) {
			String filePath = session.getServletContext().getRealPath("resources/storage");
			
			// NCP: Object Storage 이미지 삭제
			objectStorageService.deleteFile(bucketName, "storage/", imageFileName);
			
			String originalFilename = img.getOriginalFilename();
			// NCP: Object Storage 이미지 등록
			imageFileName = objectStorageService.uploadFile(bucketName, "storage/", img);
			
			File file = new File(filePath, originalFilename);
			
			// file 객체로 형변환
			try {
				img.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			userFileUploadDTO.setImageFileName(imageFileName);
			userFileUploadDTO.setImageOriginalName(originalFilename);
		} else {
			userFileUploadDTO.setImageFileName(dto.getImageFileName());
			userFileUploadDTO.setImageOriginalName(dto.getImageOriginalName());
		}
		
		fileUploadDAO.update(userFileUploadDTO);
		
	}

	@Override
	public void delete(List<Integer> seqList) {
		for (Integer seqData : seqList) {
			String imageFileName = fileUploadDAO.getImageFileName(seqData);
			objectStorageService.deleteFile(bucketName, "storage/", imageFileName);
		}
		
		fileUploadDAO.delete(seqList);
	}
	
	
}
