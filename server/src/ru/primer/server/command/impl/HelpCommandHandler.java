package ru.primer.server.command.impl;

import ru.primer.server.application.ServerApplication;
import ru.primer.server.command.CommandHandler;
import ru.primer.server.model.Message;
import ru.primer.server.util.Logger;

public final class HelpCommandHandler extends CommandHandler {

    ServerApplication server;

    public HelpCommandHandler(ServerApplication server) {
        super("help");
        this.server = server;
    }

    @Override
    protected void handle(Message message) {
        Logger.info("Список команд:");
        Logger.info("help - список команд");
        Logger.info("exit - остановить сервер");
    }
}