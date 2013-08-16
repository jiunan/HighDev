/**
 * 0. Project  : D3M
 *
 * 1. FileName : PageNavigator.java
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

import java.text.MessageFormat;
import java.util.ArrayList;


/**
 * <PRE>
 * 1. ClassName : PageNavigator 
 * 2. FileName  : PageNavigator.java
 * 3. Package   : com.kt.plfm.fw.util
 * 4. Comment   : 
 * 5. 작성자    : kyi
 * 6. 작성일    : 2013. 6. 19. 오후 1:54:42
 * </PRE>
 */ 
public class PageNavigator {

	private long PageNum = 0;
	private long RowPerPage = 10;
	private long MaxPage = 0;
	private long TotCnt = 0;
	private long TotPage = 0;
	private long LastPage = 0;
	private long FirstPage = 1;
	private long CurPage = 0;
	private long MaxViewPage = 10;
	private long StartRow = 1;
	private long EndRow = 0;

	private String nPagePattern =
		"&nbsp;<a\n href=\"#\" onclick=\"gotoPage({0}); return false\"><span class=\"f_page\">{0}</span></a>&nbsp;";
	private String stPageActPattern =
		"&nbsp;<span class=\"f_page_r\">{0}</span>&nbsp;</font>";
	private String stNxtPrvPattern =
		"<a\n href=\"#\" onclick=\"gotoPage({0}); return false\">{1}</a>";

	private String stPrev = "<img src='/images/page_prev_02.gif' align='absmiddle' />&nbsp;";
	private String stNext = "&nbsp;<img src='/images/page_next_02.gif' align='absmiddle' />&nbsp;";
	private String stFw   = "<img src='/images/page_next_01.gif' align='absmiddle' /></div>&nbsp;";
	private String stRw  = "<div align=\"center\"><img src='/images/page_prev_01.gif' align='absmiddle' />&nbsp;";
	private String stPageBreaker = "|";

	private ArrayList arList = new ArrayList();

	/**
	 * <PRE>
	 * 1. MethodName : PageNavigator
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:55:11
	 * </PRE>
	 */ 
	public PageNavigator() {
	}

	/**
	 * <PRE>
	 * 1. MethodName : PageNavigator
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:55:24
	 * </PRE>
	 *   @param ar
	 */ 
	public PageNavigator(ArrayList ar) {
		this.TotCnt = ar.size();
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : PageNavigator
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:55:42
	 * </PRE>
	 *   @param nTotCnt
	 *   @param nCurPage
	 */ 
	public PageNavigator(long nTotCnt,  long nCurPage) {
		this.TotCnt = nTotCnt;
		try {
			this.CurPage =nCurPage;
		} catch (Exception e) {
			this.CurPage = 1;
		}
		setTotPage();
		setStartRow();
		setEndRow();		
	}	

	/**
	 * <PRE>
	 * 1. MethodName : PageNavigator
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:55:53
	 * </PRE>
	 *   @param nTotCnt
	 *   @param nCurPage
	 *   @param pageSize
	 */ 
	public PageNavigator(long nTotCnt,  long nCurPage, long pageSize) {
		this.TotCnt = nTotCnt;
		try {
			this.CurPage =nCurPage;
				
		} catch (Exception e) {
			this.CurPage = 1;
		}
		
		setRowPerPage( pageSize  );
		
		setTotPage();
		setStartRow();
		setEndRow();	
		
	}	
	

	/**
	 * <PRE>
	 * 1. MethodName : PageNavigator
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:56:00
	 * </PRE>
	 *   @param nTotCnt
	 *   @param nCurPage
	 *   @param pageSize
	 *   @param stImageCur
	 */ 
	public PageNavigator(long nTotCnt,  long nCurPage, long pageSize, String stImageCur) {
		this.TotCnt = nTotCnt;
		try {
			this.CurPage =nCurPage;
			this.stPrev = "<img src='"+ stImageCur +"/page_prev_02.gif' align='absmiddle' />&nbsp;";
			this.stNext = "&nbsp;<img src='"+ stImageCur +"/page_next_02.gif' align='absmiddle' />&nbsp;";
			this.stFw   = "<img src='"+ stImageCur +"/page_next_01.gif' align='absmiddle' /></div>&nbsp;";
			this.stRw  = "<div align=\"center\"><img src='"+ stImageCur +"/page_prev_01.gif' align='absmiddle' />&nbsp;";			
		} catch (Exception e) {
			this.CurPage = 1;
		}
		
		setRowPerPage( pageSize  );
		
		setTotPage();
		setStartRow();
		setEndRow();	
		
	}		
	

	/**
	 * <PRE>
	 * 1. MethodName : PageNavigator
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:56:06
	 * </PRE>
	 *   @param nTotCnt
	 *   @param nCurPage
	 *   @param stImageCur
	 */ 
	public PageNavigator(long nTotCnt,  long nCurPage, String stImageCur) {
		this.TotCnt = nTotCnt;
		try {
			this.CurPage =nCurPage;
			this.stPrev = "<img src='"+ stImageCur +"/page_prev_02.gif' align='absmiddle' />&nbsp;";
			this.stNext = "&nbsp;<img src='"+ stImageCur +"/page_next_02.gif' align='absmiddle' />&nbsp;";
			this.stFw   = "<img src='"+ stImageCur +"/page_next_01.gif' align='absmiddle' /></div>&nbsp;";
			this.stRw  = "<div align=\"center\"><img src='"+ stImageCur +"/page_prev_01.gif' align='absmiddle' />&nbsp;";			
		} catch (Exception e) {
			this.CurPage = 1;
		}
		setTotPage();
		setStartRow();
		setEndRow();	
		
	}	
	
	/**
	 * <PRE>
	 * 1. MethodName : PageNavigator
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:56:14
	 * </PRE>
	 *   @param ar
	 *   @param nPage
	 */ 
	public PageNavigator(ArrayList ar, long nPage) {
		this.TotCnt = ar.size();
		this.CurPage = nPage;
		this.arList = ar;
		setTotPage();
		setStartRow();
		setEndRow();
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : setMaxPage
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:56:21
	 * </PRE>
	 *   @return void
	 *   @param max_page
	 */
	public void setMaxPage(long max_page) {
		this.MaxViewPage = max_page;
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : setCurPage
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:56:26
	 * </PRE>
	 *   @return void
	 *   @param curPage
	 */
	public void setCurPage(long curPage) {
		this.CurPage = curPage;
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName : getCurPage
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:56:33
	 * </PRE>
	 *   @return long
	 *   @return
	 */
	public long getCurPage() {
		return this.CurPage;
	}


	
	/**
	 * <PRE>
	 * 1. MethodName : setRowPerPage
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:56:39
	 * </PRE>
	 *   @return void
	 *   @param row_per_page
	 */
	public void setRowPerPage(long row_per_page) {
		this.RowPerPage = row_per_page;
	}

	/**
	 * <PRE>
	 * 1. MethodName : getPageSize
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:56:43
	 * </PRE>
	 *   @return long
	 *   @return
	 */
	public long getPageSize( ) {
		return this.RowPerPage;
	}


	
	/**
	 * <PRE>
	 * 1. MethodName : setPageBreaker
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:56:49
	 * </PRE>
	 *   @return void
	 *   @param page_breaker
	 */
	public void setPageBreaker(String page_breaker) {
		this.stPageBreaker = page_breaker;
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName : setTotCnt
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:56:56
	 * </PRE>
	 *   @return void
	 *   @param totCnt
	 */
	public void setTotCnt(long totCnt) {
		this.TotCnt = totCnt;
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : setTotPage
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:57:01
	 * </PRE>
	 *   @return void
	 */
	public void setTotPage() {
		this.TotPage = (TotCnt + RowPerPage - 1) / RowPerPage;
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : setLastPage
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:57:07
	 * </PRE>
	 *   @return void
	 */
	public void setLastPage() {
		this.LastPage = this.TotPage;
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : setPrev
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:57:12
	 * </PRE>
	 *   @return void
	 *   @param prev
	 */
	public void setPrev(String prev) {
		this.stPrev = prev;
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : setNext
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:57:17
	 * </PRE>
	 *   @return void
	 *   @param next
	 */
	public void setNext(String next) {
		this.stNext = next;
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : setStartRow
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:57:24
	 * </PRE>
	 *   @return void
	 */
	public void setStartRow(){
		this.StartRow = (this.CurPage - 1) * this.RowPerPage + 1;
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName : getStartRow
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:57:29
	 * </PRE>
	 *   @return long
	 *   @return
	 */
	public long getStartRow(){
		return this.StartRow;
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : setEndRow
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:57:35
	 * </PRE>
	 *   @return void
	 */
	public void setEndRow(){
		this.EndRow = (this.CurPage)*this.RowPerPage;
	}
	
	
	/**
	 * <PRE>
	 * 1. MethodName : getEndRow
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:57:40
	 * </PRE>
	 *   @return long
	 *   @return
	 */
	public long getEndRow(){
		return this.EndRow;
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : getPageLink
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:57:45
	 * </PRE>
	 *   @return String
	 *   @param pageNo
	 *   @param active
	 *   @return
	 */
	protected String getPageLink(long pageNo, boolean active) {
		return MessageFormat.format(
			(active ? this.stPageActPattern : this.nPagePattern),
			new Object[] { Long.toString(pageNo)});
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : getPageNavigation
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:57:51
	 * </PRE>
	 *   @return String
	 *   @return
	 */
	public String getPageNavigation() {
		
		if (this.TotCnt < this.RowPerPage ) return "";
		StringBuffer sb = new StringBuffer();
		sb.append(getFastNxtPrv("Prv"));
		sb.append(getNxtPrv("Prv"));

		long pageSet = (this.CurPage - 1) / this.MaxViewPage;
		long startPageSet = this.MaxViewPage * pageSet + 1;
		for (long i = startPageSet; 	i <= this.TotPage && i <= (pageSet + 1) * this.MaxViewPage;	i++) {
			sb.append(this.getPageLink(i, (i == this.CurPage) ? true : false));
			if ( i < this.TotPage && i < (pageSet + 1) * this.MaxViewPage  ) {
				sb.append(this.stPageBreaker);
			}
		}
		sb.append(getNxtPrv("Nxt"));
		sb.append(getFastNxtPrv("Nxt"));
		return sb.toString();
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : getNxtPrv
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:58:01
	 * </PRE>
	 *   @return String
	 *   @param tp
	 *   @return
	 */
	public String getNxtPrv(String tp) {
//		String strNxtPrv = new String();
//		String strCont = new String();
//		long pageNo = 0;
//		boolean flag = false;
//		if (tp.equals("Prv")) {
//			strCont = this.stPrev;
//			if (this.CurPage > this.FirstPage) {
//				flag = true;
//				pageNo = this.CurPage - 1;
//			}
//		} else if (tp.equals("Nxt")) {
//			strCont = this.stNext;
//			if (this.CurPage < this.TotPage) {
//				flag = true;
//				pageNo = this.CurPage + 1;
//			}
//		}
//
//		strNxtPrv =
//			MessageFormat.format(
//				this.stNxtPrvPattern,
//				new Object[] { longeger.toString(pageNo), strCont });
//		return (flag) ? strNxtPrv : strCont;
		
		String strNxtPrv = new String();
		String strCont = new String();
		long pageNo = 0;
		boolean flag = false;
		if (tp.equals("Prv")) {
			strCont = this.stPrev;
			if (this.CurPage > this.MaxViewPage && (this.CurPage % this.MaxViewPage) >= 0) {
				flag = true;
				pageNo =
					(this.CurPage - 1) / this.MaxViewPage * this.MaxViewPage;
			}
		} else if (tp.equals("Nxt")) {
			strCont = this.stNext;
			if ((this.CurPage -1) / this.MaxViewPage
				< (this.TotPage - 1) / this.MaxViewPage) {
				flag = true;
				if((this.CurPage % this.MaxViewPage) == 0){
					pageNo = ((this.CurPage / this.MaxViewPage) * this.MaxViewPage) + 1;
				}else{
					pageNo =
						(((this.CurPage + this.MaxViewPage) / this.MaxViewPage) * this.MaxViewPage) + 1;
						//((this.CurPage + 1) / this.MaxViewPage) * this.MaxViewPage;
				}
			}
		}

		strNxtPrv =
			MessageFormat.format(
				this.stNxtPrvPattern,
				new Object[] { Long.toString(pageNo), strCont });

		return (flag) ? strNxtPrv : strCont;
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : getFastNxtPrv
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:58:08
	 * </PRE>
	 *   @return String
	 *   @param tp
	 *   @return
	 */
	public String getFastNxtPrv(String tp) {
//		String strFastNxtPrv = new String();
//		String strCont = new String();
//		int pageNo = 0;
//		boolean flag = false;
//		if (tp.equals("Prv")) {
//			strCont = this.stRw;
//			if (this.CurPage > this.MaxViewPage && (this.CurPage % nMaxViewPage) >= 0) {
//				flag = true;
//				pageNo =
//					(this.CurPage - 1) / this.MaxViewPage * this.MaxViewPage;
//			}
//		} else if (tp.equals("Nxt")) {
//			strCont = this.stFw;
//			if ((this.CurPage - 1) / this.MaxViewPage
//				< this.TotPage / this.MaxViewPage && this.TotPage > this.MaxViewPage) {
//				flag = true;
//				if((this.CurPage % this.MaxViewPage) == 0){
//					pageNo = ((this.CurPage / this.MaxViewPage) * this.MaxViewPage) + 1;
//				}else{
//					pageNo =
//						(((this.CurPage + this.MaxViewPage) / this.MaxViewPage) * this.MaxViewPage) + 1;
//						//((this.CurPage + 1) / this.MaxViewPage) * this.MaxViewPage;
//				}
//			}
//		}
//
//		strFastNxtPrv =
//			MessageFormat.format(
//				this.stNxtPrvPattern,
//				new Object[] { Integer.toString(pageNo), strCont });
//
//		return (flag) ? strFastNxtPrv : strCont;
		
		String strFastNxtPrv = new String();
		String strCont = new String();
		long pageNo = 0;
		boolean flag = false;
		if (tp.equals("Prv")) {
			strCont = this.stRw;
			if (this.CurPage > this.MaxViewPage && (this.CurPage % this.MaxViewPage) >= 0) {
				flag = true;
				pageNo = 1;
			}
		} else if (tp.equals("Nxt")){
			strCont = this.stFw;
			
			if ((this.CurPage - 1) / this.MaxViewPage
				< (this.TotPage - 1) / this.MaxViewPage) {
				flag = true;
				if((this.TotPage % this.MaxViewPage) == 0){
					pageNo = (((this.TotPage - 1) / this.MaxViewPage) * this.MaxViewPage) + 1;
						//((this.CurPage + 1) / this.MaxViewPage) * this.MaxViewPage;
				}else{
					//pageNo = ((this.TotPage / this.MaxViewPage) * this.MaxViewPage) + 1;
					// �������� ������ ��쿡��?��ü�������� ������ �������� �̵��Ѵ�.
					pageNo = this.TotPage; 
				}		
			}
		}

		strFastNxtPrv =
			MessageFormat.format(
				this.stNxtPrvPattern,
				new Object[] { Long.toString(pageNo), strCont });

		return (flag) ? strFastNxtPrv : strCont;
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : getJavaScript
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:58:16
	 * </PRE>
	 *   @return String
	 *   @param form_name
	 *   @return
	 */
	public String getJavaScript(String form_name) {
		StringBuffer sb = new StringBuffer();
		sb
			.append("<script language=\"javascript\">\n")
			.append("function gotoPage(page){\n")
			.append("	form = document.forms[\"" + form_name + "\"];\n")
			.append("	form.CurPage.value = page;\n")
			.append("	form.StartRow.value = (page-1)*"+this.RowPerPage+"+1;\n")
			.append("	form.EndRow.value = page*"+this.RowPerPage+";\n")
			//.append("	form.target='';\n")
			//.append("	form.action='';\n")
			.append("	form.submit();\n")
			.append("}\n")
			.append("function initPage(){\n")
			.append("	form = document.forms[\"" + form_name + "\"];\n")
			.append("	form.CurPage.value = \"\";\n")
			.append("	form.StartRow.value = \"\";\n")
			.append("	form.EndRow.value = \"\";\n")
			.append("}\n")
			.append("</script>\n");
		return sb.toString();
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : getJavaScript
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:58:21
	 * </PRE>
	 *   @return String
	 *   @param form_name
	 *   @param gotoPageInit
	 *   @param initPageInit
	 *   @return
	 */
	public String getJavaScript(String form_name, String gotoPageInit, String initPageInit) {
		StringBuffer sb = new StringBuffer();
		sb
			.append("<script language=\"javascript\">\n")
			.append("function gotoPage(page){\n")
            .append("   " + gotoPageInit )
			.append("	form = document.forms[\"" + form_name + "\"];\n")
			.append("	form.CurPage.value = page;\n")
			.append("	form.StartRow.value = (page-1)*"+this.RowPerPage+"+1;\n")
			.append("	form.EndRow.value = page*"+this.RowPerPage+";\n")
			//.append("	form.target='';\n")
			//.append("	form.action='';\n")
			.append("	form.submit();\n")
			.append("}\n")
			.append("function initPage(){\n")
            .append("   " + initPageInit )
			.append("	form = document.forms[\"" + form_name + "\"];\n")
			.append("	form.CurPage.value = \"\";\n")
			.append("	form.StartRow.value = \"\";\n")
			.append("	form.EndRow.value = \"\";\n")
			.append("}\n")
			.append("</script>\n");
		return sb.toString();
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : getJavaScript
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:58:27
	 * </PRE>
	 *   @return String
	 *   @return
	 */
	public String getJavaScript() {
		StringBuffer sb = new StringBuffer();
		sb
			.append("<script language=\"javascript\">\n")
			.append("function gotoPage(page){\n")
			.append("	form = document.forms[0];\n")
			.append("	form.CurPage.value = page;\n")
			.append("	form.StartRow.value = (page-1)*"+this.RowPerPage+"+1;\n")
			.append("	form.EndRow.value = page*"+this.RowPerPage+";\n")
			//.append("	form.target='';\n")
			//.append("	form.action='';\n")
			.append("	form.submit();\n")
			.append("}\n")
			.append("function initPage(){\n")
			.append("	form = document.forms[0];\n")
			.append("	form.CurPage.value = \"\";\n")
			.append("	form.StartRow.value = \"\";\n")
			.append("	form.EndRow.value = \"\";\n")
			.append("}\n")
			.append("</script>");
		return sb.toString();
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : getHtmlForm
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:58:32
	 * </PRE>
	 *   @return String
	 *   @return
	 */
	public String getHtmlForm() {
		StringBuffer sb = new StringBuffer();
		sb.append("<input type=\"hidden\" name=\"page\" value=" + this.CurPage + ">")
			.append("<input type=\"hidden\" name=\"StartRow\" value=" + this.StartRow + ">")
			.append("<input type=\"hidden\" name=\"EndRow\" value=" + this.EndRow + ">");
		return sb.toString();
	}

	/**
	 * <PRE>
	 * 1. MethodName : getTotPage
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:58:34
	 * </PRE>
	 *   @return long
	 *   @return
	 */
	public long getTotPage() {
		return TotPage;
	}

	/**
	 * <PRE>
	 * 1. MethodName : setTotPage
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:58:37
	 * </PRE>
	 *   @return void
	 *   @param totPage
	 */
	public void setTotPage(long totPage) {
		TotPage = totPage;
	}

	/**
	 * <PRE>
	 * 1. MethodName : getTotCnt
	 * 2. ClassName  : PageNavigator
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:58:39
	 * </PRE>
	 *   @return long
	 *   @return
	 */
	public long getTotCnt() {
		return TotCnt;
	}

	
	

}