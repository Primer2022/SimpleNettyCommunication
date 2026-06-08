package ru.primer.server.registrar;

import ru.primer.server.application.ServerApplication;
import ru.primer.server.command.ExitCommandHandler;
import ru.primer.server.command.HelpCommandHandler;
import ru.primer.server.service.CommandHandlerService;

public final class CommandRegistrar {

    private final CommandHandlerService commandService;

    public CommandRegistrar(CommandHandlerService commandService) {
        this.commandService = commandService;
    }

    public void register(ServerApplication server) {
        ExitCommandHandler exitHandler = new ExitCommandHandler(server);
        commandService.register(exitHandler);

        HelpCommandHandler helpHandler = new HelpCommandHandler(server);
        commandService.register(helpHandler);
    }
}
