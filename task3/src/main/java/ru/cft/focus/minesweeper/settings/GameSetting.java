package ru.cft.focus.minesweeper.settings;

public enum GameSetting {

    EASY(9,9,3),
    MEDIUM(16,16,5),
    HARD(16,30,10),
    CUSTOM(0,0,0);

    private int rowNumber;
    private int columnNumber;
    private int countMines;

    GameSetting(int rowNumber, int columnNumber, int countMines) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.countMines = countMines;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public int getCountMines() {
        return countMines;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public void setCountMines(int countMines) {
        this.countMines = countMines;
    }
}
