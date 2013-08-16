package com.kt.smcp.fw.exception;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.MessageSource;

import com.kt.smcp.fw.util.MsgUtil;



public class KtException extends RuntimeException {
	protected static String defaultMessageKey = "fail.common.msg";	
	
	protected String message = null;
	protected String messageKey = defaultMessageKey;
	protected Object[] messageParameters = null;
	protected Exception wrappedException = null;
	protected static final String defaultMsg = MsgUtil.getMessage(defaultMessageKey);
	
	public KtException() {
		this(defaultMsg, null, null);
	}

	/**
	 * KtException 생성자 
	 * @param defaultMessage 메세지 지정
	 */
	public KtException(String defaultMessageOrKey) {
		this(defaultMessageOrKey, null, null);
		String str = MsgUtil.getMessage(defaultMessageOrKey);		
		if( !(str == null || str.equals("")) ) {
			message = str;
			messageKey = defaultMessageOrKey;
		}
	}
	/**
	 * BaseException 생성자 
	 * @param wrappedException 발생한 Exception 내포함.
	 */
	public KtException(Throwable wrappedException) {
		this(defaultMsg, null, wrappedException);
	}

	/**
	 * KtException 생성자 
	 * @param defaultMessage 메세지 지정
	 * @param wrappedException 발생한 Exception 내포함.
	 */
	public KtException(String defaultMessage, Throwable wrappedException) {
		this(MsgUtil.getMessage(defaultMessage), null, wrappedException);
		String str = MsgUtil.getMessage(defaultMessage);		
		if( !(str == null || str.equals("")) ) {
			message = str;
			messageKey = defaultMessage;
		}
	}

	/**
	 * KtException 생성자 
	 * @param defaultMessage 메세지 지정(변수지정)
	 * @param messageParameters 치환될 메세지 리스트
	 * @param wrappedException 발생한 Exception 내포함.
	 */
	public KtException(String defaultMessage, Object[] messageParameters, Throwable wrappedException) {
		super(wrappedException);

		String userMessage = defaultMessage;
		if (messageParameters != null) {
			userMessage = MessageFormat.format(defaultMessage, messageParameters);
		}
		this.message = userMessage;

	}

	/**
	 * KtException 생성자 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 */
	public KtException(MessageSource messageSource, String messageKey) {
		this(messageSource, messageKey, null, null, Locale.getDefault(), null);
	}

	/** KtException 생성자 
	 * @param messageSource
	 * @param messageKey
	 * @param messageParameters
	 */
	public KtException(MessageSource messageSource, String messageKey, Object[] messageParameters ) {
		this(messageSource, messageKey, messageParameters, null, Locale.getDefault(), null);
	}

	/**
	 * KtException 생성자 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 */
	public KtException(MessageSource messageSource, String messageKey, Throwable wrappedException) {
		this(messageSource, messageKey, null, null, Locale.getDefault(), wrappedException);
	}
	/**
	 * KtException 생성자 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 * @param locale 국가/언어지정
	 * @param wrappedException 발생한 Exception 내포함.
	 */
	public KtException(MessageSource messageSource, String messageKey, Locale locale, Throwable wrappedException) {
		this(messageSource, messageKey, null, null, locale, wrappedException);
	}
	/**
	 * KtException 생성자 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 * @param messageParameters 치환될 메세지 리스트
	 * @param locale 국가/언어지정
	 * @param wrappedException 발생한 Exception 내포함.
	 */
	public KtException(MessageSource messageSource, String messageKey, Object[] messageParameters, Locale locale,
	        Throwable wrappedException) {
		this(messageSource, messageKey, messageParameters, null, locale, wrappedException);
	}
	/**
	 * KtException 생성자 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 * @param messageParameters 치환될 메세지 리스트
	 * @param wrappedException 발생한 Exception 내포함.
	 */
	public KtException(MessageSource messageSource, String messageKey, Object[] messageParameters,
	        Throwable wrappedException) {
		this(messageSource, messageKey, messageParameters, null, Locale.getDefault(), wrappedException);
	}
	/**
	 * KtException 생성자 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 * @param messageParameters 치환될 메세지 리스트
	 * @param defaultMessage 메세지 지정(변수지정)
	 * @param wrappedException 발생한 Exception 내포함.
	 */
	public KtException(MessageSource messageSource, String messageKey, Object[] messageParameters,
	        String defaultMessage, Throwable wrappedException) {
		this(messageSource, messageKey, messageParameters, defaultMessage, Locale.getDefault(), wrappedException);
	}
	/**
	 * KtException 생성자 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 * @param messageParameters 치환될 메세지 리스트
	 * @param defaultMessage 메세지 지정(변수지정)
	 * @param locale 국가/언어지정
	 * @param wrappedException 발생한 Exception 내포함.
	 */
	public KtException(MessageSource messageSource, String messageKey, Object[] messageParameters,
	        String defaultMessage, Locale locale, Throwable wrappedException) {
		super(wrappedException);

		this.messageKey = messageKey;
		this.messageParameters = messageParameters;
		this.message = messageSource.getMessage(messageKey, messageParameters, defaultMessage, locale);

	}
		
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public Object[] getMessageParameters() {
		return messageParameters;
	}

	public void setMessageParameters(Object[] messageParameters) {
		this.messageParameters = messageParameters;
	}

	public Throwable getWrappedException() {
		return wrappedException;
	}

	public void setWrappedException(Exception wrappedException) {
		this.wrappedException = wrappedException;
	}	

}