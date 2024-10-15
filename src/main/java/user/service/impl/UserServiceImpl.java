package user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.bean.UserPaging;
import user.dao.UserDAO;
import user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	

	@Override
	public UserDTO userInfo(String id) {
		return userDAO.getUserInfo(id);
	}
	
	@Override
	public boolean checkId(String id) {
		int checkId = userDAO.checkId(id);
		if(checkId == 1) return false;
		else return true;
	}
	
	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);
	}
	
	@Override
	public void update(UserDTO userDTO) {
		userDAO.update(userDTO);
	}
	
	@Override
	public Map<String, Object> list(String pg) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		UserPaging paging = new UserPaging();
		paging.setCurrentPgae(Integer.parseInt(pg));
		paging.setPageBlock(3);
		paging.setPageSize(2);
		paging.setTotalA(userDAO.getTotalA());
		paging.makePagingHTML("userPaging");
		
		int startNum = (Integer.parseInt(pg) - 1) * paging.getPageSize();
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("pgSize", paging.getPageSize());
		map.put("startNum", startNum);
		
		resultMap.put("list", userDAO.list(map));
		resultMap.put("paging", paging);
		
		return resultMap;
	}

	@Override
	public void delete(String id) {
		userDAO.delete(id);
	}
	
	@Override
	public UserDTO getExistPwd(String id) {
		return userDAO.getUserInfo(id);
	}
}
