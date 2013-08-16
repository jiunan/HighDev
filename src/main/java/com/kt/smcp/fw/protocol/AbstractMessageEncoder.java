package com.kt.smcp.fw.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

public abstract class AbstractMessageEncoder<T extends String> implements MessageEncoder<T> {

    protected AbstractMessageEncoder() {}

    public void encode(IoSession session, T message, ProtocolEncoderOutput out) throws Exception {
        IoBuffer buf = IoBuffer.allocate(10);
        buf.setAutoExpand(true);

        encodeMessage(session, message, buf);
        
        buf.flip();
        
        out.write(buf);
    }

    protected abstract void encodeMessage(IoSession session, T message, IoBuffer out);
}
