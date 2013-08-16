package com.kt.smcp.fw.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReqMessageDecoder extends AbstractMessageDecoder {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReqMessageDecoder.class);

    public ReqMessageDecoder() {
    }

    @Override
    protected String decodeMessage(IoSession session, IoBuffer in) {
        
    	String headerStr = "";
    	String bodyStr = "";
    	LOGGER.info("ReqMessageDecoder decodeMessage in.remaining = " + in.remaining());
        
    	if(super.readHeader){
	    	byte[] headerByte = new byte[227];
	        in.get(headerByte);
	        headerStr = new String(headerByte);
	        bodyLength = Integer.parseInt(headerStr.substring(217, 227));
	        LOGGER.info("ReqMessageDecoder decodeMessage headerMsg = " + headerStr);
	    	super.totalLength += bodyLength;
	    	
	    	super.readHeader = false;
    	}
    	
    	if(in.hasRemaining()){
	    	byte[] bodyByte = new byte[in.remaining()];
	    	in.get(bodyByte);
	    	bodyStr = new String(bodyByte);
    	}
        
        return headerStr + bodyStr;
        
    }

    public void finishDecode(IoSession session, ProtocolDecoderOutput out)
            throws Exception {
    }
}
