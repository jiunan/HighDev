/**
 * 0. Project  : smcp
 *
 * 1. FileName : CommVO.java
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

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <PRE>
 * 1. ClassName : CommVO
 * 2. FileName  : CommVO.java
 * 3. Package   : com.kt.plfm.fw.comm.vo
 * 4. Comment   :
 * 5. 작성자    : kyi
 * 6. 작성일    : 2013. 6. 19. 오후 2:10:03
 * </PRE>
 */
public class CommVO implements Serializable {
	protected CommHeaderVO commHeaderVO = new CommHeaderVO();

	protected int curPage    = 1  ;
	protected int pageSize   = 10 ;
	protected int maxViewPage= 10 ;

	protected int rnum;
	protected int startNum;
	protected int endNum;
	protected int totalCnt = 0;

	/**
	 * <PRE>
	 * 1. MethodName : getCommHeaderVO
	 * 2. ClassName  : CommVO
	 * 3. Comment    :
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:10:09
	 * </PRE>
	 *   @return CommHeaderVO
	 *   @return
	 */
	public CommHeaderVO getCommHeaderVO() {
		return commHeaderVO;
	}

	/**
	 * <PRE>
	 * 1. MethodName : getCurPage
	 * 2. ClassName  : CommVO
	 * 3. Comment    :
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:10:11
	 * </PRE>
	 *   @return String
	 *   @return
	 */
	public int getCurPage() {
		return curPage;
	}

	/**
	 * <PRE>
	 * 1. MethodName : setCurPage
	 * 2. ClassName  : CommVO
	 * 3. Comment    :
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:10:14
	 * </PRE>
	 *   @return void
	 *   @param curPage
	 */
	/**
	 * <PRE>
	 * 1. MethodName : setCurPage
	 * 2. ClassName  : CommVO
	 * 3. Comment    :
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:10:17
	 * </PRE>
	 *   @return void
	 *   @param curPage
	 */
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	/**
	 * <PRE>
	 * 1. MethodName : getPageSize
	 * 2. ClassName  : CommVO
	 * 3. Comment    :
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:10:19
	 * </PRE>
	 *   @return String
	 *   @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * <PRE>
	 * 1. MethodName : setPageSize
	 * 2. ClassName  : CommVO
	 * 3. Comment    :
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:10:22
	 * </PRE>
	 *   @return void
	 *   @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * <PRE>
	 * 1. MethodName : getMaxViewPage
	 * 2. ClassName  : CommVO
	 * 3. Comment    :
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:10:24
	 * </PRE>
	 *   @return int
	 *   @return
	 */
	public int getMaxViewPage() {
		return maxViewPage;
	}

	/**
	 * <PRE>
	 * 1. MethodName : setMaxViewPage
	 * 2. ClassName  : CommVO
	 * 3. Comment    :
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:10:27
	 * </PRE>
	 *   @return void
	 *   @param maxViewPage
	 */
	public void setMaxViewPage(int maxViewPage) {
		this.maxViewPage = maxViewPage;
	}

	/**
	 * <PRE>
	 * 1. MethodName : getRown
	 * 2. ClassName  : CommVO
	 * 3. Comment    :
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:10:30
	 * </PRE>
	 *   @return String
	 *   @return
	 */
	public int getRnum() {
		return rnum;
	}

	/**
	 * <PRE>
	 * 1. MethodName : setRown
	 * 2. ClassName  : CommVO
	 * 3. Comment    :
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:10:33
	 * </PRE>
	 *   @return void
	 *   @param rown
	 */
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	/**
	 * <PRE>
	 * 1. MethodName : setCommHeaderVO
	 * 2. ClassName  : CommVO
	 * 3. Comment    :
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:10:41
	 * </PRE>
	 *   @return void
	 *   @param commHeaderVO
	 */
	public void setCommHeaderVO(CommHeaderVO commHeaderVO) {
		this.commHeaderVO = commHeaderVO;
	}

	/**
	 * <PRE>
	 * 1. MethodName : serializeVO
	 * 2. ClassName  : CommVO
	 * 3. Comment    :
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:10:44
	 * </PRE>
	 *   @return byte[]
	 *   @return
	 *   @throws Exception
	 */
	public byte[] serializeVO() throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
        ObjectOutputStream oout = new ObjectOutputStream(bout);
        oout.writeObject(this);
        oout.flush();
        oout.reset();
        bout.close();
        return bout.toByteArray();
	}
}
