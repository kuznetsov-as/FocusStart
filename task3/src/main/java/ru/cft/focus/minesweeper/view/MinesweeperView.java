package ru.cft.focus.minesweeper.view;

import ru.cft.focus.minesweeper.settings.GameSetting;

public interface MinesweeperView {
    void renderNewGame();

    void renderRestartGame(GameSetting gameSetting);

    void renderGameWon();

    void renderGameLose();

    void updateCell(int row, int column, String code);
}
