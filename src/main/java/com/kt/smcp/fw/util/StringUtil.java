/**
 * 0. Project  : D3M
 *
 * 1. FileName : KtException.java
 * 2. Package  : com.kt.plfm.fw.exception
 * 3. Comment  : 
 * 4. 작성자   : kyi
 * 5. 작성일   : 2013. 6. 19. 오후 1:26:14
 * 6. 변경이력 : 
 *               이름     : 일자          : 근거자료   : 변경내용
 *               ------------------------------------
 *               kyi  : 2013. 6. 19.       :            : 신규 개발.
 */
package com.kt.smcp.fw.util;

/**
 * <PRE>
 * 1. ClassName : StringUtil 
 * 2. FileName  : StringUtil.java
 * 3. Package   : com.kt.plfm.fw.util
 * 4. Comment   : 
 * 5. 작성자    : kyi
 * 6. 작성일    : 2013. 6. 19. 오후 2:00:09
 * </PRE>
 */ 
public class StringUtil {
    /**
     * <PRE>
     * 1. MethodName : safeTrim
     * 2. ClassName  : StringUtil
     * 3. Comment    : 
     * 4. 작성자     : kyi
     * 5. 작성일     : 2013. 6. 19. 오후 2:00:11
     * </PRE>
     *   @return String
     *   @param str
     *   @return
     */
    public static String safeTrim(String str) {
	    if (str != null) {
	      String s = new String(str.trim());
	      return s;
	    } else
	      return (String) "";
    }
    
	/**
	 * <PRE>
	 * 1. MethodName : isSelected
	 * 2. ClassName  : StringUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:00:14
	 * </PRE>
	 *   @return String
	 *   @param stValue
	 *   @param stSelectValue
	 *   @return
	 */
	public static String isSelected( String stValue, String stSelectValue ) {
		if( stSelectValue != null ) {
			if( stValue == null ) stValue = "";

			if( stSelectValue.equals(stValue) ) {
				return " selected";
			}else {
				return "";
			}
		}else {
			return "";
		}
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : isChecked
	 * 2. ClassName  : StringUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:00:17
	 * </PRE>
	 *   @return String
	 *   @param stVal1
	 *   @param stVal2
	 *   @return
	 */
	public static String isChecked(String stVal1, String stVal2){
		String stCheck = "";
		if(stVal1 != null && stVal2 != null && stVal1.equals(stVal2)){
			stCheck = "checked";
		}
		return stCheck;
	}	
	
	/**
	 * <PRE>
	 * 1. MethodName : decodeX
	 * 2. ClassName  : StringUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:00:20
	 * </PRE>
	 *   @return String
	 *   @param s
	 *   @return
	 */
	public static String decodeX(String s)
    {
        try
        {
            return HextoUTF8(s);
        }
        catch(Exception exception)
        {
            return null;
        }
    }

	/**
	 * <PRE>
	 * 1. MethodName : HextoUTF8
	 * 2. ClassName  : StringUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:00:22
	 * </PRE>
	 *   @return String
	 *   @param s
	 *   @return
	 */
	public static  String HextoUTF8(String s)
    {
        byte abyte0[] = new byte[s.length() / 2];
        byte abyte1[] = s.getBytes();

        for(int i = 0; i * 2 < s.length(); i++)
        {
            String s1 = new String(abyte1, i * 2, 2);
            abyte0[i] = Integer.valueOf(s1, 16).byteValue();
        }

        try
        {
            return new String(abyte0, "UTF8");
        }
        catch(Exception exception)
        {
            return null;
        }
    }
	
	
	
	/**
	 * <PRE>
	 * 1. MethodName : get
	 * 2. ClassName  : StringUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:00:31
	 * </PRE>
	 *   @return String
	 *   @param sRetMsg
	 *   @param psMsg
	 *   @return
	 */
	public static String get(String sRetMsg, String psMsg)	{		
		if (sRetMsg != null)
			sRetMsg = replace(sRetMsg, "{0}", psMsg);
		
		return sRetMsg;
	}

	
	/**
	 * <PRE>
	 * 1. MethodName : get
	 * 2. ClassName  : StringUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 2:00:37
	 * </PRE>
	 *   @return String
	 *   @param sRetMsg
	 *   @param psMsgs
	 *   @return
	 */
	public static String get(String sRetMsg, String[] psMsgs)	{

		if (sRetMsg != null)
		{
			for (int i = 0; i < psMsgs.length; i++)
				sRetMsg = replace(sRetMsg, "{" + i + "}", psMsgs[i]);
		}
		
		return sRetMsg;
	}
	
    /**
     * <PRE>
     * 1. MethodName : replace
     * 2. ClassName  : StringUtil
     * 3. Comment    : 
     * 4. 작성자     : kyi
     * 5. 작성일     : 2013. 6. 19. 오후 2:00:42
     * </PRE>
     *   @return String
     *   @param source
     *   @param fromStr
     *   @param toStr
     *   @return
     */
    public static String replace(String source, String fromStr, String toStr) {
        if (source == null)
          return null;
        int start = 0;
        int end = 0;
        StringBuffer result = new StringBuffer();
        while ( (end = source.indexOf(fromStr, start)) >= 0) {
          result.append(source.substring(start, end));
          result.append(toStr);
          start = end + fromStr.length();
        }
        result.append(source.substring(start));
        return result.toString();
      }	
		
}
