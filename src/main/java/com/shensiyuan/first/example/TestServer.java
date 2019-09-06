package com.shensiyuan.first.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Designed By luf
 *
 * @author luf
 * @date 2019/9/5 13:48
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {
        //死循环 不断接受客户端发起的请求
        //boss 不断接受连接
        EventLoopGroup bossGroup= new NioEventLoopGroup();
        //worker1  不断接受连接 netty 推荐 2个 线程组 完成服务器端
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{


            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new TestServerInitialize());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }



    }
}
