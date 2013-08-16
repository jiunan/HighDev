package com.kt.smcp.aui.admin.userMgt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kt.smcp.aui.admin.userMgt.service.UserMgtSVC;
import com.kt.smcp.aui.admin.userMgt.vo.UserMgtVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application/test-context-common.xml",
		"classpath:application/test-context-datasource.xml",
		"classpath:application/test-context-sqlmap.xml",
		"classpath:application/test-context-transaction.xml"})
public class ServiceTest {
	String user="test11";

	@Autowired
	private UserMgtSVC userMgtSVC;

	//@Test
	public void tester(){
		insertUser();
		updateUser();
		getUser();
		deleteUser();
		getUserList();
	}

    public void insertUser(){
		UserMgtVO input = new UserMgtVO();

		input.setUserId(user);
		input.setUserNm("테스트");
		input.setUseYn("Y");
		input.setSvcId("123");
		input.setSvcTgtId("456");
		input.setRoleId("tester");
		input.setPwd("1111");

		userMgtSVC.insert(input);
    }

    @Test
	public void getUserList(){
		UserMgtVO input = new UserMgtVO();

		input.setUserId("test");

		List<UserMgtVO> list = userMgtSVC.getList(input,1,2);
		UserMgtVO userMgtVO = list.get(0);
		assertThat(list.isEmpty(), not(nullValue()));
		assertThat(userMgtVO.getCurPage(),Matchers.is(1));
		assertThat(userMgtVO.getPageSize(),Matchers.is(2));

	}

	public void updateUser(){
		UserMgtVO input = new UserMgtVO();
		input.setUserId(user);
		input.setChgrId(user);
		input.setUseYn("N");

		UserMgtVO list = userMgtSVC.update(input);
	}

	public void getUser(){

		UserMgtVO input = new UserMgtVO();
		input.setUserId(user);

		UserMgtVO list = userMgtSVC.getView(input);
	}

	public void deleteUser(){
		UserMgtVO input = new UserMgtVO();

		input.setUserId(user);

		userMgtSVC.delete(input);
	}
}
