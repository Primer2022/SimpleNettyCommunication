package ru.primer.client.service;

import ru.primer.client.command.CommandHandler;
import ru.primer.client.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class CommandHandlerService {

    private final List<CommandHandler> handlers = new ArrayList<>();
    private Thread commandThread;

    public void register(CommandHandler commandHandler) {
        handlers.add(commandHandler);
    }

    public void startCommandThread() {
        if(commandThread != null && commandThread.isAlive()) return;
        commandThread = new Thread(() -> {
            while (commandThread.isAlive()) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNext()) handleMessage(scanner);
            }
        });
        commandThread.start();
    }

    public void stopCommandThread() {
        commandThread.interrupt();
    }

    private void handleMessage(Scanner scanner) {
        String text = scanner.nextLine();
        String[] split = text.split(" ");
        long date = System.currentTimeMillis();
        Message message = new Message(split, date);
        handlers.forEach(handler -> handler.checkAndHandle(message));
    }
}
