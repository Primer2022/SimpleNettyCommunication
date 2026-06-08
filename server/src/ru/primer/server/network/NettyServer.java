package ru.primer.server.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import ru.primer.server.util.Logger;

import java.net.InetSocketAddress;

public final class NettyServer {

    private static final int PORT = 8080;
    private EventLoopGroup boss;
    private EventLoopGroup worker;
    private Thread nettyThread;

    public void start() {
        if(nettyThread != null && nettyThread.isAlive()) return;
        nettyThread = new Thread(() -> {
            boss = new NioEventLoopGroup(1);
            worker = new NioEventLoopGroup();

            try {
                ServerBootstrap bootstrap = new ServerBootstrap();

                bootstrap.group(boss, worker)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) {
                                handleConnection(ch);
                            }
                        });

                ChannelFuture f = bootstrap.bind(PORT).sync();
                Logger.info("Server listening on " + PORT + " port");
                Logger.info("Server started");

                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                shutdown();
            }
        });
        nettyThread.start();
    }

    public void shutdown() {
        if(boss == null || worker == null) return;
        boss.shutdownGracefully();
        worker.shutdownGracefully();
        nettyThread.interrupt();
    }

    private void handleConnection(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();

        p.addLast(new LineBasedFrameDecoder(8192));

        p.addLast(new StringDecoder(CharsetUtil.UTF_8));
        p.addLast(new StringEncoder(CharsetUtil.UTF_8));

        handlePackets(p);
    }

    private void handlePackets(ChannelPipeline p) {
        p.addLast(new SimpleChannelInboundHandler<String>() {
            @Override
            public void channelActive(ChannelHandlerContext ctx) {
                InetSocketAddress addr = (InetSocketAddress) ctx.channel().remoteAddress();

                String ip = addr.getAddress().getHostAddress();
                int port = addr.getPort();

                Logger.info("New connection from " + ip + ":" + port);

                ctx.fireChannelActive();
            }

            @Override
            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                String address = ctx.channel().remoteAddress().toString()
                        .replace("/", "");
                Logger.info(address + " > " + msg);
                ctx.writeAndFlush("Ваше сообщение было получено и обработано: " + msg + "\n");
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                ctx.close();
            }
        });
    }
}
