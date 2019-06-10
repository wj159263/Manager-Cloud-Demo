package com.wj.desiner._09netty.start;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutorGroup;

public class ClientHandle extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            System.out.println("客户端==============");
            if (msg instanceof ByteBuf) {
                ByteBuf buf = (ByteBuf) msg;
                System.out.println("1111222==============");
                byte[] req = new byte[buf.readableBytes()];
                buf.readBytes(req);
                String body = new String(req, "utf-8");
                System.out.println("客户端接收数据:=" + body);
                StringBuilder stringBuilder = new StringBuilder();
               /* while (buf.isReadable()){
                    stringBuilder.append((char)buf.readByte());
                }*/
               // ctx.writeAndFlush("z这是客户端发送的请求".getBytes());
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //ctx.writeAndFlush(Unpooled.copiedBuffer("z这是客户端发送的请求".getBytes()));
        //ctx.writeAndFlush(1522L);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("222222222==============");
        //ctx.close();
        super.userEventTriggered(ctx, evt);
    }
}
