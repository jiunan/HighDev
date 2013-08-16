package com.kt.smcp.aui.admin.roleMgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.smcp.aui.admin.roleMgt.service.RoleMgtSVC;
import com.kt.smcp.aui.admin.roleMgt.vo.RoleMgtVO;

@Controller
@RequestMapping("/admin")
public class RoleMgtCTR {

	@Autowired
	private RoleMgtSVC roleMgtSVC;


	@RequestMapping("roles")
	@ResponseBody
	public List<RoleMgtVO> getRoles(RoleMgtVO roleMgtVO){
		return roleMgtSVC.getList(roleMgtVO);
	}

}
