package com.wj.desiner._09netty.start.marshalling.cc;

import java.net.UnknownHostException;

import com.wj.desiner._09netty.start.marshalling.dd.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

public class NettyClientMarshalling {

    
    private void bind(int port,String host){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
        .option(ChannelOption.TCP_NODELAY, true).handler(new ClientHandlerInit());
        
        try {
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            group.shutdownGracefully();
        }
        
        
        
    }
    
    private class ClientHandlerInit extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            // TODO Auto-generated method stub
            //添加对marshalling框架的支持
            MarshallingEncoder encoder = MarshallingCodeFactory.getEncoder();
            MarshallingDecoder decoder = MarshallingCodeFactory.getDecoder();
            ch.pipeline().addLast(encoder);
            ch.pipeline().addLast(decoder);
            ch.pipeline().addLast(new ClientHandler());
        }
        
    }

    public static void main(String[] args) throws UnknownHostException {
        // TODO Auto-generated method stub
        NettyClientMarshalling client = new NettyClientMarshalling();
        client.bind(12580,"localhost");
    }

}