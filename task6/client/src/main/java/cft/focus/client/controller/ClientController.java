package cft.focus.client.controller;

import cft.focus.client.model.Client;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClientController {
    private final Client client;

    public void handleClickedOnSendButton(String message) {
        client.sendTextMessage(message);
    }

    public void handleName(String name) {
        client.setName(name);
    }

    public void handleServerAddress(String serverAddress) {
        client.setServerAddress(serverAddress);
    }

    public void handlePort(int port) {
        client.setPort(port);
    }

    public void handleExit() {
        System.exit(0);
    }
}
