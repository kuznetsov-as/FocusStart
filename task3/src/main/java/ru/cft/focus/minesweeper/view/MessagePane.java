package ru.cft.focus.minesweeper.view;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focus.minesweeper.model.Record;

import javax.swing.*;
import java.io.*;
import java.util.Objects;
import java.util.Optional;

@Slf4j
class MessagePane extends JOptionPane {

    private IconRegistry iconRegistry = new IconRegistry();

    void sayNoImageFound() {
        log.info("Не удалось найти иконку");
        JOptionPane.showMessageDialog(this, "Не удалось найти иконку", "Ошибка",
                JOptionPane.ERROR_MESSAGE);
    }

    void sayYouWin() {

        //Получаем иконку
        Optional<ImageIcon> imageIconOpt = iconRegistry.getImageForCell("pentagram");
        if (!imageIconOpt.isPresent()) {
            sayNoImageFound();
            return;
        }

        JOptionPane.showMessageDialog(this, "Поздравляю с победой!",
                "Теперь зомби не доберутся до нас", JOptionPane.INFORMATION_MESSAGE, imageIconOpt.get());
    }

    void sayYouLose() {

        //Получаем иконку
        Optional<ImageIcon> imageIconOpt = iconRegistry.getImageForCell("zombi");
        if (!imageIconOpt.isPresent()) {
            sayNoImageFound();
            return;
        }

        JOptionPane.showMessageDialog(this, "Тебя сожрали :(",
                "Суровые реалии", JOptionPane.INFORMATION_MESSAGE, imageIconOpt.get());
    }

    void showAbout() {
        JTextArea textArea = new JTextArea(30, 45);
        textArea.setEditable(false);

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File about = new File((Objects.requireNonNull(classLoader.getResource("about/about.txt"))).getFile());

        try(FileReader fileReader = new FileReader(about);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            StringBuilder inputFile = new StringBuilder();
            String textFieldReadable = bufferedReader.readLine();

            while (textFieldReadable != null){
                inputFile.append(textFieldReadable);
                inputFile.append(System.lineSeparator());
                textFieldReadable = bufferedReader.readLine();
            }

            textArea.setText(inputFile.toString());
        } catch (FileNotFoundException e) {
            log.info("Не удалось найти файл с правилами игры");
            textArea.setText("Не удалось найти файл с правилами игры");
        } catch (IOException e) {
            log.info("Не удалось получить данные из файла с правилами игры");
            textArea.setText("Не удалось получить данные из файла с правилами игры");
        }

        //Автоматическая прокрутка текстового поля вверх
        textArea.setCaretPosition(0);

        //Получаем иконку
        Optional<ImageIcon> imageIconOpt = iconRegistry.getImageForCell("zombi");
        if (!imageIconOpt.isPresent()) {
            sayNoImageFound();
            return;
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane,
                "Об игре", JOptionPane.INFORMATION_MESSAGE,
                imageIconOpt.get());
    }

    void showRecords(Record[] records) {
        StringBuilder stringRecords = new StringBuilder();

        if (records.length == 0) {
            stringRecords.append("Пока никто не побеждал!");
        } else {
            for (int i = 0; i < records.length; i++) {
                // Прибавляем 1, чтобы пропустить нулевое значение
                stringRecords.append(i + 1).append(": ").append(records[i].getName()).append(" - ").append(records[i].getTime());
                stringRecords.append(System.lineSeparator());
            }
        }

        JTextArea textArea = new JTextArea(15, 10);
        textArea.setEditable(false);
        textArea.setText(stringRecords.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Автоматическая прокрутка текстового поля вверх
        textArea.setCaretPosition(0);

        //Получаем иконку
        Optional<ImageIcon> imageIconOpt = iconRegistry.getImageForCell("pentagram");
        if (!imageIconOpt.isPresent()) {
            sayNoImageFound();
            return;
        }

        JOptionPane.showMessageDialog(this, scrollPane,
                "Рекорды", JOptionPane.INFORMATION_MESSAGE, imageIconOpt.get());
    }

    String askForAName() {

        while (true) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Введите ваше имя: ");
            panel.add(label);
            String[] options = {"OK"};
            JTextField textField = new JTextField(10);
            panel.add(textField);

            //Получаем иконку
            Optional<ImageIcon> imageIconOpt = iconRegistry.getImageForCell("question");
            if (!imageIconOpt.isPresent()) {
                sayNoImageFound();
                return "-1";
            }

            int selectedOption = JOptionPane.showOptionDialog(null, panel,
                    "Добавление в таблицу рекордов",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imageIconOpt.get(), options, options[0]);

            if (selectedOption == JOptionPane.CLOSED_OPTION) {
                return "-1";
            }

            if (textField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Ты не ввел имя!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            } else if (textField.getText().length() > 12) {
                JOptionPane.showMessageDialog(this, "Длина имени не должна превышать 12 символов",
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

            //Получаем иконку
            Optional<ImageIcon> imageIconOpt = iconRegistry.getImageForCell("question");
            if (!imageIconOpt.isPresent()) {
                sayNoImageFound();
                return -1;
            }

            int selectedOption = JOptionPane.showOptionDialog(null, panel,
                    "Создание игры с собственными настройками",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imageIconOpt.get(), options, options[0]);

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

            //Получаем иконку
            Optional<ImageIcon> imageIconOpt = iconRegistry.getImageForCell("question");
            if (!imageIconOpt.isPresent()) {
                sayNoImageFound();
                return -1;
            }

            int selectedOption = JOptionPane.showOptionDialog(null, panel,
                    "Создание игры с собственными настройками", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, imageIconOpt.get(), options, options[0]);

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
            JLabel label = new JLabel("Введите количество зомби:");
            panel.add(label);
            String[] options = {"OK"};
            JTextField textField = new JTextField(2);
            panel.add(textField);

            //Получаем иконку
            Optional<ImageIcon> imageIconOpt = iconRegistry.getImageForCell("question");
            if (!imageIconOpt.isPresent()) {
                sayNoImageFound();
                return -1;
            }

            int selectedOption = JOptionPane.showOptionDialog(null, panel,
                    "Создание игры с собственными настройками", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, imageIconOpt.get(), options, options[0]);

            if (selectedOption == JOptionPane.CLOSED_OPTION) {
                return -1;
            }

            try {
                int countMines = Integer.parseInt(textField.getText());
                if (countMines >= rowNumber * columnNumber || countMines < 1) {
                    JOptionPane.showMessageDialog(this, "Невозможно установить " +
                            "такое количество " + "зомби на поле", "Ошибка", JOptionPane.ERROR_MESSAGE);
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
