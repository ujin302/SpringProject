package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import user.bean.UserDTO;

@Mapper
public interface UserDAO {
	public void write(UserDTO userDTO);

	public int checkId(String id);

	public List<UserDTO> list(HashMap<String, Integer> map);

	public int getTotalA();

	public UserDTO getUserInfo(String id);

	public void update(UserDTO dto);

	public void delete(String id);

}