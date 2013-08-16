
package com.kt.smcp.fw.log;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.smcp.fw.util.MsgUtil;


/**
 * <PRE>
 * 1. ClassName : ComLog 
 * 2. FileName  : ComLog.java
 * 3. Package   : com.kt.plfm.fw.log
 * 4. Comment   : 
 * 5. 작성자    : kyi
 * </PRE>
 */ 
public class ComLog {
	
	/** logger static 변수 선언*/
	private static Logger logger;
	
	/**
	 * <PRE>
	 * 1. MethodName : debug
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 *   @param msg
	 */
	public static void debug(String msg){
		logger = LoggerFactory.getLogger("Console");
		logger.debug(msg);		
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : debug
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 *   @param obj
	 *   @param msg
	 */
	public static void debug(Class obj, String msg){
		logger = LoggerFactory.getLogger("Console");

		logger.debug("[" + obj.getName() + "] " + msg);		
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : debug
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 *   @param obj
	 *   @param msg
	 */
	public static void debug(Object obj, int msg){
		logger = LoggerFactory.getLogger("Console");
		
		logger.debug("[" + obj.getClass().getName() + "] " + msg);
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : info
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 *   @param msg
	 */
	public static void info(String msg){
		logger = LoggerFactory.getLogger("Console");

		logger.info("[ == " + msg + " == ]");
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : warn
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 *   @param msg
	 */
	public static void warn(String msg){
		logger = LoggerFactory.getLogger("Console");

		logger.warn("[ == " + msg + " == ]");
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : error
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 *   @param obj
	 *   @param msg
	 */
	public static void error(Class obj, String msg){
		logger = LoggerFactory.getLogger("Console");
		
		logger.error("[" + obj.getName() + "] " + msg);
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : error
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 *   @param msg
	 */
	public static void error(String msg){
		logger = LoggerFactory.getLogger("Console");
		
		logger.error("[" + msg +  "] ");
	}

	
	
	/** 반복 출력 문자의 폭(길이) */
	private static final int REPEAT_WIDTH = 80;
	/** 공백 */
	private static final String SPACE = " ";
	/** 줄바꿈문자 */
	private static final String NEW_LINE = "\n";
	
	/**
	 * <PRE>
	 * 1. MethodName : printAsHexCode
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 *   @param buffer
	 *   @param rowCount
	 */
	public static void printAsHexCode (byte[] buffer, int rowCount) {
		StringBuffer logMsg = new StringBuffer();
    	for (int inx = 0; inx < buffer.length; inx++) {
    		if (rowCount > 0 && inx != 0 && (inx % rowCount) == 0) {
    			logMsg.append(NEW_LINE);
    		}
    		logMsg.append(String.format("%02X" + SPACE, buffer[inx]));
    	}
    	println(logMsg.toString());
	}

	/**
	 * <PRE>
	 * 1. MethodName : printAsString
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 *   @param buffer
	 *   @param title
	 */
	public static void printAsString (String buffer, String title) {
		int charLength = REPEAT_WIDTH;
		
		println(getRepearString("*", (charLength)));
		println(title);
		println(getRepearString("-", (charLength)));
		println(buffer);
		println(getRepearString("-", (charLength)));
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : printAsString
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 *   @param buffer
	 *   @param encoding
	 *   @param title
	 */
	public static void printAsString (byte[] buffer, String encoding, String title) {
		int charLength = REPEAT_WIDTH;
		try {
			String message = (encoding != null ? (new String(buffer, encoding)) : new String(buffer));
			printAsString(message, title);
		} catch (UnsupportedEncodingException e) {
			println ("ComLog.printAsString() "+MsgUtil.getMessage("fail.logger.msg")+":[" + e.getMessage() + "]");
		}
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : printAsHexCode
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 *   @param buffer
	 *   @param rowCount
	 *   @param title
	 */
	public static void printAsHexCode (byte[] buffer, int rowCount, String title) {
		int titleLentgh = title != null ? title.getBytes().length : 10;
		int defaultCount = (buffer != null && buffer.length > 10) ? 
				REPEAT_WIDTH : titleLentgh;
		boolean isDefault = !(rowCount > 0 && (rowCount * 3 > titleLentgh));
		
		println(getRepearString("*", (!isDefault ? (rowCount) * 3: defaultCount)));
		println(title);
		println(getRepearString("-", (!isDefault ? (rowCount) * 3: defaultCount)));
		printAsHexCode(buffer, rowCount);
		println(getRepearString("-", (!isDefault ? (rowCount) * 3: defaultCount)));
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : getRepearString
	 * 2. Comment    : 
	 * </PRE>
	 *   @return String
	 *   @param value
	 *   @param repeatCount
	 *   @return
	 */
	public static String getRepearString (String value, int repeatCount) {
		StringBuffer sbRes = new StringBuffer();
		for (int inx = 0; inx < repeatCount; inx ++) {
			sbRes.append(value);
		}
		return sbRes.toString();
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : println
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 */
	public static void println () {
		ComLog.debug ("");
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : println
	 * 2. Comment    : 
	 * </PRE>
	 *   @return void
	 *   @param message
	 */
	public static void println (String message) {
		ComLog.debug (message);
	}
	
}
