
package com.kt.smcp.aui.admin.userMgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.smcp.aui.admin.userMgt.vo.UserMgtVO;
import com.kt.smcp.fw.comm.dao.CommonDao;


@Service
public class UserMgtSVC{

	@Autowired
	private CommonDao commonDao;

	public List< UserMgtVO > getList( UserMgtVO input)	{
		return commonDao.queryForList( "admin.userMgt.select_list_user" , input );
	}
	
	public List< UserMgtVO > getList( UserMgtVO input, int curPage, int pageSize)	{
		input.setCurPage(curPage);
		input.setPageSize(pageSize);
		return commonDao.queryForList( "admin.userMgt.select_list_user_paging" , input );
	}
	
	public UserMgtVO getView( UserMgtVO input )	{
		return commonDao.queryForObject( "admin.userMgt.select_view_user" , input );
	}

	public UserMgtVO insert( UserMgtVO input ) 	{
		Object insert = commonDao.insert( "admin.userMgt.insert_user" , input );

		return input;
	}

	public UserMgtVO update( UserMgtVO input ) {
		int iResult = commonDao.update( "admin.userMgt.update_user" , input );

		return input;
	}

	public UserMgtVO delete( UserMgtVO input ) {
		int iResult = commonDao.delete( "admin.userMgt.delete_user" , input );

		return input;
	}

}