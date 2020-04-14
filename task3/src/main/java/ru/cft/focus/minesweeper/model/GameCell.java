package ru.cft.focus.minesweeper.model;

public class GameCell {
    final int row;
    final int column;
    boolean isFlag;
    boolean isMine;
    boolean isOpen;
    int countMineNeighbors;

    GameCell(int row, int column, boolean isMine) {
        this.row = row;
        this.column = column;
        this.isMine = isMine;
    }
}
