package com.kt.smcp.fw.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.demux.MessageEncoder;

public class ResMessageEncoder<T extends String> extends AbstractMessageEncoder<T> {
    public ResMessageEncoder() {
    }

    @Override
    protected void encodeMessage(IoSession session, T message, IoBuffer buf) {
    	buf.put(message.getBytes());
    }

    public void dispose() throws Exception {
    }
}
