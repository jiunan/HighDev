package com.kt.smcp.fw.protocol;

import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * A {@link ProtocolCodecFactory} that provides a protocol codec for
 * SumUp protocol.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class CustomProtocolCodecFactory extends DemuxingProtocolCodecFactory {

    public CustomProtocolCodecFactory(boolean server) {
        if (server) {
            super.addMessageDecoder(ReqMessageDecoder.class);
            super.addMessageEncoder(String.class, ResMessageEncoder.class); 
            
        }else{
        	super.addMessageEncoder(String.class, ReqMessageEncoder.class);
            super.addMessageDecoder(ResMessageDecoder.class);
        	
        }
    }
}
