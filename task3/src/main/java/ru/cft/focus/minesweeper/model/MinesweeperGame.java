package ru.cft.focus.minesweeper.model;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focus.minesweeper.settings.GameSetting;
import ru.cft.focus.minesweeper.view.MinesweeperView;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class MinesweeperGame {

    private int rowNumber;
    private int columnNumber;
    private int countFlags;
    private int countMines;
    private int countClosedCells;
    private boolean isGameStopped;
    private GameCell[][] gameCells;

    private final MinesweeperViewNotifier viewNotifier = new MinesweeperViewNotifier();

    public MinesweeperGame(int rowNumber, int columnNumber, int countMines) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.countMines = countMines;
        this.countClosedCells = this.rowNumber * this.columnNumber;
        this.gameCells = new GameCell[this.rowNumber][this.columnNumber];
        this.countFlags = this.countMines;
        initializeGameCells(rowNumber, columnNumber);
    }

    public void startNewGame() {
        viewNotifier.notifyViewsNewGame();
    }

    public void attachView(MinesweeperView minesweeperView) {
        viewNotifier.attachView(minesweeperView);
    }

    public void markCell(int row, int column) {
        if (isGameStopped) {
            return;
        }

        if (row >= rowNumber || column >= columnNumber) {
            return;
        }

        if (gameCells[row][column].isOpen) {
            return;
        }

        if (countFlags == 0 && !gameCells[row][column].isFlag) {
            return;
        }

        if (gameCells[row][column].isFlag) {
            gameCells[row][column].isFlag = false;
            countFlags++;
            log.info("Пользователь убрал флаг с ячейки под координатами " + row + " : " + column);
            viewNotifier.notifyViewAboutCellStatusChanged(row, column, CellType.CLOSE_CELL);
        } else {
            gameCells[row][column].isFlag = true;
            countFlags--;
            log.info("Пользователь поставил флаг для ячейки под координатами " + row + " : " + column);
            viewNotifier.notifyViewAboutCellStatusChanged(row, column, CellType.FLAG);
        }
    }

    public void openCellNeighbors(int row, int column) {
        if (gameCells[row][column].isOpen && !gameCells[row][column].isMine && !gameCells[row][column].isFlag) {
            List<GameCell> neighbors = getNeighbors(gameCells[row][column]);
            int countOfNeighborsWithAFlag = 0;
            for (GameCell neighbor : neighbors) {
                if (neighbor.isFlag) {
                    countOfNeighborsWithAFlag++;
                }
            }

            if (gameCells[row][column].countMineNeighbors == countOfNeighborsWithAFlag) {
                for (GameCell neighbor : neighbors) {
                    openCell(neighbor.row, neighbor.column);
                }
            }
        }
    }

    public void openCell(int row, int column) {

        log.info("Была открыта ячейка с координатами: строка - " + row + " столбец - " + column);

        if (gameCells[row][column].isFlag || isGameStopped || gameCells[row][column].isOpen) {
            return;
        }

        if (gameCells[row][column].isMine) {
            viewNotifier.notifyViewAboutCellStatusChanged(row, column, CellType.MINE);


            for (GameCell[] cellRow : gameCells) {
                for (GameCell cell : cellRow) {
                    if (cell.isMine) {
                        viewNotifier.notifyViewAboutCellStatusChanged(cell.row, cell.column, CellType.MINE);
                    }
                }
            }

            realizeLose();

            gameCells[row][column].isOpen = true;
            return;
        } else {
            countClosedCells--;
            gameCells[row][column].isOpen = true;
            if (gameCells[row][column].countMineNeighbors != 0) {
                switch (gameCells[row][column].countMineNeighbors) {
                    case 1:
                        viewNotifier.notifyViewAboutCellStatusChanged(row, column,
                                CellType.OPEN_CELL_WITH_THE_NUMBER_1);
                        break;
                    case 2:
                        viewNotifier.notifyViewAboutCellStatusChanged(row, column,
                                CellType.OPEN_CELL_WITH_THE_NUMBER_2);
                        break;
                    case 3:
                        viewNotifier.notifyViewAboutCellStatusChanged(row, column,
                                CellType.OPEN_CELL_WITH_THE_NUMBER_3);
                        break;
                    case 4:
                        viewNotifier.notifyViewAboutCellStatusChanged(row, column,
                                CellType.OPEN_CELL_WITH_THE_NUMBER_4);
                        break;
                    case 5:
                        viewNotifier.notifyViewAboutCellStatusChanged(row, column,
                                CellType.OPEN_CELL_WITH_THE_NUMBER_5);
                        break;
                    case 6:
                        viewNotifier.notifyViewAboutCellStatusChanged(row, column,
                                CellType.OPEN_CELL_WITH_THE_NUMBER_6);
                        break;
                    case 7:
                        viewNotifier.notifyViewAboutCellStatusChanged(row, column,
                                CellType.OPEN_CELL_WITH_THE_NUMBER_7);
                        break;
                    case 8:
                        viewNotifier.notifyViewAboutCellStatusChanged(row, column,
                                CellType.OPEN_CELL_WITH_THE_NUMBER_8);
                        break;
                }
            } else {
                viewNotifier.notifyViewAboutCellStatusChanged(row, column, CellType.OPEN_CELL);
                List<GameCell> neighbors = getNeighbors(gameCells[row][column]);
                for (GameCell cell : neighbors) {
                    if (!cell.isOpen && !cell.isMine) {
                        openCell(cell.row, cell.column);
                    }
                }
            }
        }

        if (countClosedCells == countMines && !isGameStopped) {
            realizeWin();
        }
    }

    public Record[] getRecords(GameSetting gameSetting) {

        log.info("Произведен запрос рекордов для настроек: " + gameSetting.name());

        Record[] records = new Record[0];

        File fileWithRecords;
        switch (gameSetting) {
            case EASY:
                fileWithRecords = new File("easy_records.bin");
                break;
            case MEDIUM:
                fileWithRecords = new File("medium_records.bin");
                break;
            default:
                fileWithRecords = new File("hard_records.bin");
                break;
        }

        try (FileInputStream fileInputStream = new FileInputStream(fileWithRecords);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            records = (Record[]) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            log.error("При попытки чтения рекордов не удалось обнаружить файл с информацией о них");
        } catch (IOException e) {
            log.error("Не удалось прочесть данные рекордов");
        } catch (ClassNotFoundException e) {
            log.error("Не удалось найти подходящий для приведения класс");
        }

        return records;
    }

    public void addRecord(String name, String time, GameSetting gameSetting) {

        log.info("Производится добавление нового рекорда");

        Record[] oldRecords;
        oldRecords = getRecords(gameSetting);

        Record[] newRecords = new Record[oldRecords.length + 1];

        for (int i = 0; i < newRecords.length; i++) {
            if (i == newRecords.length - 1 || oldRecords.length == 0) {
                newRecords[i] = new Record(name, time);
            } else {
                newRecords[i] = oldRecords[i];
            }
        }

        Arrays.sort(newRecords);

        File fileWithRecords;
        switch (gameSetting) {
            case EASY:
                fileWithRecords = new File("easy_records.bin");
                break;
            case MEDIUM:
                fileWithRecords = new File("medium_records.bin");
                break;
            default:
                fileWithRecords = new File("hard_records.bin");
                break;
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(fileWithRecords);
             ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream)) {
            objectInputStream.writeObject(newRecords);
        } catch (FileNotFoundException e) {
            log.error("При добавлении нового рекорда не удалось обнаружить файл с информацией о них");
        } catch (IOException e) {
            log.error("Не удалось записать данные нового рекорда");
        }
    }

    public void restartGame(GameSetting gameSetting) {
        log.info("Пользователь запустил новую игру с настройками: " + gameSetting.name());
        isGameStopped = false;
        rowNumber = gameSetting.getRowNumber();
        columnNumber = gameSetting.getColumnNumber();
        countMines = gameSetting.getCountMines();
        countClosedCells = rowNumber * columnNumber;
        gameCells = new GameCell[rowNumber][columnNumber];
        countFlags = this.countMines;

        initializeGameCells(rowNumber, columnNumber);
        viewNotifier.notifyViewsRestartGame(gameSetting);
    }

    private void initializeGameCells(int rowNumber, int columnNumber) {
        log.info("Производится инициализация ячеек на поле");
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                gameCells[i][j] = new GameCell(i, j, false);
            }
        }

        for (int i = 0; i < countMines; i++) {

            int row = (int) (Math.random() * rowNumber);
            int column = (int) (Math.random() * columnNumber);

            if (gameCells[row][column].isMine) {
                i--;
            } else {
                gameCells[row][column] = new GameCell(row, column, true);
                log.info("В ячейке под координатами " + row + " : " + column + " теперь стоит мина");
            }

        }

        log.info("Все мины расставлены на поле");

        countMineNeighbors();
    }

    private void countMineNeighbors() {
        for (GameCell[] cellRow : gameCells) {
            for (GameCell cell : cellRow) {
                if (!cell.isMine) {
                    List<GameCell> neighbors = getNeighbors(cell);
                    for (GameCell neighbor : neighbors) {
                        if (neighbor.isMine) {
                            cell.countMineNeighbors++;
                        }
                    }
                }
            }
        }
    }

    private List<GameCell> getNeighbors(GameCell cell) {
        List<GameCell> result = new ArrayList<>();
        for (int row = cell.row - 1; row <= cell.row + 1; row++) {
            for (int column = cell.column - 1; column <= cell.column + 1; column++) {
                if (row < 0 || row >= rowNumber) {
                    continue;
                }
                if (column < 0 || column >= columnNumber) {
                    continue;
                }
                if (gameCells[row][column] == cell) {
                    continue;
                }
                result.add(gameCells[row][column]);
            }
        }
        return result;
    }

    private void realizeLose() {
        log.info("Игра проиграна");
        isGameStopped = true;
        viewNotifier.notifyViewGameLose();
    }

    private void realizeWin() {
        log.info("Игра выиграна");
        viewNotifier.notifyViewGameWon();
        isGameStopped = true;
    }
}