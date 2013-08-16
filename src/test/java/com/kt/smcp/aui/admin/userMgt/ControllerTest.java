package com.kt.smcp.aui.admin.userMgt;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.MvcResult;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kt.smcp.aui.admin.userMgt.vo.UserMgtVO;
import com.kt.smcp.fw.test.WebContextLoader;

@ContextConfiguration(locations={
		"classpath:/application/test-context-common.xml",
		"classpath:/application/test-context-datasource.xml",
		"classpath:/application/test-context-sqlmap.xml",
		"classpath:/application/test-context-transaction.xml",
		"classpath:/servlet/test-context-common.xml",
		"classpath:/servlet/test-context-view.xml"},loader=WebContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	private UserMgtVO userMgtVO = new UserMgtVO();

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webApplicationContextSetup(this.wac).build();

		userMgtVO.setUserId("jeadoKo");
		userMgtVO.setUserNm("재도");
		userMgtVO.setSvcId("1234");
		userMgtVO.setSvcNm("관재서비스");
		userMgtVO.setRoleId("admin");
		userMgtVO.setRoleNm("관리자");
		userMgtVO.setSvcTgtId("2234");
		userMgtVO.setPwd("1111");
	}

	@Test
	public void viewReturn() throws Exception{
		mockMvc.perform(get("/admin/userMgt/", new HashMap<String,String>()))
			.andExpect(forwardedUrl("/WEB-INF/view/admin/userMgt.jsp"))
			.andExpect(view().name("admin/userMgt"));
	}

	@Test
	public void getUserList() throws Exception{
		Map<String,Object> searchParam = new HashMap<String,Object>();
		searchParam.put("userId", "");
		searchParam.put("userNm", "");
		searchParam.put("svcTgtId", "");
		searchParam.put("roleId", "");
//		searchParam.put("currentPage", 1);
//		searchParam.put("pageSize", 10);

		MvcResult andReturn = mockMvc.perform(get("/admin/users?currentPage=1&pageSize=10", searchParam))
		.andExpect(content().mimeType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$[0].userId").exists())
		.andExpect(jsonPath("$[6].userId").exists())
		.andReturn();

		String contentAsString = andReturn.getResponse().getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	public void insertUser() throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] userMgtVoContent = objectMapper.writeValueAsBytes(userMgtVO);

		MvcResult andReturn = mockMvc.perform(post("/admin/users", new HashMap())
				.body(userMgtVoContent)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		String contentAsString = andReturn.getResponse().getContentAsString();

		 Map<String, String> param = new HashMap<String, String>();
		 param.put("userId", userMgtVO.getUserId());

		mockMvc.perform(get("/admin/users/", new HashMap() ).param("userId", userMgtVO.getUserId()))
	 		.andExpect(content().mimeType("application/json;charset=UTF-8"))
	 		.andExpect(jsonPath("$[0].userId").value(is("jeadoKo")))
	 		.andExpect(jsonPath("$[0].userNm").value(is("재도")))
	 		.andExpect(jsonPath("$[0].svcId").value(is("1234")));
	}

	@Test
	public void deleteUser() throws Exception {
		MvcResult andReturn = mockMvc.perform(delete("/admin/users/jeadoKo", userMgtVO))
				.andReturn();
		String contentAsString = andReturn.getResponse().getContentAsString();

		System.out.println(contentAsString);
	}

	@Test
	public void updateUser() throws Exception {
		UserMgtVO update = new UserMgtVO();
		update.setUserId("jeadoKo");
		update.setUserNm("재도고");
		update.setSvcId("1234566");
		update.setSvcNm("관재서비스2");
		update.setRoleId("admin");
		update.setRoleNm("관리자");

		MvcResult andReturn = mockMvc.perform(put("/admin/users/", update ))
				.andReturn();
		String contentAsString = andReturn.getResponse().getContentAsString();

		System.out.println(contentAsString);

		 mockMvc.perform(get("/admin/users/", update ))
		 		.andExpect(content().mimeType("application/json;charset=UTF-8"))
		 		.andExpect(jsonPath("$[0].userId").value(is("jeadoKo")))
		 		.andExpect(jsonPath("$[0].userNm").value(is("재도고")))
		 		.andExpect(jsonPath("$[0].userId").value(is("jeadoKo")));

	}
}
