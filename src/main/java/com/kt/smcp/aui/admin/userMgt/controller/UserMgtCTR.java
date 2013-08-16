package com.kt.smcp.aui.admin.userMgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.smcp.aui.admin.userMgt.service.UserMgtSVC;
import com.kt.smcp.aui.admin.userMgt.vo.UserMgtVO;

@Controller
@RequestMapping("admin")
public class UserMgtCTR {

	@Autowired
	private UserMgtSVC userMgtSVC;

	@RequestMapping("/userMgt")
	public String getViewName(Model moodel){
		moodel.addAttribute("TEST", "TEST");
		return "admin/userMgt";
	}

	@RequestMapping("/users")
	@ResponseBody
	public List<UserMgtVO> getUserList(UserMgtVO userMgtVO,@RequestParam(required=false) int currentPage, @RequestParam(required=false) int pageSize){
		return userMgtSVC.getList(userMgtVO, currentPage, pageSize);
	}

	@RequestMapping(method={RequestMethod.POST},value="/users")
	public void insertUser(@RequestBody UserMgtVO userMgtVO){

		userMgtSVC.insert(userMgtVO);
	}

	@RequestMapping(method={RequestMethod.PUT},value="/users")
	public void updateUser(@RequestBody UserMgtVO userMgtVO){
		userMgtSVC.update(userMgtVO);
	}

	@RequestMapping(method={RequestMethod.DELETE},value="/users/{userId}")
	public void deleteUser(@PathVariable String userId){
		UserMgtVO input = new UserMgtVO();
		input.setUserId(userId);

		userMgtSVC.delete(input);
	}
}
