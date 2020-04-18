package ru.cft.focus.minesweeper.view;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focus.minesweeper.model.Record;

import javax.swing.*;

@Slf4j
class MessagePane extends JOptionPane {

    void sayNoImageFound() {
        log.info("Не удалось найти иконку");
        JOptionPane.showMessageDialog(this, "Не удалось найти иконку", "Ошибка",
                JOptionPane.ERROR_MESSAGE);
    }

    void sayYouWin() {
        JOptionPane.showMessageDialog(this, "Поздравляю с победой!",
                "Теперь зомби не доберутся до нас", JOptionPane.INFORMATION_MESSAGE);
    }

    void sayYouLose() {
        JOptionPane.showMessageDialog(this, "Тебя сожрали :(",
                "Суровые реалии", JOptionPane.INFORMATION_MESSAGE);
    }

    void showRecords(Record[] records) {
        StringBuilder stringRecords = new StringBuilder();

        if (records.length == 0) {
            stringRecords.append("Пока никто не побеждал!");
        } else {
            for (int i = 0; i < records.length; i++) {
                stringRecords.append(i + 1).append(": ").append(records[i].getName()).append(" - ").append(records[i].getTime());
                stringRecords.append(System.lineSeparator());
            }
        }

        JTextArea textArea = new JTextArea(15, 10);
        textArea.setEditable(false);
        textArea.setText(stringRecords.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane,
                "Рекорды", JOptionPane.INFORMATION_MESSAGE);
    }

    String askForAName() {

        while (true) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Введите ваше имя: ");
            panel.add(label);
            String[] options = {"OK"};
            JTextField textField = new JTextField(10);
            panel.add(textField);

            int selectedOption = JOptionPane.showOptionDialog(null, panel,
                    "Добавление в таблицу рекордов",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (selectedOption == JOptionPane.CLOSED_OPTION) {
                return "-1";
            }

            if (textField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Ты не ввел имя!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            } else {
                return textField.getText();
            }
        }
    }

    int askForTheCountOfRows() {

        while (true) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Введите количество рядов:");
            panel.add(label);
            String[] options = {"OK"};
            JTextField textField = new JTextField(2);
            panel.add(textField);

            int selectedOption = JOptionPane.showOptionDialog(null, panel,
                    "Создание игры с собственными настройками",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (selectedOption == JOptionPane.CLOSED_OPTION) {
                return -1;
            }

            try {
                int rowNumber = Integer.parseInt(textField.getText());
                if (rowNumber > 16 || rowNumber < 5) {
                    JOptionPane.showMessageDialog(this, "Количество рядов не может быть " +
                            "меньше 5 или больше 16!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else {
                    return rowNumber;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Пожалуйста, введите целое " +
                        "число больше нуля", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    int askForTheCountOfColumns() {

        while (true) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Введите количество столбцов:");
            panel.add(label);
            String[] options = {"OK"};
            JTextField textField = new JTextField(2);
            panel.add(textField);

            int selectedOption = JOptionPane.showOptionDialog(null, panel,
                    "Создание игры с собственными настройками", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (selectedOption == JOptionPane.CLOSED_OPTION) {
                return -1;
            }

            try {
                int columnNumber = Integer.parseInt(textField.getText());
                if (columnNumber > 30 || columnNumber < 5) {
                    JOptionPane.showMessageDialog(this, "Количество столбцов не может быть " +
                            "меньше 1 или больше 30!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else {
                    return columnNumber;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Пожалуйста, введите целое " +
                        "число больше нуля", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    int askForTheCountOfMines(int rowNumber, int columnNumber) {

        while (true) {

            JPanel panel = new JPanel();
            JLabel label = new JLabel("Введите количество мин:");
            panel.add(label);
            String[] options = {"OK"};
            JTextField textField = new JTextField(2);
            panel.add(textField);

            int selectedOption = JOptionPane.showOptionDialog(null, panel,
                    "Создание игры с собственными настройками",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (selectedOption == JOptionPane.CLOSED_OPTION) {
                return -1;
            }

            try {
                int countMines = Integer.parseInt(textField.getText());
                if (countMines >= rowNumber * columnNumber || countMines < 1) {
                    JOptionPane.showMessageDialog(this, "Невозможно установить " +
                            "такое количество " + "мин на поле", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else {
                    return countMines;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Пожалуйста, введите целое " +
                        "число больше нуля", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
