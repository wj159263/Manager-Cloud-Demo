package com.wj.desiner._09netty.start.marshalling.dd;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter {
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
    throws Exception { 
   cause.printStackTrace(); 
   ctx.close(); 
  } 
  
  public void channelRead(ChannelHandlerContext ctx, Object msg)
    throws Exception { 
   Receive receive = (Receive) msg; 
   System.out.println("server反馈："+receive); 
  } 
}