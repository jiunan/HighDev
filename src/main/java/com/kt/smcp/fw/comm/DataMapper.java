/**
 * <PRE>
 *  Project : AdminUI
 *  Package : com.kt.smcp.fw.comm
 * </PRE>
 * @file   : DataMapper.java
 * @date   : 2013. 7. 12. 오전 10:48:10
 * @author : 관제개발TF
 * @brief  :
 *  변경이력    : 
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        관제개발TF  : 2013. 7. 12.       :            : 신규 개발.
 */
package com.kt.smcp.fw.comm;

import java.util.HashMap;

import com.kt.smcp.fw.exception.KtException;
import com.kt.smcp.fw.util.MsgUtil;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * <PRE>
 *  ClassName : DataMapper 
 * </PRE>
 * @version : 1.0
 * @date    : 2013. 7. 12. 오전 10:48:10
 * @author  : 관제개발TF
 * @brief   : 
 */

public class DataMapper {
	private DataMapper() {}
	
	
	/**
	 * <pre>
	 * Header에 해당하는 JSONObject를 HashMap<String key, String value>의 형태로 변환한다.
	 * </pre>
	 * @param jroot Header에 해당하는 JSONObject
	 * @return HashMap<String, String> Header에 해당하는 HashMap 
	 */
	public static HashMap<String, String> toHashMap(JSONObject jroot) {
		HashMap<String, String> header = new HashMap<String, String>();
		String key = null;
		Object valueObj = null;
		for( Object keyObj : jroot.keySet() ) {
			key = String.valueOf(keyObj);
			valueObj = jroot.get(key);
			if( valueObj != null ) {
				if( valueObj instanceof JSONArray || valueObj instanceof JSONObject ) {
					throw new KtException(MsgUtil.getMessage("E00000"));
				}
				header.put(key, String.valueOf(valueObj));
			} else {
				header.put(key, null);
			}
		}
		return header;
	}
	
	/**
	 * <pre>
	 * 모바일 플랫폼의 DTO를 표현한 JSON 객체에서 시스템 헤더를 변환한다.
	 * </pre>
	 * @param jroot 모바일 플랫폼의 DTO를 표현한 JSON 객체
	 * @return HashMap<String, String> 시스템 헤더에 해당하는 HashMap
	 */
	public static HashMap<String, String> extractSystemHeader(JSONObject jroot) {
		return toHashMap((JSONObject)jroot.get("_H"));
	}
	
	/**
	 * <pre>
	 * 모바일 플랫폼의 DTO를 표현한 JSON 객체에서 사용자 헤더를 변환한다.
	 * </pre>
	 * @param jroot 모바일 플랫폼의 DTO를 표현한 JSON 객체
	 * @return HashMap<String, String> 사용자 헤더에 해당하는 HashMap
	 */
	public static HashMap<String, String> extractUserHeader(JSONObject jroot) {
		return toHashMap((JSONObject)jroot.get("_U"));
	}
	
	/**
	 * <pre>
	 * HashMap<String, String>를 JSONObject로 변환한다.
	 * </pre>
	 *  
	 * @param map HashMap<String, String> Header 데이터
	 * @return JSONObject 변환된 Header 데이터
	 * 
	 */
	public static JSONObject toJSON(HashMap<String, String> map){
		JSONObject jObject = new JSONObject();
		for(String key:map.keySet()) {
			if(map.get(key) != null) {
				jObject.put(key, map.get(key));
			} else {
				jObject.put(key, null);
			}
		}
		return jObject;
	}
}
