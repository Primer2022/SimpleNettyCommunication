package ru.primer.server.application;

import ru.primer.api.util.Logger;
import ru.primer.server.network.NettyServer;
import ru.primer.server.registrar.CommandRegistrar;
import ru.primer.server.service.CommandHandlerService;
public final class ServerApplication {

    private final CommandHandlerService commandService;
    private final NettyServer nettyServer;

    public ServerApplication() {
        commandService = new CommandHandlerService();
        nettyServer = new NettyServer();

        CommandRegistrar commandRegistrar = new CommandRegistrar(commandService);
        commandRegistrar.register(this);
    }

    public void start() {
        Logger.info("Starting server...");

        commandService.startCommandThread();
        nettyServer.start();
    }

    public void shutdown() {
        Logger.info("Shutdown server...");
        commandService.stopCommandThread();
        nettyServer.shutdown();
    }
}
