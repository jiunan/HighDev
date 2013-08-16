/**
 * 0. Project  : D3M
 *
 * 1. FileName : CommHeaderVO.java
 * 2. Package  : com.kt.plfm.fw.comm.vo
 * 3. Comment  : 
 * 4. 작성자   : kyi
 * 5. 작성일   : 2013. 6. 19. 오후 1:26:14
 * 6. 변경이력 : 
 *               이름     : 일자          : 근거자료   : 변경내용
 *               ------------------------------------
 *               kyi  : 2013. 6. 19.       :            : 신규 개발.
 */
package com.kt.smcp.fw.comm.vo;

import java.io.Serializable;

/**
 * <PRE>
 * 1. ClassName : CommHeaderVO 
 * 2. FileName  : CommHeaderVO.java
 * 3. Package   : com.kt.plfm.fw.comm.vo
 * 4. Comment   : 
 * 5. 작성자    : kyi
 * 6. 작성일    : 2013. 6. 19. 오후 2:08:18
 * </PRE>
 */ 
public class CommHeaderVO implements Serializable{
	
	private int    result       = 0 ;
	private String msg          = "";
	
	private String errorCode    = "" ;
	private String errorMsg     = "";
	
	private String redirect_url = "";
	private String user_id      = "";	

	

	/**
	 * <PRE>
	 * 1. MethodName : getErrorCode
	 * 2. ClassName  : CommHeaderVO
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:08:22
	 * </PRE>
	 *   @return String
	 *   @return
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * <PRE>
	 * 1. MethodName : setErrorCode
	 * 2. ClassName  : CommHeaderVO
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:08:26
	 * </PRE>
	 *   @return void
	 *   @param errorCode
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * <PRE>
	 * 1. MethodName : getErrorMsg
	 * 2. ClassName  : CommHeaderVO
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:08:28
	 * </PRE>
	 *   @return String
	 *   @return
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * <PRE>
	 * 1. MethodName : setErrorMsg
	 * 2. ClassName  : CommHeaderVO
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:08:31
	 * </PRE>
	 *   @return void
	 *   @param errorMsg
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	/**
	 * <PRE>
	 * 1. MethodName : getResult
	 * 2. ClassName  : CommHeaderVO
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:08:33
	 * </PRE>
	 *   @return int
	 *   @return
	 */
	public int getResult() {
		return result;
	}
	/**
	 * <PRE>
	 * 1. MethodName : setResult
	 * 2. ClassName  : CommHeaderVO
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:08:36
	 * </PRE>
	 *   @return void
	 *   @param result
	 */
	public void setResult(int result) {
		this.result = result;
	}		
	/**
	 * <PRE>
	 * 1. MethodName : getMsg
	 * 2. ClassName  : CommHeaderVO
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:08:42
	 * </PRE>
	 *   @return String
	 *   @return
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * <PRE>
	 * 1. MethodName : setMsg
	 * 2. ClassName  : CommHeaderVO
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:08:45
	 * </PRE>
	 *   @return void
	 *   @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * <PRE>
	 * 1. MethodName : getUser_id
	 * 2. ClassName  : CommHeaderVO
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:08:47
	 * </PRE>
	 *   @return String
	 *   @return
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * <PRE>
	 * 1. MethodName : setUser_id
	 * 2. ClassName  : CommHeaderVO
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:08:50
	 * </PRE>
	 *   @return void
	 *   @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * <PRE>
	 * 1. MethodName : getAccept_kind
	 * 2. ClassName  : CommHeaderVO
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:08:52
	 * </PRE>
	 *   @return String
	 *   @return
	 */

	public String getRedirect_url() {
		return redirect_url;
	}
	/**
	 * <PRE>
	 * 1. MethodName : setRedirect_url
	 * 2. ClassName  : CommHeaderVO
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:09:05
	 * </PRE>
	 *   @return void
	 *   @param redirect_url
	 */
	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}
	/* (비Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommHeaderVO [result=" + result + ", msg=" + msg
				+ ", errorCode=" + errorCode + ", errorMsg=" + errorMsg
				+ ", redirect_url=" + redirect_url + ", user_id=" + user_id
				+ "]";
	}
	
}
