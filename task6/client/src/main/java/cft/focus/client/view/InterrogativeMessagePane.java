package cft.focus.client.view;

import javax.swing.*;

class InterrogativeMessagePane extends JOptionPane {

    /**
     * Спросить у пользователя его имя
     *
     * @return Имя пользователя;
     * -1 в случае, если пользователь решил выйти из программы.
     */
    String askForName() {
        while (true) {
            JTextField responseTextField = new JTextField(10);

            if (showQuestion("Введи свое имя: ", responseTextField) == -1) {
                if (askForExit() == 0) {
                    return "-1";
                } else {
                    continue;
                }
            }

            if (!responseTextField.getText().matches("^[а-яА-Яa-zA-Z0-9_-]{1,12}$")) {
                JOptionPane.showMessageDialog(this,
                        "Нельзя использовать такое имя. Имя должно содржать 1-12 символов," +
                                " которыми могут быть буквы и цифры, а также знаки \"-\" и \"_\"",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            } else if (responseTextField.getText().equals("-1")) {
                JOptionPane.showMessageDialog(this,
                        "Прости, но такое имя ввести нельзя",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                return responseTextField.getText();
            }
        }
    }

    /**
     * Спросить у пользователя адрес сервера
     *
     * @return Адрес сервера;
     * -1 в случае, если пользователь решил выйти из программы.
     */
    String askForServerAddress() {
        while (true) {
            JTextField responseTextField = new JTextField(15);
            responseTextField.setText("localhost");

            if (showQuestion("Введи адрес сервера: ", responseTextField) == -1) {
                if (askForExit() == 0) {
                    return "-1";
                } else {
                    continue;
                }
            }

            if (responseTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(this,
                        "Ты не ввел адрес!",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            } else if (responseTextField.getText().equals("-1")) {
                JOptionPane.showMessageDialog(this,
                        "Прости, но такой адрес ввести нельзя",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                return responseTextField.getText();
            }
        }
    }

    /**
     * Спросить у пользователя порт
     *
     * @return Номер порта;
     * -1 в случае, если пользователь решил выйти из программы.
     */
    int askForServerPort() {
        while (true) {
            JTextField responseTextField = new JTextField(10);
            responseTextField.setText("7777");

            if (showQuestion("Введи порт:", responseTextField) == -1) {
                if (askForExit() == 0) {
                    return -1;
                } else {
                    continue;
                }
            }

            try {
                int port = Integer.parseInt(responseTextField.getText());
                if (port > 65535 || port < 0) {
                    JOptionPane.showMessageDialog(this, "Порт не может быть " +
                            "меньше 0 или больше 65535!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else {
                    return port;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Пожалуйста, введи целое " +
                        "число больше нуля", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Спросить у пользователя желает ли он выйти
     *
     * @return 0, если да;
     * 1, если нет;
     * -1, если нажал на крестик (обрабатывать как отказ).
     */
    int askForExit() {
        String[] options = {"Yes", "No"};

        return JOptionPane.showOptionDialog(null,
                "Ты хочешь покинуть чат? :C",
                "Может, не стоит?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
    }

    /**
     * Вспомогательный метод для отображения вопросов
     *
     * @param question          Вопрос
     * @param responseTextField Текстовое поле для ответа
     * @return 0, если пользователь отправил ответ;
     * -1, если пользователь закрыл окно.
     */
    private int showQuestion(String question, JTextField responseTextField) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(question);
        panel.add(label);
        panel.add(responseTextField);

        String[] options = {"OK"};

        return JOptionPane.showOptionDialog(null,
                panel,
                "Конфигурация клиента",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }
}
