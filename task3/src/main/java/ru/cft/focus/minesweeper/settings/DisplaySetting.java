package ru.cft.focus.minesweeper.settings;

public enum DisplaySetting {

    ACTIVE_CELL_WIDTH(40),
    ACTIVE_CELL_HEIGHT(40),

    WIDTH_GAP(2),
    HEIGHT_GAP(2),

    CELL_WIDTH(ACTIVE_CELL_WIDTH.size + WIDTH_GAP.size),
    CELL_HEIGHT(ACTIVE_CELL_HEIGHT.size + HEIGHT_GAP.size),

    GAME_INFO_PANEL_HEIGHT(110),
    TIME_PANEL_HEIGHT(25),
    RESTART_BUTTON_HEIGHT(25),
    NUMBER_OF_MINES_PANEL_HEIGHT(25);


    private int size;

    DisplaySetting(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
