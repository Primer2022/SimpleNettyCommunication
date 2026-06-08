package ru.primer.client.command;

import ru.primer.api.command.CommandHandler;
import ru.primer.api.model.Message;
import ru.primer.client.network.NettyClient;
import ru.primer.api.util.Logger;

import java.util.Arrays;

public class MessageCommandHandler extends CommandHandler {

    private final NettyClient nettyClient;

    public MessageCommandHandler(NettyClient nettyClient) {
        super("msg");
        this.nettyClient = nettyClient;
    }

    @Override
    protected void handle(Message message) {
        String[] text = message.text();

        if(text.length < 2) {
            handleHelp();
            return;
        }

        nettyClient.send(String.join(" ",
                Arrays.stream(message.text()).skip(1).toList()));
    }

    private static void handleHelp() {
        Logger.error("Используйте: msg <text>");
    }
}
