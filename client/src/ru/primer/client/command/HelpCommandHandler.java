package ru.primer.client.command;

import ru.primer.api.command.CommandHandler;
import ru.primer.api.model.Message;
import ru.primer.client.application.ClientApplication;
import ru.primer.api.util.Logger;

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