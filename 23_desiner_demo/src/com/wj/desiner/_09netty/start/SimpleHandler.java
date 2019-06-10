package com.wj.desiner._09netty.start;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * 服务器回调处理类
 */
public class SimpleHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("11111111==============");
        if(msg instanceof ByteBuf){
            ByteBuf buf = (ByteBuf) msg;
            System.out.println("1111222==============");
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String body = new String(req, "utf-8");
            System.out.println("服务器接收数据:="+body);
            buf.release();


            //服务器响应客户端
            String response = "hi client加哦加哦加哦$";
            //Unpooled工具包装发送的数据成ByteBuf
            //服务器端有写操作，则会释放msg。只读的话需要像客户端一样手动释放msg
            ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
                    //.addListener(ChannelFutureListener.CLOSE);//每次发送完数据就关闭长连接
            //客户端发送多条数据时，服务器只收到数据并响应然后就断开连接，客户端也关闭

        }
        if(msg instanceof String){
            String msg1 = (String) msg;
            System.out.println("服务器接收字符串数据:="+msg1);
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("222222222==============");
        //ctx.close();
        super.userEventTriggered(ctx, evt);
    }
}
