package ru.primer.client.command.impl;

import ru.primer.client.command.CommandHandler;
import ru.primer.client.model.Message;
import ru.primer.client.network.NettyClient;
import ru.primer.client.util.Logger;

public class ConnectCommandHandler extends CommandHandler {

    private final NettyClient nettyClient;

    public ConnectCommandHandler(NettyClient nettyClient) {
        super("connect");
        this.nettyClient = nettyClient;
    }

    @Override
    protected void handle(Message message) {
        String[] text = message.text();

        if(text.length < 2) {
            handleHelp();
            return;
        }

        String connectionAddress = message.text()[1];
        String[] splitAddress = connectionAddress.split(":");
        String host = splitAddress[0];
        int port = Integer.parseInt(splitAddress[1]);

        nettyClient.connect(host, port);
    }

    private static void handleHelp() {
        Logger.error("Используйте: connect <host>:<port>");
    }
}
