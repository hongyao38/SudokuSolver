package main;

import ui.*;

public class SudokuBoard {
    
    // App parameters
    private AppPanel ap;
    public MenuButton solveButton;
    public MenuButton resetButton;

    // Board
    public Cell[][] board = new Cell[9][9];
    public Cell selectedCell;

    public SudokuBoard(AppPanel ap) {
        this.ap = ap;

        // Initialise board to empty board
        for (int i = 0; i < 9; i++) {
            int y = (i + 1) * ap.tileSize;

            for (int j = 0; j < 9; j++) {
                int x = (j + 1) * ap.tileSize;

                Cell c = new Cell(x, y, i, j, ap);
                this.ap.add(c);
                board[i][j] = c;
            }
        }

        // Create solve button
        solveButton = new MenuButton(11 * ap.tileSize, 9 * ap.tileSize, 2 * ap.tileSize, ap.tileSize, ap);
        this.ap.add(solveButton);

        // Create reset button
        resetButton = new MenuButton(11 * ap.tileSize, 7 * ap.tileSize, 2 * ap.tileSize, ap.tileSize, ap);
        this.ap.add(resetButton);
    }

    public void solve() {
        boolean movedBack = false;
        int cellNo = 0;

        while (cellNo > -1 && cellNo < 81) {

            int i = cellNo / 9;
            int j = cellNo % 9;
            Cell cell = board[i][j];

            // If cell is a fixed cell, bypass
            if (cell.isFixed) {
                cellNo = movedBack ? cellNo - 1 : cellNo + 1;

            // If current cell has no candidates, move back one cell
            } else if (!cell.hasCandidates(movedBack)) {
                cell.number = 0;
                cellNo--;
                movedBack = true;

            // If still has candidates, continue going forward
            } else {
                cellNo++;
                movedBack = false;
            }
        }
    }

    public boolean isValidRow(Cell cell) {
        int count = 0;
        for (int j = 0; j < 9; j++) {
            int num = board[cell.i][j].number;
            if (num == cell.number) {
                count++;
            }
        }
        return count == 1;
    }

    public boolean isValidCol(Cell cell) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            int num = board[i][cell.j].number;
            if (num == cell.number) {
                count++;
            }
        }
        return count == 1;
    }

    public boolean isValidZone(Cell cell) {
        int row = cell.i / 3;
        int col = cell.j / 3;
        int count = 0;
        for (int i = row * 3; i < row * 3 + 3; i ++) {
            for (int j = col * 3; j < col * 3 + 3; j++) {
                int num = board[i][j].number;
                if (num == cell.number) {
                    count++;
                }
            }
        }
        return count == 1;
    }

    public void reset() {
        for (Cell[] row : board) {
            for (Cell c : row) {
                c.isFixed = false;
                c.number = 0;
            }
        }
    }
}
