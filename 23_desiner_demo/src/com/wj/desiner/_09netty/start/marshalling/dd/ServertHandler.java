package com.wj.desiner._09netty.start.marshalling.dd;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServertHandler extends ChannelHandlerAdapter {
  
 @Override
 public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
   throws Exception { 
  cause.printStackTrace(); 
 } 
  
 public void channelRead(ChannelHandlerContext ctx, Object msg)
   throws Exception { 
  Send send = (Send) msg; 
  System.out.println("client发送："+send); 
    
  Receive receive = new Receive(); 
  receive.setId(send.getId()); 
  receive.setMessage(send.getMessage()); 
  receive.setName(send.getName()); 
  ctx.writeAndFlush(receive); 
 } 
   
}