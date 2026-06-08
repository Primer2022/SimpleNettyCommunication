package ru.primer.client.registrar;

import ru.primer.client.application.ClientApplication;
import ru.primer.client.command.impl.ConnectCommandHandler;
import ru.primer.client.command.impl.ExitCommandHandler;
import ru.primer.client.command.impl.HelpCommandHandler;
import ru.primer.client.command.impl.MessageCommandHandler;
import ru.primer.client.network.NettyClient;
import ru.primer.client.service.CommandHandlerService;

public final class CommandRegistrar {

    private final CommandHandlerService commandService;

    public CommandRegistrar(CommandHandlerService commandService) {
        this.commandService = commandService;
    }

    public void register(ClientApplication client, NettyClient nettyClient) {
        ExitCommandHandler exitHandler = new ExitCommandHandler(client);
        commandService.register(exitHandler);

        HelpCommandHandler helpHandler = new HelpCommandHandler(client);
        commandService.register(helpHandler);

        ConnectCommandHandler connectHandler = new ConnectCommandHandler(nettyClient);
        commandService.register(connectHandler);

        MessageCommandHandler messageHandler = new MessageCommandHandler(nettyClient);
        commandService.register(messageHandler);
    }
}
