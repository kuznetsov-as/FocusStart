package ru.cft.focus.minesweeper.model;

import ru.cft.focus.minesweeper.api.CellCode;

public enum CellType {

    MINE(CellCode.MINE),
    FLAG(CellCode.FLAG),
    CLOSE_CELL(CellCode.CLOSE_CELL),
    OPEN_CELL(CellCode.OPEN_CELL),
    OPEN_CELL_WITH_THE_NUMBER_1(CellCode.OPEN_CELL_WITH_THE_NUMBER_1),
    OPEN_CELL_WITH_THE_NUMBER_2(CellCode.OPEN_CELL_WITH_THE_NUMBER_2),
    OPEN_CELL_WITH_THE_NUMBER_3(CellCode.OPEN_CELL_WITH_THE_NUMBER_3),
    OPEN_CELL_WITH_THE_NUMBER_4(CellCode.OPEN_CELL_WITH_THE_NUMBER_4),
    OPEN_CELL_WITH_THE_NUMBER_5(CellCode.OPEN_CELL_WITH_THE_NUMBER_5),
    OPEN_CELL_WITH_THE_NUMBER_6(CellCode.OPEN_CELL_WITH_THE_NUMBER_6),
    OPEN_CELL_WITH_THE_NUMBER_7(CellCode.OPEN_CELL_WITH_THE_NUMBER_7),
    OPEN_CELL_WITH_THE_NUMBER_8(CellCode.OPEN_CELL_WITH_THE_NUMBER_8);

    private String code;

    CellType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
