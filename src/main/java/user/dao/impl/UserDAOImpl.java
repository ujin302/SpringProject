// @Mapper 사용으로 인해 해당 클래스 필요 없음


//package user.dao.impl;
//
//import java.util.List;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import user.bean.UserDTO;
//import user.dao.UserDAO;
//
//@Repository
//public class UserDAOImpl implements UserDAO {
//	@Autowired
//	private SqlSession sqlSession; // interface SqlSession 
//	private String userSQL = "userSQL";
//	
//	@Override
//	public int checkId(String id) {
//		System.out.println("checkId("+ id +") 호출");
//		return sqlSession.selectOne(userSQL + ".checkId", id);
//	}
//
//	@Override
//	public void write(UserDTO userDTO) {
//		sqlSession.insert(userSQL + ".write", userDTO);
//	}
//
//	@Override
//	public List<UserDTO> list() {
//		List<UserDTO> list = null;
//		list = sqlSession.selectList(userSQL + ".list");
//		return list;
//	}
//}
