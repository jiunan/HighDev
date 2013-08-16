
package com.kt.smcp.aui.standard.serviceMgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.smcp.aui.standard.serviceMgt.vo.ServiceMgtVO;
import com.kt.smcp.fw.CommConstants;
import com.kt.smcp.fw.comm.dao.CommonDao;
import com.kt.smcp.fw.exception.KtException;


@Service
public class ServiceMgtSVC{
	
	@Autowired
	private CommonDao commonDao;
	
	public void  insertTranOK(){
		ServiceMgtVO input = new ServiceMgtVO();
		
		input.setSvcId("trantest1");
		
		insert(input);
		
		input.setSvcNm("트랜젝션 테스트1");
		update(input);
		
		//delete(input);
	}

	public void  insertTranFail(){
		ServiceMgtVO input = new ServiceMgtVO();
		
		input.setSvcId("trantest2");
		
		insert(input);
		
		input.setSvcNm("트랜젝션 테스트2");
		update(input);
		
		throw new KtException(CommConstants.DEFAULT_ERR_CODE);
		
		
		//delete(input);
		
		
	}
	
	public List< ServiceMgtVO > getList( ServiceMgtVO input )	{
		return commonDao.queryForList( "standard.serviceMgt.select_list_service" , input );		
	}
		
	public ServiceMgtVO getView( ServiceMgtVO input )	{
		return commonDao.queryForObject( "standard.serviceMgt.select_view_service" , input );
	}
	
	public ServiceMgtVO insert( ServiceMgtVO input ) 	{
		Object insert = commonDao.insert( "standard.serviceMgt.insert_service" , input );

		return input;		
	}
	
	public ServiceMgtVO update( ServiceMgtVO input ) {		
		int iResult = commonDao.update( "standard.serviceMgt.update_service" , input );		

		return input;	
	}
	
	public ServiceMgtVO delete( ServiceMgtVO input ) {
		int iResult = commonDao.delete( "standard.serviceMgt.delete_service" , input );

		return input;			
	}		

}