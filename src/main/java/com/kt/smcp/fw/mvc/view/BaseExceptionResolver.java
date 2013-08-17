package com.kt.smcp.fw.mvc.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class BaseExceptionResolver implements HandlerExceptionResolver{
	private static final Logger log = LoggerFactory.getLogger( BaseExceptionResolver.class );
    private String view = null;
    
    public void setView(String view){
        this.view = view;
    }
 
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception) {    	
    	log.debug("################# Error ######################");
    	log.debug(exception.toString());
    	exception.printStackTrace();
    	return new ModelAndView(view , "CommVO", null);    	        
    }
}