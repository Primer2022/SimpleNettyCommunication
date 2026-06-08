package ru.primer.server.command;

import ru.primer.api.command.CommandHandler;
import ru.primer.api.model.Message;
import ru.primer.api.util.Logger;
import ru.primer.server.application.ServerApplication;

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