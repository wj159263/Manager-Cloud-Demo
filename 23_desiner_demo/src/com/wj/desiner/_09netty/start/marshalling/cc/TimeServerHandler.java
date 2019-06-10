package com.wj.desiner._09netty.start.marshalling.cc;

import java.nio.ByteBuffer;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // TODO Auto-generated method stub
//      ByteBuf in = (ByteBuf) msg;
        try {
            System.out.println(msg);
            String remsg = new String("has receive");
            ctx.write(remsg);
            ctx.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        ctx.flush();
    }
}
