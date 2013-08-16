package com.kt.smcp.aui.standard.serviceMgt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kt.smcp.aui.standard.serviceMgt.service.ServiceMgtSVC;
import com.kt.smcp.aui.standard.serviceMgt.vo.ServiceMgtVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application/test-context-common.xml",
		"classpath:application/test-context-datasource.xml",
		"classpath:application/test-context-sqlmap.xml",
		"classpath:application/test-context-transaction.xml"})
public class ServiceTest {
	String service="service2";
	
	@Autowired
	private ServiceMgtSVC serviceMgtSVC;
	
	//@Test
	public void tester(){
		//insertSvc();
		//updateSvc();
		//getSvc();
		//deleteSvc();
		//getSvcList();
		serviceMgtSVC.insertTranOK();
		serviceMgtSVC.insertTranFail();
	}
	
    public void insertSvc(){
		ServiceMgtVO input = new ServiceMgtVO();
		
		input.setSvcId(service);
		input.setSvcNm("테스트");
		input.setSvcSbjtCd("test 테마");
		input.setSvcVer("1.0");
		input.setAdmrId("test1");
		input.setOprSttCd("1");

		
		serviceMgtSVC.insert(input);
    }
	@Test
	public void getSvcList(){
		ServiceMgtVO input = new ServiceMgtVO();
		
		List<ServiceMgtVO> list = serviceMgtSVC.getList(null);
		
		assertThat(list.isEmpty(), not(nullValue()));

	}
	
	public void updateSvc(){
		ServiceMgtVO input = new ServiceMgtVO();
		input.setSvcId(service);
		input.setModId("test1");
		input.setOprSttCd("2");
		
		ServiceMgtVO list = serviceMgtSVC.update(input);
	}
	
	public void getSvc(){
		
		ServiceMgtVO input = new ServiceMgtVO();
		input.setSvcId(service);
		
		ServiceMgtVO list = serviceMgtSVC.getView(input);
	}

	public void deleteSvc(){
		ServiceMgtVO input = new ServiceMgtVO();
		
		input.setSvcId(service);
		
		serviceMgtSVC.delete(input);
	}
}
