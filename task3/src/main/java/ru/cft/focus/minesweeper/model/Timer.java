package ru.cft.focus.minesweeper.model;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class Timer implements ActionListener {

    private int second = 0;
    private int minutes = 0;
    private javax.swing.Timer timer = new javax.swing.Timer(1000, this);
    private JTextArea textAreaWithTimer;

    public JTextArea initTextAreaWithTimer() {
        textAreaWithTimer = new JTextArea();

        //Устанавливаем фон текстового поля в цвет окна игры
        textAreaWithTimer.setBackground(new Color(238, 238, 238));

        textAreaWithTimer.setText("Время: 0:0");
        textAreaWithTimer.setEditable(false);
        textAreaWithTimer.setBorder(BorderFactory.createEmptyBorder());

        return textAreaWithTimer;
    }

    public void startTimer() {
        timer.start();
        log.info("Таймер запущен");
    }

    public void stopTimer() {
        second = 0;
        minutes = 0;
        timer.stop();
        log.info("Таймер остановлен");
    }

    public String getTime() {
        return textAreaWithTimer.getText().replace("Время: ", "");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        second++;
        if (second > 60) {
            second = 0;
            minutes++;
        }
        textAreaWithTimer.setText("Время: " + minutes + ":" + second);
    }
}
