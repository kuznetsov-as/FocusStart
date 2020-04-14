package ru.cft.focus.minesweeper.model;

import ru.cft.focus.minesweeper.settings.GameSetting;
import ru.cft.focus.minesweeper.view.MinesweeperView;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperViewNotifier {
    private final List<MinesweeperView> minesweeperViews = new ArrayList<>();

    void attachView(MinesweeperView minesweeperView) {
        minesweeperViews.add(minesweeperView);
    }

    void notifyViewsNewGame() {
        minesweeperViews.forEach(MinesweeperView::renderNewGame);
    }

    void notifyViewsRestartGame(GameSetting gameSetting) {
        minesweeperViews.forEach(minesweeperView -> minesweeperView.renderRestartGame(gameSetting));
    }

    void notifyViewAboutCellStatusChanged(int row, int column, CellType cellType) {
        minesweeperViews.forEach(minesweeperView -> minesweeperView.updateCell(row, column, cellType.getCode()));
    }

    void notifyViewGameWon() {
        minesweeperViews.forEach(MinesweeperView::renderGameWon);
    }

    void notifyViewGameLose() {
        minesweeperViews.forEach(MinesweeperView::renderGameLose);
    }
}
