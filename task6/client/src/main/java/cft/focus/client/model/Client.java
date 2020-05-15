package cft.focus.client.model;

import cft.focus.client.view.ClientView;
import cft.focus.client.view.NotificationMessagePane;
import cft.focus.common.Connection;
import cft.focus.common.Message;
import cft.focus.common.MessageType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Slf4j
public class Client {
    private Set<String> allUserNames = new HashSet<>();
    private String name;
    private String serverAddress;
    private int port;
    private Connection connection;
    private NotificationMessagePane notificationMessagePane = new NotificationMessagePane();
    private final ClientViewNotifier viewNotifier = new ClientViewNotifier();

    public void attachView(ClientView clientView) {
        viewNotifier.attachView(clientView);
    }

    public void run() {
        new SocketThread().start();
    }

    /**
     * Отправка сообщения
     *
     * @param text Текст сообщения
     */
    public void sendTextMessage(String text) {
        try {
            if (!text.isEmpty()) {
                Message message = new Message(MessageType.TEXT, text);
                connection.send(message);
            }
        } catch (IOException e) {
            log.info("Невозможно отправить сообщение");
        }
    }

    /**
     * Запросить адрес сервера
     */
    private void requestServerAddress() {
        viewNotifier.notifyViewsAboutServerAddressRequest();
    }

    /**
     * Запросить порт
     */
    private void requestPort() {
        viewNotifier.notifyViewsAboutPortRequest();
    }

    /**
     * Запросить имя пользователя
     */
    private void requestName() {
        viewNotifier.notifyViewsAboutNameRequest();
    }


    /**
     * Класс, отвечающий за поток, устанавливающий сокетное соединение и
     * читающий сообщения сервера
     */
    private class SocketThread extends Thread {
        @Override
        public void run() {
            Socket socket;
            requestServerAddress();
            requestPort();

            try {
                socket = new Socket(serverAddress, port);
                Client.this.connection = new Connection(socket);
                acquaintance();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                log.error(e.getMessage(), e);
            }
        }

        /**
         * Знакомство клиента и сервера
         */
        private void acquaintance() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    requestName();
                    connection.send(new Message(MessageType.USER_NAME, name));
                } else if (message.getType() == MessageType.NAME_USED) {
                    notificationMessagePane.sayNameIsBusy();
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    return;
                } else {
                    throw new IOException("Непредвиденный тип сообщения");
                }
            }
        }

        /**
         * Главный цикл обработки сообщений клиентом
         */
        private void clientMainLoop() throws IOException, ClassNotFoundException {
            boolean isYourName = true;
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    viewNotifier.notifyViewsAboutNewMessage(message.getData());
                } else if (message.getType() == MessageType.USER_ADDED) {
                    if (isYourName) {
                        allUserNames.add(message.getData() + " (это ты)");
                        isYourName = false;
                    } else {
                        allUserNames.add(message.getData());
                    }
                    viewNotifier.notifyViewsAboutUsersUpdate(allUserNames);
                } else if (message.getType() == MessageType.USER_REMOVED) {
                    allUserNames.remove(message.getData());
                    viewNotifier.notifyViewsAboutUsersUpdate(allUserNames);
                } else {
                    throw new IOException("Непредвиденный тип сообщения");
                }
            }
        }
    }
}
