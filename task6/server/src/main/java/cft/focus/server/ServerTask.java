package cft.focus.server;

import cft.focus.server.exceptions.ServerParametersException;
import cft.focus.server.parameters.ServerParameters;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ServerTask {
    public static void main(String[] args) {
        new ServerTask().run();
    }

    private void run() {
        try {
            ServerParameters serverParameters = new ServerParameters();
            Server server = new Server(serverParameters.getPort());
            server.run();
        } catch (ServerParametersException | IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}