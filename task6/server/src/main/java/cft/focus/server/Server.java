package cft.focus.server;

import cft.focus.common.Connection;
import cft.focus.common.Message;
import cft.focus.common.MessageType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Основной класс сервера.
 */
@Slf4j
class Server {
    private final int port;
    private static final Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    Server(int port) {
        this.port = port;
    }

    void run() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Сервер успешно запущен");
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }
    }

    /**
     * Отправка сообщения всем клиентам
     *
     * @param message - сообщение
     */
    private void sendBroadcastMessage(Message message) {
        try {
            for (Connection connection : connectionMap.values()) {
                connection.send(message);
            }
        } catch (IOException e) {
            log.error("Не удалось отправить сообщение всем клиентам", e);
        }
    }

    /**
     * Класс - обработчик, отвечающий за поток, который устанавливает сокетное соединение и
     * в котором происходит обмен сообщениями с клиентом
     */
    @AllArgsConstructor
    private class Handler extends Thread {
        private final Socket socket;

        @Override
        public void run() {
            log.info("Установлено новое соединение с удаленным адресом: " +
                    socket.getRemoteSocketAddress().toString());

            String userName = null;

            try (Connection connection = new Connection(socket)) {
                userName = acquaintance(connection);
                sendBroadcastMessage(new Message(MessageType.TEXT, "Пользователь " + userName + " зашел в чат"));
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);

                log.info("Пользователь " + userName + " зашел в чат");

                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                log.error("Ошибка при обмене данными с удаленным адресом", e);
            }

            if (userName != null) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.TEXT, "Пользователь " + userName + " покинул чат"));
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));

                log.info("Пользователь " + userName + " покинул чат");
            }
            log.info("Соединение с удаленным адресом закрыто");
        }

        /**
         * Знакомство нового клиента и сервера
         *
         * @param connection - Соединение
         * @return - Имя нового клиента
         */
        private String acquaintance(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message name = connection.receive();

                if ((name.getType().equals(MessageType.USER_NAME)) &&
                        (!name.getData().isEmpty()) &&
                        (!connectionMap.containsKey(name.getData()))) {

                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    connectionMap.put(name.getData(), connection);
                    return name.getData();
                } else if (connectionMap.containsKey(name.getData())) {
                    connection.send(new Message(MessageType.NAME_USED));
                }
            }
        }

        /**
         * Отправляет зашедшему пользователю список всех участников чата
         *
         * @param connection - Соединение
         * @param userName   - Имя пользователя, которому отправляем список участников
         */
        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> connectionEntry : connectionMap.entrySet()) {
                String name = connectionEntry.getKey();
                Message message = new Message(MessageType.USER_ADDED, name);

                if (!name.equals(userName)) {
                    connection.send(message);
                }
            }
        }

        /**
         * Главный цикл обработки сообщений сервером
         *
         * @param connection - Соединение
         * @param userName   - Имя пользователя
         */
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType().equals(MessageType.TEXT)) {
                    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                            Calendar.getInstance().getTime());
                    String stringWithMessage = timeStamp +
                            " [" +
                            userName +
                            "]: " +
                            message.getData();
                    Message formattedMessage = new Message(message.getType(), stringWithMessage);
                    sendBroadcastMessage(formattedMessage);
                } else {
                    log.info("Непредвиденный тип сообщения");
                }
            }
        }
    }
}

