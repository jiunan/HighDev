package com.kt.smcp.aui.admin.roleMgt;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.MvcResult;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webApplicationContextSetup(this.wac).build();
	}

	@Test
	public void getUserList() throws Exception{
		Map<String,String> searchParam = new HashMap<String,String>();

		MvcResult andReturn = mockMvc.perform(get("/admin/roles", searchParam))
			.andExpect(content().mimeType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$[0].userId").exists())
			.andExpect(jsonPath("$[0].userNm").exists())
			.andExpect(jsonPath("$[0].svcId").exists())
			.andExpect(jsonPath("$[0].svcTgtId").exists())
			.andExpect(jsonPath("$[0].roleId").exists())
			.andExpect(jsonPath("$[0].mphon").exists())
			.andExpect(jsonPath("$[0].officeTelNo").exists())
			.andExpect(jsonPath("$[0].emailAdr").exists())
			.andExpect(jsonPath("$[0].useYn").exists())
			.andExpect(jsonPath("$[0].pwd").exists())
			.andExpect(jsonPath("$[0].cretDt").exists())
			.andExpect(jsonPath("$[0].cretrId").exists())
			.andExpect(jsonPath("$[0].chgDt").exists())
			.andExpect(jsonPath("$[0].chgrId").exists())
			.andReturn();

		String contentAsString = andReturn.getResponse().getContentAsString();
		logger.debug(contentAsString);
	}
}
