/**
 * 0. Project  : D3M
 *
 * 1. FileName : CommConstants.java
 * 2. Package  : com.kt.plfm.fw
 * 3. Comment  : 
 * 4. 작성자   : kyi
 * 5. 작성일   : 2013. 6. 19. 오후 1:26:14
 * 6. 변경이력 : 
 *               이름     : 일자          : 근거자료   : 변경내용
 *               ------------------------------------
 *               kyi  : 2013. 6. 19.       :            : 신규 개발.
 */
package com.kt.smcp.fw;

import com.kt.smcp.fw.util.PropUtil;

/**
 * <PRE>
 * 1. ClassName : CommConstants 
 * 2. FileName  : CommConstants.java
 * 3. Package   : com.kt.plfm.fw
 * 4. Comment   : 
 * 5. 작성자    : kyi
 * 6. 작성일    : 2013. 6. 19. 오후 1:51:10
 * </PRE>
 */ 
public class CommConstants {
	public static final String DEFAULT_CFG_FILE = "properties/com.properties";
	public static final String DEFAULT_MSG_FILE = "message/msg_ko_KR.properties";
	
	public static final String LOGIN_VO   = "LoginVO";

	public static final String DEFAULT_ERR_CODE = "E00000";
	public static final String DEFAULT_ERR_MSG = PropUtil.getMessage(DEFAULT_ERR_CODE);
	
	public static final String DEFAULT_ERR1_CODE = "E00001";
	public static final String DEFAULT_ERR1_MSG = PropUtil.getMessage( DEFAULT_ERR1_CODE);
	
	public static final String LOGIN_USER_ERR = "E10000";
	public static final String LOGIN_USER_MSG = PropUtil.getMessage(LOGIN_USER_ERR);

	public static final String LOGIN_PASS_ERR = "E10001";
	public static final String LOGIN_PASS_MSG = PropUtil.getMessage(LOGIN_PASS_ERR);

	public static final String LOGIN_ROLL_ERR = "E10002";
	public static final String LOGIN_ROLL_MSG = PropUtil.getMessage(LOGIN_ROLL_ERR);
	
	public static final String LOGIN_PAGE_ROLL_ERR = "E10003";
	public static final String LOGIN_PAGE_ROLL_MSG = PropUtil.getMessage(LOGIN_PAGE_ROLL_ERR);
	
	public static final String LOGIN_LOGIN_SESSION_ERR = "E10004";
	public static final String LOGIN_LOGIN_SESSION_MSG = PropUtil.getMessage(LOGIN_LOGIN_SESSION_ERR);
	
	
	public static final String PRC_MODE_INSERT = "INSERT";
	public static final String PRC_MODE_UPDATE = "UPDATE";
	public static final String PRC_MODE_DELETE = "DELETE";	
	public static final String PRC_MODE_VIEW   = "VIEW";
	public static final String PRC_MODE_LOGIN  = "LOGIN";
	public static final String PRC_MODE_TEST   = "TEST";
	
	public static final String PRC_MODE = "prc_mode";
	
	
	
	
}
