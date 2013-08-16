package com.kt.smcp.fw.protocol;

import java.io.UnsupportedEncodingException;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;

public class ResMessageDecoder extends AbstractMessageDecoder {

    public ResMessageDecoder() {
    }

    @Override
    protected String decodeMessage(IoSession session, IoBuffer in) {
    	
    	String resStr = "";

    	if(in.hasRemaining()){
	    	byte[] resByte = new byte[in.remaining()];
	    	resStr = new String(resByte);
	    	in.get(resByte);
    	}
        
        return resStr;
        
    }

    public void finishDecode(IoSession session, ProtocolDecoderOutput out)
            throws Exception {
    }

}
