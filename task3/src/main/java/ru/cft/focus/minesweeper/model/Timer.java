package ru.cft.focus.minesweeper.model;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class Timer implements ActionListener {

    private int second = 0;
    private int minutes = 0;
    private javax.swing.Timer timer = new javax.swing.Timer(1000, this);
    private JTextArea textArea;

    public JTextArea initTextAreaWithTimer() {

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder());

        return textArea;
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
        return textArea.getText();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        second++;
        if (second > 60) {
            second = 0;
            minutes++;
        }
        textArea.setText(minutes + ":" + second);
    }
}
