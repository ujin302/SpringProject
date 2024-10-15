package user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import user.bean.UserDTO;
import user.dao.UserDAO;
import user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/userInfo")
	public String userInfo(@RequestParam String id, @RequestParam String pg, Model model) {
		UserDTO userDTO = userService.userInfo(id);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("pg", pg);
		
		return "user/UserInfo";
	}
	
	@RequestMapping(value = "/writeFrom")
	public String writeFrom() {
		return "user/WriteForm";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ResponseBody
	public void write(@ModelAttribute UserDTO userDTO) {
		userService.write(userDTO);
	}
	
	@RequestMapping(value = "/checkId" )
	@ResponseBody
	public String checkId(@RequestParam String id, ModelAndView mv) {
		return userService.checkId(id)+"";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(@ModelAttribute UserDTO userDTO, Model model) {
		userService.update(userDTO);
	}
	
	@RequestMapping(value = "/list")
	public String list(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		Map<String, Object> map = userService.list(pg);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("paging", map.get("paging"));
		
		return "user/listUser";
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete(@RequestParam String id) {
		userService.delete(id);
	}
	
	@RequestMapping(value = "/getExistPwd")
	@ResponseBody
	public UserDTO getExistPwd(@RequestParam String id) {
		// Spring이 자동으로 JSON 객체로 변환하여 클라에게 전송 
		return userService.getExistPwd(id);
	}
	
}
