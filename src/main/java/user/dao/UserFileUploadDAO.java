package user.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import user.bean.UserFileUploadDTO;

@Mapper
public interface UserFileUploadDAO {

	public void fileUpload(List<UserFileUploadDTO> dtoList);

	public int getTotalA();

	public List<UserFileUploadDTO> list();

	public UserFileUploadDTO fileInfo(String seq);

	public String getImageFileName(int seq);

	public void update(UserFileUploadDTO userFileUploadDTO);

	public void delete(List<Integer> seqList);
	
}
