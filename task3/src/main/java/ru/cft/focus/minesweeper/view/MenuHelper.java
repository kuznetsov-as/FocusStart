package ru.cft.focus.minesweeper.view;

import javax.swing.*;

public class MenuHelper {

    public static void initGameMenu(SwingMinesweeperView view) {


        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Игра");

        JMenuItem newEasyGame = new JMenuItem("Легко");
        newEasyGame.addActionListener(view);
        menuGame.add(newEasyGame);

        JMenuItem newMediumGame = new JMenuItem("Средне");
        newMediumGame.addActionListener(view);
        menuGame.add(newMediumGame);

        JMenuItem newHardGame = new JMenuItem("Сложно");
        newHardGame.addActionListener(view);
        menuGame.add(newHardGame);

        JMenuItem newCustomSettingsGame = new JMenuItem("Свои настройки");
        newCustomSettingsGame.addActionListener(view);
        menuGame.add(newCustomSettingsGame);

        menuGame.addSeparator();

        JMenuItem exitGame = new JMenuItem("Выход");
        exitGame.addActionListener(view);
        menuGame.add(exitGame);


        JMenu menuRecords = new JMenu("Рекорды");

        JMenuItem easyRecords = new JMenuItem("Для легких настроек");
        easyRecords.addActionListener(view);
        menuRecords.add(easyRecords);

        JMenuItem mediumRecords = new JMenuItem("Для средних настроек");
        mediumRecords.addActionListener(view);
        menuRecords.add(mediumRecords);

        JMenuItem hardRecords = new JMenuItem("Для сложных настроек");
        hardRecords.addActionListener(view);
        menuRecords.add(hardRecords);

        menuBar.add(menuGame);
        menuBar.add(menuRecords);
        menuBar.setOpaque(true);
        view.setJMenuBar(menuBar);
    }
}
