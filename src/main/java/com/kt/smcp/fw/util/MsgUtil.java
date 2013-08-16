/**
 * 0. Project  : D3M
 *
 * 1. FileName : MsgUtil.java
 * 2. Package  : com.kt.plfm.fw.util
 * 3. Comment  : 
 * 4. 작성자   : kyi
 * 5. 작성일   : 2013. 6. 19. 오후 1:26:14
 * 6. 변경이력 : 
 *               이름     : 일자          : 근거자료   : 변경내용
 *               ------------------------------------
 *               kyi  : 2013. 6. 19.       :            : 신규 개발.
 */
package com.kt.smcp.fw.util;

import java.util.Locale;

import org.springframework.context.support.MessageSourceAccessor;

/**
 * <PRE>
 * 1. ClassName : MsgUtil 
 * 2. FileName  : MsgUtil.java
 * 3. Package   : com.kt.plfm.fw.util
 * 4. Comment   : 
 * 5. 작성자    : kyi
 * 6. 작성일    : 2013. 6. 19. 오후 1:53:01
 * </PRE>
 */ 
public class MsgUtil {
	private  static MessageSourceAccessor msAcc  = null;
	
	/**
	 * <PRE>
	 * 1. MethodName : setMessageSourceAccessor
	 * 2. ClassName  : MsgUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:53:06
	 * </PRE>
	 *   @return void
	 *   @param msAcc
	 */
	public void setMessageSourceAccessor(MessageSourceAccessor  msAcc){
		MsgUtil.msAcc = msAcc;
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName : getMessage
	 * 2. ClassName  : MsgUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:53:25
	 * </PRE>
	 *   @return String
	 *   @param key
	 *   @return
	 */
	public static String getMessage(String key){
		return msAcc.getMessage(key, Locale.getDefault());
		
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName : getMessage
	 * 2. ClassName  : MsgUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:53:32
	 * </PRE>
	 *   @return String
	 *   @param key
	 *   @param objs
	 *   @return
	 */
	public static String getMessage(String key, Object[] objs){
		return msAcc.getMessage(key, objs, Locale.getDefault());
		
	}
}
