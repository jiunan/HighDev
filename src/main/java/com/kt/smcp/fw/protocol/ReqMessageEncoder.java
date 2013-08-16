package com.kt.smcp.fw.protocol;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.kt.smcp.fw.util.PropUtil;


public class ReqMessageEncoder<T extends String> extends AbstractMessageEncoder<T> {
    public ReqMessageEncoder() {
    }
    
    @Override
    protected void encodeMessage(IoSession session, T message, IoBuffer buf) {
    	buf.put(message.getBytes(Charset.forName(PropUtil.getPropValue("fw.charSet"))));
    }

    public void dispose() throws Exception {
    }
}
