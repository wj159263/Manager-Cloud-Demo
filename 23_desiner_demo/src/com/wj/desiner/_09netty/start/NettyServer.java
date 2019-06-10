package com.wj.desiner._09netty.start;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * netty服务器
 */
//@ChannelHandler.Sharable
public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup parentGroup = new NioEventLoopGroup(1);//1是线程数,处理服务器接收客户端连接
        EventLoopGroup chidGroup = new NioEventLoopGroup();//接收后，处理网络通信
        try {
        ServerBootstrap serverBootstrap = new ServerBootstrap();//服务器通道配置类
        serverBootstrap.group(parentGroup,chidGroup); //绑定2个线程组
        serverBootstrap.channel(NioServerSocketChannel.class);//通道设置，指定Nio模式
        ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                //socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,
                 //       Delimiters.lineDelimiter()[0]));

                //每5个长度则截取。处理粘包拆包
                //socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(5));
                //定义$为分割符。处理粘包拆包。Unpooled工具类将其转为ByteBuf。所有的数据要结束时末尾必须加上$
                ByteBuf endBuf = Unpooled.copiedBuffer("$".getBytes());
                //参数一1024表示特殊分隔符$的大小，1024足够了。参数二:特殊分隔符为endBuf
                socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,endBuf));


                //socketChannel.pipeline().addLast(new StringDecoder());//设置字符串形式的解码.将ByteBuf转字符串.可注释掉
                //由于上面配置了StringDecoder，所以SimpleHandler的channelRead的msg由原来的ByteBuf转字符串了
                socketChannel.pipeline().addLast(new SimpleHandler());
            }
        };
        serverBootstrap.childOption(ChannelOption.SO_BACKLOG,1024) //设置tcp缓冲区
               // .childOption(ChannelOption.SO_SNDBUF,1024)//设置发送缓冲大小
                        .childOption(ChannelOption.SO_KEEPALIVE,true)//保持连接
                        .childHandler(channelInitializer);

            ChannelFuture future = serverBootstrap.bind(8080).sync();//端口绑定


            future.channel().closeFuture().sync();//阻塞等待关闭.监听，当服务器和客户端连接断开时，sync同步关闭closeFuture
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            parentGroup.shutdownGracefully();
            chidGroup.shutdownGracefully();
        }

    }
}
