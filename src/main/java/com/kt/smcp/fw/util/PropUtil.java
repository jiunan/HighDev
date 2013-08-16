/**
 * 0. Project  : D3M
 *
 * 1. FileName : PropUtil.java
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

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.smcp.fw.CommConstants;


/**
 * <PRE>
 * 1. ClassName : PropUtil 
 * 2. FileName  : PropUtil.java
 * 3. Package   : com.kt.plfm.fw.util
 * 4. Comment   : 
 * 5. 작성자    : kyi
 * 6. 작성일    : 2013. 6. 19. 오후 1:58:56
 * </PRE>
 */ 
public class PropUtil {
	private static final Logger log = LoggerFactory.getLogger( PropUtil.class );
	private static HashMap HM_PROP = new HashMap();
	
	/**
	 * <PRE>
	 * 1. MethodName : getPropValue
	 * 2. ClassName  : PropUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:59:32
	 * </PRE>
	 *   @return String
	 *   @param propCode
	 *   @return
	 */
	public static String getPropValue( String propCode ) {
		return  getValues( CommConstants.DEFAULT_CFG_FILE , propCode)  ;
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : getPropValue
	 * 2. ClassName  : PropUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:59:39
	 * </PRE>
	 *   @return String
	 *   @param propCode
	 *   @param param
	 *   @return
	 */
	public static String getPropValue( String propCode, String param ) {
		return StringUtil.get( getMessage( propCode ), param );
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : getPropValue
	 * 2. ClassName  : PropUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:59:42
	 * </PRE>
	 *   @return String
	 *   @param propCode
	 *   @param paramArr
	 *   @return
	 */
	public static String getPropValue( String propCode, String paramArr[] ) {
		return StringUtil.get( getMessage( propCode ), paramArr );
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : getMessage
	 * 2. ClassName  : PropUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:59:45
	 * </PRE>
	 *   @return String
	 *   @param msgCode
	 *   @return
	 */
	public static String getMessage( String msgCode ) {
		return  getValues( CommConstants.DEFAULT_MSG_FILE , msgCode)  ;
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : getMessage
	 * 2. ClassName  : PropUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:59:48
	 * </PRE>
	 *   @return String
	 *   @param msgCode
	 *   @param param
	 *   @return
	 */
	public static String getMessage( String msgCode, String param ) {
		return StringUtil.get( getMessage( msgCode ), param );
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : getMessage
	 * 2. ClassName  : PropUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:59:50
	 * </PRE>
	 *   @return String
	 *   @param msgCode
	 *   @param paramArr
	 *   @return
	 */
	public static String getMessage( String msgCode, String paramArr[] ) {
		return StringUtil.get( getMessage( msgCode ), paramArr );
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : clearHmProperties
	 * 2. ClassName  : PropUtil
	 * 3. Comment    : 
	 * 4. 작성자     : kyi
	 * 5. 작성일     : 2013. 6. 19. 오후 1:59:53
	 * </PRE>
	 *   @return void
	 */
	public static void clearHmProperties( ) {
		HM_PROP = new HashMap();
		log.debug("\n\n\n\n\n");
		log.debug("#######################################################" );
		log.debug("#######################################################" );
		log.debug("############### Properties Reload Sucess ##############" );
		log.debug("#######################################################" );
		log.debug("#######################################################" );
		log.debug("\n\n\n\n\n");
	}
	
    /**
     * <PRE>
     * 1. MethodName : getValues
     * 2. ClassName  : PropUtil
     * 3. Comment    : 
     * 4. 작성자     : kyi
     * 5. 작성일     : 2013. 6. 19. 오후 1:59:56
     * </PRE>
     *   @return String
     *   @param fileName
     *   @param propName
     *   @return
     */
    public static String getValues (String fileName, String propName){				
        String value = null;	
		Properties prop = null;
		  
		// HM_PROP 에서 해당 hm 있으면 
		 try{ 
			  prop = (Properties)HM_PROP.get( fileName ) ;
		 }catch(Exception e1 ) {
			 log.error( "[getPropValues] - e1 :" + e1);	
		 }

		 if( prop == null ) {
			 try{
				 InputStream fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
				 
				 prop = new Properties();				  				 				 
				 prop.load( fis );
				 HM_PROP.put ( fileName , prop ) ;

				 fis.close();
			 }catch( Exception e2 ) {
				 log.error( "[getPropValues] - e2 :" + e2);	
			 }
		 }
      
        try{          
            value = new String(prop.getProperty(propName, null ));
            value = Uni2Utf( value );
        }catch(Exception e){
        	log.error(
            "해당 properties["+propName+"]가 존재하지 않습니다.1==>" + fileName + "=="+ e);
        }
        return value;   		
    }

    /**
     * <PRE>
     * 1. MethodName : Uni2Utf
     * 2. ClassName  : PropUtil
     * 3. Comment    : 
     * 4. 작성자     : kyi
     * 5. 작성일     : 2013. 6. 19. 오후 2:00:00
     * </PRE>
     *   @return String
     *   @param UnicodeStr
     *   @return
     */
    public static String Uni2Utf(String UnicodeStr) {
  	  try{
  	      if (UnicodeStr == null) {
  	          return null;
  	      } else {
  	          return new String(UnicodeStr.getBytes("ISO-8859-1"), "UTF-8");
  	      }
  	  }catch( Exception e ) {
  		  log.error( e.toString() );
  	  }
  	  return UnicodeStr;
    } 
}
