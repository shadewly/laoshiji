package com.lsfrpc.netty.encoder;

import com.lsfrpc.netty.serialization.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
public class RPCDecoder extends ByteToMessageDecoder {
    private static Logger logger = LoggerFactory.getLogger(RPCDecoder.class);
    private Class<?> genericClass;

    public RPCDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        try {
            if (in.readableBytes() < 4) {
                return;
            }
            in.markReaderIndex();
            int dataLength = in.readInt();
            if (dataLength < 0) {
                ctx.close();
            }
            if (in.readableBytes() < dataLength) {
                in.resetReaderIndex();
            }
            byte[] data = new byte[dataLength];
            in.readBytes(data);

            Object obj = SerializationUtil.deserialize(data, genericClass);
            out.add(obj);
        } catch (Throwable e) {
            logger.error("Error when decode " + in);
            e.printStackTrace();
        }
    }
}
