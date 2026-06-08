package ru.primer.client.command;

import ru.primer.client.model.Message;

import java.util.Locale;

public abstract class CommandHandler {

    private final String command;

    public CommandHandler(String command) {
        this.command = command;
    }

    public void checkAndHandle(Message message) {
        if(checkCommand(message))
            handle(message);
    }

    protected abstract void handle(Message message);

    public boolean checkCommand(Message message) {
        return message.text()[0].toLowerCase(Locale.ROOT)
                .equals(command.toLowerCase(Locale.ROOT));
    }
}
