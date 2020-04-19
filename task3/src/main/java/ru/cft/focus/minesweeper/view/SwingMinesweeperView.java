package ru.cft.focus.minesweeper.view;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focus.minesweeper.api.CellCode;
import ru.cft.focus.minesweeper.controller.MinesweeperController;
import ru.cft.focus.minesweeper.settings.DisplaySetting;
import ru.cft.focus.minesweeper.settings.GameSetting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

@Slf4j
public class SwingMinesweeperView extends JFrame implements MinesweeperView, ActionListener {

    private final IconRegistry iconRegistry = new IconRegistry();
    private final MessagePane messagePane = new MessagePane();
    private final MinesweeperController minesweeperController;
    private MinesweeperCell[][] cells;

    private JPanel cellPanel;
    private JLabel timerLabel;
    private JPanel timerPanel;

    private GameSetting gameSetting = GameSetting.EASY;


    public SwingMinesweeperView(MinesweeperController minesweeperController, int rowNumber,
                                int columnNumber) {
        super("Minesweeper");
        this.minesweeperController = minesweeperController;
        this.cells = new MinesweeperCell[rowNumber][columnNumber];
        this.timerLabel = new JLabel();


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new FlowLayout());
        setSize(calcWidth(columnNumber), calcHeight(rowNumber));
        setBackground(Color.BLACK);

        setGameGridPanel(rowNumber, columnNumber);
        generateCell(rowNumber, columnNumber);
        initMenuBar();

        setTimerLabel(columnNumber);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void initMenuBar() {
        MenuHelper.initGameMenu(this);
    }


    private int calcWidth(int columnNumber) {
        return columnNumber * DisplaySetting.CELL_WIDTH.getSize() +
                DisplaySetting.CELL_WIDTH.getSize();
    }

    private int calcHeight(int rowNumber) {
        return rowNumber * DisplaySetting.CELL_HEIGHT.getSize() +
                DisplaySetting.ACTIVE_PLAYER_PANEL_HEIGHT.getSize();
    }

    private void generateCell(int rowNumber, int columnNumber) {
        final boolean[] isFirstOpened = {true};
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                MinesweeperCell cell = new MinesweeperCell(i, j);
                cellPanel.add(cell);
                cell.setOpaque(true);
                cell.setBackground(Color.GRAY);

                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (isFirstOpened[0]) {
                                minesweeperController.initializeGameCells(cell.getRow(), cell.getColumn());
                                minesweeperController.startTimer();
                                isFirstOpened[0] = false;
                            }
                            minesweeperController.handleClickedLeftButtonOnCell(cell.getRow(),
                                    cell.getColumn());
                        }

                        if (e.getButton() == MouseEvent.BUTTON2) {
                            if (!isFirstOpened[0]) {
                                minesweeperController.handleClickedWheelButtonOnCell(cell.getRow(),
                                        cell.getColumn());
                            }
                        }

                        if (e.getButton() == MouseEvent.BUTTON3) {
                            if (!isFirstOpened[0]) {
                                minesweeperController.handleClickedRightButtonOnCell(cell.getRow(),
                                        cell.getColumn());
                            }
                        }
                    }
                });

                cells[i][j] = cell;
            }
        }
    }

    private void setTimerLabel(int columnNumber) {
        timerPanel = new JPanel();
        timerPanel.setLayout(new FlowLayout());
        timerPanel.setPreferredSize(new Dimension(columnNumber * DisplaySetting.CELL_WIDTH.getSize(),
                DisplaySetting.ACTIVE_PLAYER_PANEL_HEIGHT.getSize()));

        timerPanel.add(minesweeperController.initTextAreaWithTimer());

        timerPanel.add(timerLabel);
        add(timerPanel);
    }

    private void setGameGridPanel(int rowNumber, int columnNumber) {
        cellPanel = new JPanel();
        cellPanel.setBackground(Color.BLACK);
        cellPanel.setLayout(new GridLayout(rowNumber, columnNumber, DisplaySetting.WIDTH_GAP.getSize(),
                DisplaySetting.HEIGHT_GAP.getSize()));
        add(cellPanel);
    }


    @Override
    public void renderRestartGame(GameSetting gameSetting) {
        this.remove(cellPanel);
        this.remove(timerPanel);


        cells = new MinesweeperCell[gameSetting.getRowNumber()][gameSetting.getColumnNumber()];

        setGameGridPanel(gameSetting.getRowNumber(), gameSetting.getColumnNumber());
        generateCell(gameSetting.getRowNumber(), gameSetting.getColumnNumber());
        setTimerLabel(gameSetting.getColumnNumber());

        setSize(calcWidth(gameSetting.getColumnNumber()), calcHeight(gameSetting.getRowNumber()));

        renderNewGame();

        setLocationRelativeTo(null);
    }

    @Override
    public void renderNewGame() {
        for (MinesweeperCell[] cellRow : cells) {
            for (MinesweeperCell cell : cellRow) {
                Optional<ImageIcon> imageIconOpt = iconRegistry.getImageForCell("grave");
                if (!imageIconOpt.isPresent()) {
                    messagePane.sayNoImageFound();
                    return;
                }
                cell.setIcon(imageIconOpt.get());
            }
        }
    }

    @Override
    public void renderGameWon() {
        minesweeperController.stopTimer();
        messagePane.sayYouWin();

        if (gameSetting != GameSetting.CUSTOM) {
            String name = messagePane.askForAName();
            if (name.equals("-1")) {
                return;
            }
            String time = minesweeperController.askTimerForTime();
            minesweeperController.writeRecord(name, time, gameSetting);
        }
    }

    @Override
    public void renderGameLose() {
        minesweeperController.stopTimer();
        messagePane.sayYouLose();
    }

    @Override
    public void updateCell(int row, int column, String code) {

        Optional<ImageIcon> imageIconOpt = iconRegistry.getImageForCell(code);
        if (!imageIconOpt.isPresent()) {
            messagePane.sayNoImageFound();
            return;
        }
        if (code.equals(CellCode.MINE)) {
            cells[row][column].setBackground(Color.RED);
        }
        cells[row][column].setIcon(imageIconOpt.get());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();

        switch (command) {
            case "Легко":
                minesweeperController.restartGame(GameSetting.EASY);
                gameSetting = GameSetting.EASY;
                break;
            case "Средне":
                minesweeperController.restartGame(GameSetting.MEDIUM);
                gameSetting = GameSetting.MEDIUM;
                break;
            case "Сложно":
                minesweeperController.restartGame(GameSetting.HARD);
                gameSetting = GameSetting.HARD;
                break;
            case "Свои настройки":
                int rowNumber = messagePane.askForTheCountOfRows();
                if (rowNumber == -1) {
                    break;
                }
                GameSetting.CUSTOM.setRowNumber(rowNumber);

                int columnNumber = messagePane.askForTheCountOfColumns();
                if (columnNumber == -1) {
                    break;
                }
                GameSetting.CUSTOM.setColumnNumber(columnNumber);

                int countMines = messagePane.askForTheCountOfMines(rowNumber, columnNumber);
                if (countMines == -1) {
                    break;
                }
                GameSetting.CUSTOM.setCountMines(countMines);

                minesweeperController.restartGame(GameSetting.CUSTOM);
                gameSetting = GameSetting.CUSTOM;
                break;

            case "Выход":
                log.info("Пользователь вышел из игры");
                minesweeperController.exit();
                break;

            case "Для легких настроек":
                messagePane.showRecords(minesweeperController.getRecords(GameSetting.EASY));
                break;
            case "Для средних настроек":
                messagePane.showRecords(minesweeperController.getRecords(GameSetting.MEDIUM));
                break;
            case "Для сложных настроек":
                messagePane.showRecords(minesweeperController.getRecords(GameSetting.HARD));
                break;
        }
    }
}
