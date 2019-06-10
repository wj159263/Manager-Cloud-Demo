package com.wj.desiner._09netty.start.marshalling;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class MyServer {
    private int port;
    private EventLoopGroup bossGroup = null; // (1)
    private EventLoopGroup workerGroup = null;
    private ServerBootstrap serverBootstrap = null;
    public MyServer(int port){
        this.port = port;
        init();
    }
    private void init(){
       bossGroup = new NioEventLoopGroup(); // (1)
       workerGroup = new NioEventLoopGroup();
       serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // (3)
                .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

    }
    public ChannelFuture doAccept(ChannelHandler... ChannelHandlers) throws InterruptedException {
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(MarshallingFactory.buildDecoder());//反序列化
                        ch.pipeline().addLast(MarshallingFactory.buildEncoder());//序列化。两个顺序无关
                        ch.pipeline().addLast(ChannelHandlers);//有该注解@ChannelHandler.Sharable，这么写
                        //ch.pipeline().addLast(new ServerSerializableHandler());//无该注解@Sharable这么写，每次都是new
                    }
                });
        ChannelFuture f = serverBootstrap.bind(port).sync(); // (7)
        return  f;
    }
    public void release(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        MyServer myServer = null;
        ChannelFuture channelFuture = null;
        try {
            myServer = new MyServer(8080);
            channelFuture = myServer.doAccept(new ServerSerializableHandler());
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if( null != channelFuture){
                try {
                    channelFuture.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            myServer.release();
        }
    }
}
