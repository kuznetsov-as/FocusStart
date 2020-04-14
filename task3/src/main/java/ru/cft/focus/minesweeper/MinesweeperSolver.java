package ru.cft.focus.minesweeper;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focus.minesweeper.controller.MinesweeperController;
import ru.cft.focus.minesweeper.model.MinesweeperGame;
import ru.cft.focus.minesweeper.settings.GameSetting;
import ru.cft.focus.minesweeper.view.MinesweeperView;
import ru.cft.focus.minesweeper.view.SwingMinesweeperView;

@Slf4j
public class MinesweeperSolver {
    public static void main(String[] args) {
        log.info("Программа запущена");
        new MinesweeperSolver().solve(GameSetting.EASY);
    }

    public void solve(GameSetting gameSetting) {

        MinesweeperGame minesweeperGame = new MinesweeperGame(gameSetting.getRowNumber(), gameSetting.getColumnNumber(), gameSetting.getCountMines());
        MinesweeperController minesweeperController = new MinesweeperController(minesweeperGame);
        MinesweeperView minesweeperView = new SwingMinesweeperView(minesweeperController, gameSetting.getRowNumber(), gameSetting.getColumnNumber());

        minesweeperGame.attachView(minesweeperView);
        minesweeperGame.startNewGame();
    }
}
