package com.wj.desiner._09netty.start.marshalling;

import com.wj.desiner._09netty.start.ClientHandle;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Random;

public class MyClient {
    NioEventLoopGroup loopGroup = null;
    Bootstrap bootstrap = null;

    public MyClient() {
        init();
    }

    private void init() {
        loopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(loopGroup);
        bootstrap.channel(NioSocketChannel.class);
    }
    public ChannelFuture doRequest(String host, int port, ChannelHandler... channelHandlers) throws InterruptedException {
        bootstrap.option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(MarshallingFactory.buildDecoder());//反序列化
                        ch.pipeline().addLast(MarshallingFactory.buildEncoder());//序列化。两个顺序无关
                        ch.pipeline().addLast(channelHandlers);//有该注解@ChannelHandler.Sharable，这么写
                    }
                });
        ChannelFuture future = bootstrap.connect(host, port).sync();
        return future;
    }
    public void release(){
        loopGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        System.out.println("客户端启动-------------");
        MyClient myClient = null;
        ChannelFuture future = null;
        try {
            myClient = new MyClient();
            future = myClient.doRequest("127.0.0.1", 8080, new ClientSerializableHandler());
            RequestMessage message = new RequestMessage(new Random().nextInt(500), "这是", new byte[0]);

            future.channel().writeAndFlush(Unpooled.copiedBuffer("c->s : hello server$".getBytes()));
            future.channel().writeAndFlush(message);
            Thread.sleep(100);
            //future.addListener(ChannelFutureListener.CLOSE);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("finally");
            if(future != null) {
                try {

                    future.channel().closeFuture().sync();
                    System.out.println("关闭closeFuture");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            myClient.release();
            System.out.println("关闭myClient");
        }

    }
}
