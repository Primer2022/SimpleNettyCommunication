package ru.primer.client.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import ru.primer.client.model.Message;
import ru.primer.client.util.Logger;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public final class NettyClient {

    private final EventLoopGroup group = new NioEventLoopGroup();
    private Bootstrap bootstrap;
    private volatile Channel channel;

    public NettyClient() {
        init();
    }

    private void init() {
        bootstrap = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {

                        ch.pipeline().addLast(new IdleStateHandler(0, 10, 0));

                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new StringEncoder());

                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
    }

    public void connect(String host, int port) {
        channel = bootstrap.connect(host, port).addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                Logger.info("Подключение к " + host + ":" + port + " установлено!");
            } else {
                Logger.warn("Не удалось подключиться! Попытка переподключения через 3 секунды...");
                future.channel().eventLoop()
                        .schedule(() -> connect(host, port), 3, TimeUnit.SECONDS);
            }
        }).channel();
    }

    public void send(String message) {
        if (channel == null || !channel.isActive()) return;

        channel.writeAndFlush(message + "\n");
    }

    public void shutdown() {
        group.shutdownGracefully();
    }
}