package ru.primer.client.command.impl;

import ru.primer.client.application.ClientApplication;
import ru.primer.client.command.CommandHandler;
import ru.primer.client.model.Message;
import ru.primer.client.util.Logger;

public final class HelpCommandHandler extends CommandHandler {

    ClientApplication client;

    public HelpCommandHandler(ClientApplication client) {
        super("help");
        this.client = client;
    }

    @Override
    protected void handle(Message message) {
        Logger.info("Список команд:");
        Logger.info("connect <host>:<port> - подключиться к серверу");
        Logger.info("msg <text> - отправить сообщение серверу");
        Logger.info("help - список команд");
        Logger.info("exit - отключиться");
    }
}