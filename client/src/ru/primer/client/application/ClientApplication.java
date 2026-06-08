package ru.primer.client.application;

import ru.primer.client.network.NettyClient;
import ru.primer.client.registrar.CommandRegistrar;
import ru.primer.client.service.CommandHandlerService;
import ru.primer.client.util.Logger;

public final class ClientApplication {

    private final CommandHandlerService commandService;
    private final NettyClient nettyClient;

    public ClientApplication() {
        commandService = new CommandHandlerService();
        nettyClient = new NettyClient();

        CommandRegistrar commandRegistrar = new CommandRegistrar(commandService);
        commandRegistrar.register(this, nettyClient);
    }

    public void start() {
        commandService.startCommandThread();
        Logger.info("Введите команду connect <host>:<port> сервера для подключения");
    }

    public void shutdown() {
        commandService.stopCommandThread();
        nettyClient.shutdown();
    }
}
