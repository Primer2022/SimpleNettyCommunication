package ru.primer.server.command.impl;

import ru.primer.server.application.ServerApplication;
import ru.primer.server.command.CommandHandler;
import ru.primer.server.model.Message;

public final class ExitCommandHandler extends CommandHandler {

    ServerApplication server;

    public ExitCommandHandler(ServerApplication server) {
        super("exit");
        this.server = server;
    }

    @Override
    protected void handle(Message message) {
        server.shutdown();
        System.exit(0);
    }
}
