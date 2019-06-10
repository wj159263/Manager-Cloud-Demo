package com.wj.desiner._09netty.start;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;

import java.nio.charset.Charset;

public class NettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE,true)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                           // nioSocketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,
                                    //Delimiters.lineDelimiter()[0]));
                            nioSocketChannel.pipeline().addLast(new ClientHandle());
                        }
                    });
            ChannelFuture future = bootstrap.connect("localhost", 8080).sync();
            //future.channel().write("张三".getBytes(Charset.forName("UTF-8")));
            //future.channel().writeAndFlush(Delimiters.lineDelimiter()[0]);
            future.channel().writeAndFlush(Unpooled.copiedBuffer("c->s : hello server$".getBytes()));
            //如果把sleep去掉，则这3条数据会倍当成一条数据发送
            Thread.sleep(1000);
            future.channel().writeAndFlush(Unpooled.copiedBuffer("c->s : 你好!$".getBytes()));
            Thread.sleep(1000);
            future.channel().writeAndFlush(Unpooled.copiedBuffer("c->s : 还在码？$".getBytes()));
            future.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            loopGroup.shutdownGracefully();
        }
    }
}
