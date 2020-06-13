package cft.focus.client;

import cft.focus.client.controller.ClientController;
import cft.focus.client.model.Client;
import cft.focus.client.view.ClientView;
import cft.focus.client.view.SwingClientView;

public class ClientTask {
    public static void main(String[] args) {
        new ClientTask().run();
    }

    private void run() {
        Client client = new Client();
        ClientController clientController = new ClientController(client);
        ClientView clientView = new SwingClientView(clientController);

        client.attachView(clientView);
        client.run();
    }
}
