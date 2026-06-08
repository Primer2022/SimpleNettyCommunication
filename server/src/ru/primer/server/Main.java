package ru.primer.server;

import ru.primer.server.application.ServerApplication;

public final class Main {
    public static void main(String[] args) {
        ServerApplication server = new ServerApplication();
        server.start();
    }
}