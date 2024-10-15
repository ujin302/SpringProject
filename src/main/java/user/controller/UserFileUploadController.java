package user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import user.bean.UserDTO;
import user.bean.UserFileUploadDTO;
import user.service.ObjectStorageService;
import user.service.UserFileUploadService;

@Controller
@RequestMapping("user/file")
public class UserFileUploadController {
	@Autowired
	private UserFileUploadService fileUploadService;
	@Autowired
	private ObjectStorageService objectStorageService; // NCP 
	private String bucketName = "bitcamp-9th-bucket-129";
	
	@RequestMapping(value = "/uploadForm")
	public String uploadForm() {
		return "file/uploadForm";
	}
	
	@RequestMapping(value = "/uploadAjaxForm")
	public String uploadAjaxForm() {
		return "file/uploadAjaxForm";
	}
	
	// 1. 단일 파일 처리 
//	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
//	@ResponseBody
//	public String upload(@ModelAttribute UserFileUploadDTO userFileUploadDTO, // 파일 정보 
//						 @RequestParam MultipartFile img, // 업로드 파일 정보 
//						 HttpSession session) { // 
//		
//		// 실제 폴더 
//		String filePath = session.getServletContext().getRealPath("resources/storage");
//		// D:\Spring\workspace\stsWorkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringProject\resources\storage
//
//		String imgOriginalFileName = img.getOriginalFilename();
//		File file = new File(filePath, imgOriginalFileName);
//		System.out.println("실제 폴더: " + filePath + "/" + imgOriginalFileName);
//		
//		try {
//			img.transferTo(file);
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String result = "<span>"
//						+ "<img src='/Spring/resources/storage/" + imgOriginalFileName + "' width='300' height='300'"
//						+ "<span/>";
//		
//		userFileUploadDTO.setImageOriginalName(imgOriginalFileName);
//		
//		
//		return result;
//	}
	
	// 2. 다중 파일 처리: Array
//	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
//	@ResponseBody
//	public String upload(@ModelAttribute UserFileUploadDTO userFileUploadDTO, // 파일 정보 
//						 @RequestParam MultipartFile[] img, // 업로드 파일 정보 
//						 HttpSession session) { // 
//		
//		// 실제 폴더 
//		String filePath = session.getServletContext().getRealPath("resources/storage");
//		// D:\Spring\workspace\stsWorkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringProject\resources\storage
//
//		String imgOriginalFileName = null;
//		File file = null;
//		String result = null;
//		
//		for (MultipartFile fileImg : img) {
//			imgOriginalFileName = fileImg.getOriginalFilename();
//			file = new File(filePath, imgOriginalFileName);
//			
//			System.out.println("실제 폴더: " + filePath + "/" + imgOriginalFileName);
//			
//			
//			try {
//				fileImg.transferTo(file);
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			result += "<span>"
//				+ "<img src='/Spring/resources/storage/" + imgOriginalFileName + "' width='300' height='300'"
//				+ "<span/><br>";
//			
//			userFileUploadDTO.setImageOriginalName(imgOriginalFileName);
//		}
//		
//		return result;
//	}
	
	// 3. 다중 파일 처리: List
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String upload(@ModelAttribute UserFileUploadDTO userFileUploadDTO, // 파일 정보 
						 @RequestParam("img[]") List<MultipartFile> imgList, // 업로드 파일 정보 
						 HttpSession session) {
		System.out.println("1");
		
		// [ 파일 객체 설정 ]
		// 실제 폴더 
		String filePath = session.getServletContext().getRealPath("resources/storage");
		// D:\Spring\workspace\stsWorkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringProject\resources\storage

		String imgOriginalFileName = null;
		File file = null;
		String result = "";
		List<UserFileUploadDTO> dtoList = new ArrayList<UserFileUploadDTO>();
		
		for (MultipartFile fileImg : imgList) {			
			// 변수 설정
//			String imageFileName = UUID.randomUUID().toString();

			// NCP: Object Storage
			String imageFileName = objectStorageService.uploadFile(bucketName, "storage/", fileImg);
			
			UserFileUploadDTO dto = new UserFileUploadDTO();
			imgOriginalFileName = fileImg.getOriginalFilename();
			file = new File(filePath, imgOriginalFileName);
			
			System.out.println("실제 폴더: " + filePath + "/" + imgOriginalFileName);
			
			// file 객체로 형변환
			try {
				fileImg.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// 화면에 뿌릴 HTML 태그
			result += "<span>"
				+ "<img src='/Spring/resources/storage/" + imgOriginalFileName + "' width='300' height='300'"
				+ "<span/><br>";
			
			// dto 설정 후, dtoList에 저장
			dto.setImageName(userFileUploadDTO.getImageName());
			dto.setImageContent(userFileUploadDTO.getImageContent());
			dto.setImageFileName(imageFileName);
			dto.setImageOriginalName(imgOriginalFileName);
			
			System.out.println("controller: " + dto.toString());
			dtoList.add(dto);
		}
		
		// [ DB 저장 ]
		fileUploadService.fileUpload(dtoList);
		
		return result;
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		Map<String, Object> map = fileUploadService.list();
		model.addAttribute("list", map.get("list"));
		model.addAttribute("totalA", map.get("totalA"));
		
		return "file/FileList";
	}
	
	@RequestMapping(value = "/fileInfo")
	public String userInfo(@RequestParam String seq, Model model) {
		UserFileUploadDTO uploadDTO = fileUploadService.fileInfo(seq);
		model.addAttribute("uploadDTO", uploadDTO);
		
		return "file/fileInfo";
	}
	
	@RequestMapping(value = "/updateForm")
	public String updateForm(@RequestParam String seq, Model model) {
		UserFileUploadDTO uploadDTO = fileUploadService.fileInfo(seq);
		model.addAttribute("uploadDTO", uploadDTO);
		
		return "file/fileUpdateForm";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String update(@ModelAttribute UserFileUploadDTO userFileUploadDTO, @RequestParam MultipartFile img , Model model) {
		fileUploadService.update(userFileUploadDTO, img);
		
		return "상품 수정 완료";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
//	@ResponseBody
	public String delete(@RequestParam("seqList") List<Integer> seqList) {
		System.out.println("1: " + seqList);
		fileUploadService.delete(seqList);
		
		return "file/FileList";
	}

}
