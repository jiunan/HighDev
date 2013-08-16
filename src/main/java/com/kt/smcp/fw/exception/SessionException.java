package com.kt.smcp.fw.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;


/**
 * <pre>
 *
 * Session 에서 발생하는 Exception을 정의할 클래스
 *
 * </pre>
 */
public class SessionException extends KtException {
	
	protected static String defaultMessageKey = "fail.common.session";	
	/**
	 * <pre>
	 * SessionException 생성자이다.
	 * 
	 * </pre> 
	 */
	public SessionException() {
		super(defaultMsg);
	}
	
	/**
	 * <pre>
	 * SessionException 생성자이다.
	 * </pre> 
	 * @param message 오류 메시지
	 */
	public SessionException(String message) {
		super(message);
	}
	
	/**
	 * <pre>
	 * SessionException 생성자이다.
	 * </pre> 
	 * @param cause 발생한 오류 객체 
	 */
	public SessionException(Throwable cause) {
		super(defaultMsg, cause);
	}
	
	/**
	 * <pre>
	 * SessionException 생성자이다.
	 * </pre> 
	 * @param message 오류 메시지
	 * @param cause 발생한 오류 객체 
	 */
	public SessionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SessionException(MessageSource messageSource, String messageKey ) {
		super(messageSource, messageKey, null, null, Locale.getDefault(), null);
	}	
	
	public SessionException(MessageSource messageSource, String messageKey, Object[] messageParameters ) {
		super(messageSource, messageKey, messageParameters, null, Locale.getDefault(), null);
	}		
	
	public SessionException(MessageSource messageSource, String messageKey, Object[] messageParameters, Throwable wrappedException) {
		super(messageSource, messageKey, messageParameters, null, Locale.getDefault(), wrappedException);
	}
	
}
