package user.service;

import java.util.Map;

import user.bean.UserDTO;

public interface UserService {
	
	public boolean checkId(String id);

	public Map<String, Object> list(String pg);

	public void write(UserDTO userDTO);

	public UserDTO userInfo(String id);

	public void update(UserDTO dto);

	public void delete(String id);

	public UserDTO getExistPwd(String id);

 }
