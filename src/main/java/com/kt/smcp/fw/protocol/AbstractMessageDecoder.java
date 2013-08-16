package com.kt.smcp.fw.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

public abstract class AbstractMessageDecoder implements MessageDecoder {

    public boolean readHeader = true;
    public String rcvStr = "";
    public int bodyLength = 0;
    public int totalLength = 227;
    
    protected AbstractMessageDecoder() {
    	
    }
    
    @Override
    public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
    	
        return MessageDecoderResult.OK;
    }
    
    @Override
    public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
    	rcvStr += decodeMessage(session, in);
                
        if (rcvStr.getBytes().length < totalLength ) {
            return MessageDecoderResult.NEED_DATA;
        }
        
        out.write(rcvStr);

        return MessageDecoderResult.OK;
    }

    /**
     * @return <tt>null</tt> if the whole body is not read yet
     */
    protected abstract String decodeMessage(IoSession session, IoBuffer in);
}
