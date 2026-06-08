package ru.primer.server.command;

import ru.primer.api.command.CommandHandler;
import ru.primer.api.model.Message;
import ru.primer.server.application.ServerApplication;

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
