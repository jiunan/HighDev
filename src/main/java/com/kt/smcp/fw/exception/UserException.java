package com.kt.smcp.fw.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;



public class UserException extends KtException {

	protected static String defaultMessageKey = "fail.common.user";	
	
	public UserException() {
		super(defaultMsg, null, null);
	}

	/**
	 * UserException 생성자 
	 * @param defaultMessage 메세지 지정
	 */
	public UserException(String defaultMessage) {
		super(defaultMessage, null, null);
	}
	/**
	 * UserException 생성자 
	 * @param wrappedException 발생한 Exception 내포함.
	 */
	public UserException(Throwable wrappedException) {
		super(defaultMsg, null, wrappedException);
	}
	
	public UserException(MessageSource messageSource, String messageKey ) {
		super(messageSource, messageKey, null, null, Locale.getDefault(), null);
	}	
	
	public UserException(MessageSource messageSource, String messageKey, 
			Object[] messageParameters ) {
		super(messageSource, messageKey, messageParameters, null, Locale.getDefault(), null);
	}		
	
	public UserException(MessageSource messageSource, String messageKey,
			Object[] messageParameters, Throwable wrappedException) {
		super(messageSource, messageKey, messageParameters, null, Locale.getDefault(), wrappedException);
	}

}
