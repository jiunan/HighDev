package com.kt.smcp.aui.admin.roleMgt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kt.smcp.aui.admin.roleMgt.service.RoleMgtSVC;
import com.kt.smcp.aui.admin.roleMgt.vo.RoleMgtVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application/test-context-common.xml",
		"classpath:application/test-context-datasource.xml",
		"classpath:application/test-context-sqlmap.xml",
		"classpath:application/test-context-transaction.xml"})
public class ServiceTest {
	String role="test1";
	
	@Autowired
	private RoleMgtSVC roleMgtSVC;
	
	@Test
	public void tester(){
		insertRole();
		updateRole();
		getRole();
		deleteRole();
		getRoleList();
	}
	
    public void insertRole(){
    	RoleMgtVO input = new RoleMgtVO();
		
		input.setRoleId(role);
		input.setRoleNm("테스트");
		input.setExplain("테스트 해봅니다.");
		input.setCretrId("test1");
		
		roleMgtSVC.insert(input);
    }
	
	public void getRoleList(){
		RoleMgtVO input = new RoleMgtVO();
		
		List<RoleMgtVO> list = roleMgtSVC.getList(null);
		
		assertThat(list.isEmpty(), not(nullValue()));

	}
	
	public void updateRole(){
		RoleMgtVO input = new RoleMgtVO();
		input.setRoleId(role);
		input.setChgrId("test1");
		
		RoleMgtVO list = roleMgtSVC.update(input);
	}
	
	public void getRole(){
		
		RoleMgtVO input = new RoleMgtVO();
		input.setRoleId(role);
		
		RoleMgtVO list = roleMgtSVC.getView(input);
	}

	public void deleteRole(){
		RoleMgtVO input = new RoleMgtVO();
		
		input.setRoleId(role);
		
		roleMgtSVC.delete(input);
	}
}
