package com.wj.desiner._09netty.start.marshalling;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 注解作用:代表当前Handler时一个可分享的。如果不使用该注解，则要这么写
 * ch.pipeline().addLast(new ServerSerializableHandler());每次都是new新的一个Handler对象,而不是
 * ch.pipeline().addLast(ChannelHandlers);
 */
@ChannelHandler.Sharable
public class ServerSerializableHandler extends ChannelInboundHandlerAdapter {
    //有@Sharable注解最好不要定义共享变量，因为ServerSerializableHandler对象时共享的


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("---服务器读取客户端数据的类：");
        System.out.println(msg.toString()+"---服务器读取客户端数据的类：" + msg.getClass().getName());
        if(msg instanceof RequestMessage){
            RequestMessage message = (RequestMessage) msg;
            System.out.println("是RequestMessage");
        }
        ResponseMessage responseMessage = new ResponseMessage(1L,"服务器给客户端的数据");
        ctx.channel().writeAndFlush(responseMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("dddddddddddd");
        super.channelActive(ctx);
    }
}
