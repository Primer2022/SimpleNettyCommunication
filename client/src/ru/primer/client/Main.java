package ru.primer.client;

import ru.primer.client.application.ClientApplication;

public final class Main {
    public static void main(String[] args) {
        ClientApplication client = new ClientApplication();
        client.start();
    }
}