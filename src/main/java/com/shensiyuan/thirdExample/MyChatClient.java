package com.shensiyuan.thirdExample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Designed By luf
 *
 * @author luf
 * @date 2019/9/6 14:41
 */
public class MyChatClient {
    public static void main(String[] args) throws InterruptedException, IOException {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try{
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());
//            ChannelFuture channelFuture = bootstrap.connect("localhost",8899).sync();
            Channel channel = bootstrap.connect("localhost",8899).sync().channel();

//            channelFuture.channel().closeFuture().sync();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                channel.writeAndFlush(br.readLine() + "\r\n");
            }

        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
