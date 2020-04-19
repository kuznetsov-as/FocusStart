package ru.cft.focus.minesweeper.controller;

import ru.cft.focus.minesweeper.model.MinesweeperGame;
import ru.cft.focus.minesweeper.model.Record;
import ru.cft.focus.minesweeper.model.Timer;
import ru.cft.focus.minesweeper.settings.GameSetting;

import javax.swing.*;

public class MinesweeperController {
    private final MinesweeperGame minesweeperGame;
    private final Timer timer = new Timer();

    public MinesweeperController(MinesweeperGame minesweeperGame) {
        this.minesweeperGame = minesweeperGame;
    }

    public void handleClickedRightButtonOnCell(int row, int column) {
        minesweeperGame.markCell(row, column);
    }

    public void handleClickedWheelButtonOnCell(int row, int column) {
        minesweeperGame.openCellNeighbors(row, column);
    }

    public void handleClickedLeftButtonOnCell(int row, int column) {
        minesweeperGame.openCell(row, column);
    }

    public void initializeGameCells(int firstOpenCellRow, int firstOpenCellColumn) {
        minesweeperGame.initializeGameCells(firstOpenCellRow, firstOpenCellColumn);
    }

    public void exit() {
        System.exit(0);
    }

    public void restartGame(GameSetting gameSetting) {
        stopTimer();
        minesweeperGame.restartGame(gameSetting);
    }

    public JTextArea initTextAreaWithTimer() {
        return timer.initTextAreaWithTimer();
    }

    public int numberOfRemainingMines() {
        return minesweeperGame.getCountFlags();
    }

    public void startTimer() {
        timer.startTimer();
    }

    public void stopTimer() {
        timer.stopTimer();
    }

    public String askTimerForTime() {
        return timer.getTime();
    }

    public Record[] getRecords(GameSetting gameSetting) {
        return minesweeperGame.getRecords(gameSetting);
    }

    public void writeRecord(String name, String time, GameSetting gameSetting) {
        minesweeperGame.addRecord(name, time, gameSetting);
    }
}
