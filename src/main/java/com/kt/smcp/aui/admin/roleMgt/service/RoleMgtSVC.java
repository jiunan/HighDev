
package com.kt.smcp.aui.admin.roleMgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.smcp.aui.admin.roleMgt.vo.RoleMgtVO;
import com.kt.smcp.fw.comm.dao.CommonDao;


@Service
public class RoleMgtSVC{
	
	@Autowired
	private CommonDao commonDao;
	
	public List< RoleMgtVO > getList( RoleMgtVO input )	{
		return commonDao.queryForList( "admin.roleMgt.select_list_role" , input );		
	}
		
	public RoleMgtVO getView( RoleMgtVO input )	{
		return commonDao.queryForObject( "admin.roleMgt.select_view_role" , input );
	}
	
	public RoleMgtVO insert( RoleMgtVO input ) 	{
		Object insert = commonDao.insert( "admin.roleMgt.insert_role" , input );

		return input;		
	}
	
	public RoleMgtVO update( RoleMgtVO input ) {		
		int iResult = commonDao.update( "admin.roleMgt.update_role" , input );		

		return input;	
	}
	
	public RoleMgtVO delete( RoleMgtVO input ) {
		int iResult = commonDao.delete( "admin.roleMgt.delete_role" , input );

		return input;			
	}		

}