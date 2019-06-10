package com.wj.desiner._09netty.start.marshalling.cc;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter {
    private byte[] request = ("senninha" + System.getProperty("line.separator")).getBytes();

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(msg);
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        for (int i = 0; i < 500; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setAge(i + "year");
            userInfo.setUsername("senninha");
            ctx.write(userInfo);
            ctx.flush();
        }
        System.out.println("-----------------send over-----------------");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("error");
    }
}
