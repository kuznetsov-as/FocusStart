package cft.focus.client.view;

import cft.focus.client.controller.ClientController;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

@Slf4j
public class SwingClientView extends JFrame implements ClientView {
    private final ClientController clientController;
    private final InterrogativeMessagePane interrogativeMessagePane = new InterrogativeMessagePane();

    private JTextField textField;
    private JTextArea messages;
    private JTextArea users;

    public SwingClientView(ClientController clientController) {
        super("Чат");
        this.clientController = clientController;

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (interrogativeMessagePane.askForExit() == 0) {
                    clientController.handleExit();
                }
            }
        });

        setResizable(false);
        setLayout(new GridBagLayout());

        initializationView();
        setLocationRelativeTo(null);
    }

    /**
     * Инициализация компонентов отображения
     */
    private void initializationView() {
        textField = new JTextField(50);
        messages = new JTextArea(10, 50);
        users = new JTextArea(10, 9);

        JButton sendButton = new JButton("Отправить");
        sendButton.addActionListener(e -> {
            clientController.handleClickedOnSendButton(textField.getText());
            textField.setText("");
        });

        textField.setEditable(true);
        messages.setEditable(false);
        users.setEditable(false);

        // Помещаем каждый компонент отображения в абстрактную таблицу размером 2X2,
        // где "x" - это столбец, а "y" - строка
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(new JScrollPane(messages), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        this.add(new JScrollPane(users), gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(textField, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        this.add(sendButton, gridBagConstraints);

        this.pack();
        this.setVisible(true);
    }

    /**
     * Обработка запроса адреса сервера
     */
    @Override
    public void renderRequestServerAddress() {
        log.info("Запрашивается адрес сервера");
        String serverAddress = interrogativeMessagePane.askForServerAddress();
        if (serverAddress.equals("-1")) {
            clientController.handleExit();
        }
        clientController.handleServerAddress(serverAddress);
    }

    /**
     * Обработка запроса порта
     */
    @Override
    public void renderRequestPort() {
        log.info("Запрашивается порт");
        int port = interrogativeMessagePane.askForServerPort();
        if (port == -1) {
            clientController.handleExit();
        }
        clientController.handlePort(port);
    }

    /**
     * Обработка запроса имени
     */
    @Override
    public void renderRequestName() {
        log.info("Запрашивается имя пользователя");
        String name = interrogativeMessagePane.askForName();
        if (name.equals("-1")) {
            clientController.handleExit();
        }
        clientController.handleName(name);
    }

    /**
     * Обработка запроса обновления отображения списка пользователей
     *
     * @param allUserNames Множество имен всех пользователей
     */
    @Override
    public void renderRefreshUsers(Set<String> allUserNames) {
        log.info("Производится обновление отображения списка пользователей");
        StringBuilder allUserNamesStringBuilder = new StringBuilder();
        for (String name : allUserNames) {
            allUserNamesStringBuilder.append(name).append(System.lineSeparator());
        }
        users.setText(allUserNamesStringBuilder.toString());
    }

    /**
     * Обработка запроса обновления отображения сообщений
     *
     * @param message Текст нового сообщения
     */
    @Override
    public void renderRefreshMessages(String message) {
        log.info("Производится обновление отображения сообщений");
        messages.append(message);
        messages.append(System.lineSeparator());
    }
}
