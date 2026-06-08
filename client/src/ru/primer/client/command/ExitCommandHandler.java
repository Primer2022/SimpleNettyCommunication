package ru.primer.client.command;

import ru.primer.api.command.CommandHandler;
import ru.primer.api.model.Message;
import ru.primer.client.application.ClientApplication;

public final class ExitCommandHandler extends CommandHandler {

    ClientApplication client;

    public ExitCommandHandler(ClientApplication client) {
        super("exit");
        this.client = client;
    }

    @Override
    protected void handle(Message message) {
        client.shutdown();
        System.exit(0);
    }
}
