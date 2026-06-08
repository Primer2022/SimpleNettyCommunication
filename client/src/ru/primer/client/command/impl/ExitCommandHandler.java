package ru.primer.client.command.impl;

import ru.primer.client.application.ClientApplication;
import ru.primer.client.command.CommandHandler;
import ru.primer.client.model.Message;

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
